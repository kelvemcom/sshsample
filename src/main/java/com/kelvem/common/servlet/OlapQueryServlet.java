package com.kelvem.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

// http://kelvem-pc2:8080/sshsample/OlapQueryServlet/query
// http://localhost:8080/sshsample/page/self-query.html?table=sys_user
// file:///D:/Project/MyEclipse10/Workspaces/SSH2Sample/WebRoot/page/self-query.html?table=a1_c
public class OlapQueryServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7755601139444058096L;

    public String user = "root";
    public String password = "321456";
    public String ip = "localhost";
    public String port = "3306";
    public String schema = "blog";
    public String url = "jdbc:mysql://" + ip + ":" + port + "/" + schema;
    public String driver = "com.mysql.jdbc.Driver";
    //String driver = "org.gjt.mm.mysql.Driver";
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String json = "";
		String method = request.getPathInfo().replace("/", "");
		if ("init".equals(method)) {
			json = init(request);
		} else if ("query".equals(method)) {
			json = query(request);
		} else if ("matrix".equals(method)) {
			json = matrix(request);
		} else {

		}
		
		// 使用 "out"把应答内容发送到浏览器
		PrintWriter out = response.getWriter();
		out.write(json);
		out.close();
	}
	
//	SELECT t.table_name, t.column_name, t.data_type, t.column_key FROM INFORMATION_SCHEMA.COLUMNS t where t.table_schema='blog' and t.table_name='a1_c' order by t.ordinal_position;
// select * from a1_c
	
	private String init(HttpServletRequest request) {
		String tableName = request.getParameter("table");
		System.out.println(tableName);
		
		String sql = "SELECT t.ordinal_position as id, t.column_name as name, t.data_type as data_type, " +
				" IF(column_comment='',column_name, column_comment) as display, " +
				" (case when t.column_key='PRI' then 'data' else '' end) as type, " +
				" (case when t.column_key='PRI' then 'false' else 'false' end) as isKey, " +
				" (case when t.column_key='PRI' then 'true' else 'false' end) as isParam " +
				" from INFORMATION_SCHEMA.COLUMNS t " +
				" where t.table_schema='" + schema + "' and t.table_name='" + tableName + "' order by t.ordinal_position";
		
		List<Map<String, String>> result = query(sql);
		
		// DATE(create_time)
		Map<String, String> buf = new HashMap<String, String>();
		buf.put("id", "111");
		buf.put("name", "DATE(create_time)");
		buf.put("display", "年月日");
		buf.put("type", "col");
		result.add(buf);
		
		// DATE_FORMAT(create_time, '%Y-%m')
		buf = new HashMap<String, String>();
		buf.put("id", "111");
		buf.put("name", "DATE_FORMAT(create_time, '%Y-%m')");
		buf.put("display", "年月");
		buf.put("type", "col");
		result.add(buf);
		
		JSONObject json = new JSONObject();
		json.put("items", result);
		
		System.out.println(json.toString());
		return json.toString();
		
//		return "{'items':[{'id':'101','name':'name1','type':'col','isKey':'true','isParam':'true'}," +
//						 "{'id':'102','name':'name2','type':'row','isKey':'false','isParam':'true'}]," +
//				"'params':[{'id':'101','name':'name1','type':'1','value':'1'}," +
//						  "{'id':'102','name':'name2','type':'2','value':'2'}]}";
	}
	
	private String query(HttpServletRequest request) {
		System.out.println("==================   query   ====================");
		String tableName = request.getParameter("table");
		String cols = request.getParameter("cols");
		String params = request.getParameter("params");
		System.out.println("tableName = " + tableName);
		System.out.println("cols = " + cols);
		System.out.println("params = " + params);
		
//		String sql = "SELECT t.ordinal_position as id, t.column_name as name, t.data_type as data_type, " +
//				" (case when t.column_key='PRI' then 'col' else '' end) as type, " +
//				" (case when t.column_key='PRI' then 'true' else 'false' end) as isKey, " +
//				" (case when t.column_key='PRI' then 'true' else 'false' end) as isParam " +
//				" from INFORMATION_SCHEMA.COLUMNS t " +
//				" where t.table_schema='" + schema + "' and t.table_name='" + tableName + "' order by t.ordinal_position";
//		
//		List<Map<String, String>> result = query(sql);
		
		// cols
		List<String> colList = new ArrayList<String>();
		for (String colName : cols.split(",")) {
			if (colName.trim().length() > 0) {
				colList.add(colName);
			}
		}

		String sql_col = "'" + tableName + "'";
		for (String colName : colList) {
			sql_col += ", " + colName;
		}
		
		// params
		List<String> paramList = new ArrayList<String>();
		for (String param : params.split(",")) {
			if (param.trim().length() <= 0) {
				continue;
			}
			String[] paramArr = param.split("\\$");
			if (paramArr.length != 3){
				continue;
			}
			if ("1".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " = '" + paramArr[2] + "'");
			} else if ("2".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " > '" + paramArr[2] + "'");
			} else if ("3".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " < '" + paramArr[2] + "'");
			} else if ("4".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " in (" + paramArr[2] + ")");
			} else {
				// do nothing
			}
		}

		String sql_where = " 1=1 ";
		for (String where : paramList) {
			sql_where += " and " + where;
		}
		
		String sqlData = "select " + sql_col + " from " + tableName + " where " + sql_where + " limit 10";
		List<Map<String, String>> data = query(sqlData);
		
		List<List<String>> table = new ArrayList<List<String>>();
		for (Map<String, String> map : data) {
			List<String> row = new ArrayList<String>();
			for (String colName : colList) {
				row.add(map.get(colName));
			}
			table.add(row);
		}
		JSONObject json = new JSONObject();
		json.put("data", table);
		
		System.out.println(json.toString());
		return json.toString();
	}
	
	// http://localhost:8080/sshsample/OlapQueryServlet/matrix?table=sys_user&cols=DATE_FORMAT(create_time,%20%27%Y-%m%27)&rows=UPDATE_TIME&data=sys_user_ID&params=,%E7%94%A8%E6%88%B7ID$1$,
	
	private String matrix(HttpServletRequest request) {
		System.out.println("==================   matrix   ====================");
		String tableName = request.getParameter("table");
		String cols = request.getParameter("cols");
		String rows = request.getParameter("rows");
		String data = request.getParameter("data");
		String params = request.getParameter("params");
		System.out.println("tableName = " + tableName);
		System.out.println("cols = " + cols);
		System.out.println("rows = " + rows);
		System.out.println("data = " + data);
		System.out.println("params = " + params);
		
//		String sql = "SELECT t.ordinal_position as id, t.column_name as name, t.data_type as data_type, " +
//				" (case when t.column_key='PRI' then 'col' else '' end) as type, " +
//				" (case when t.column_key='PRI' then 'true' else 'false' end) as isKey, " +
//				" (case when t.column_key='PRI' then 'true' else 'false' end) as isParam " +
//				" from INFORMATION_SCHEMA.COLUMNS t " +
//				" where t.table_schema='" + schema + "' and t.table_name='" + tableName + "' order by t.ordinal_position";
//		
//		List<Map<String, String>> result = query(sql);
		
		// params
		List<String> paramList = new ArrayList<String>();
		for (String param : params.split(",")) {
			if (param.trim().length() <= 0) {
				continue;
			}
			String[] paramArr = param.split("\\$");
			if (paramArr.length != 3){
				continue;
			}
			if ("1".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " = '" + paramArr[2] + "'");
			} else if ("2".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " > '" + paramArr[2] + "'");
			} else if ("3".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " < '" + paramArr[2] + "'");
			} else if ("4".equals(paramArr[1])) {
				paramList.add(paramArr[0] + " in (" + paramArr[2] + ")");
			} else {
				// do nothing
			}
		}

		String sql_where = " 1=1 ";
		for (String where : paramList) {
			sql_where += " and " + where;
		}
		
		// result
		List<List<String>> table = new ArrayList<List<String>>();
		List<String> row = new ArrayList<String>();
		row.add(rows);
		
		// cols
		String sqlCol = "select " + cols + " as cols from " + tableName + " where " + sql_where + " group by " + cols + " limit 5";
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> colDs = query(sqlCol);
		for (Map<String, String> colName : colDs) {
			String name = "null";
			if (colName.get("cols") == null) {
				row.add("null");
				sb.append(", case when ccol is null then ddata else 0 end as '" + name + "'");
			} else {
				name = colName.get("cols").trim();
				if (name.length() == 0) {
					name = "_";
				}
				row.add(name);
				sb.append(", case when ccol='" + name + "' then ddata else 0 end as '" + name + "'");
			}
		}
		//sb.append("'" + tableName + "'");
		table.add(row);
		
//	SELECT 
//		rrow , 
//		CASE WHEN ccol IS NULL THEN ddata ELSE 0 END AS 'null', 
//		CASE WHEN ccol='2013-11-10' THEN ddata ELSE 0 END AS '2013-11-10', 
//		CASE WHEN ccol='2014-01-29' THEN ddata ELSE 0 END AS '2014-01-29', 
//		CASE WHEN ccol='2014-03-02' THEN ddata ELSE 0 END AS '2014-03-02'
//	FROM (
//		SELECT USER_LOGON_NAME AS rrow, DATE(create_time) AS ccol, sys_user_ID AS ddata 
//		FROM sys_user 
//		WHERE  1=1  
//		GROUP BY USER_LOGON_NAME 
//	) t
//	LIMIT 10;
		
		String sqlData = "select rrow " + sb.toString() 
				+ " from (" 
				+ " 	select " + rows + " as rrow, " + cols + " as ccol, " + data + " as ddata "
				+ " 	from " + tableName 
				+ " 	where " + sql_where + " group by " + rows + ", " + cols 
				+ " ) t"
				+ " limit 10";
		List<Map<String, String>> dataList = query(sqlData);
		
		for (Map<String, String> map : dataList) {
			row = new ArrayList<String>();
			for (String colName : map.keySet()) {
				row.add(map.get(colName));
			}
			table.add(row);
		}
		JSONObject json = new JSONObject();
		json.put("data", table);
		
		System.out.println(json.toString());
		return json.toString();
	}

	public static void main(String[] args) {
		OlapQueryServlet s = new OlapQueryServlet();
		s.query("select * from a1_c");
		
		String sql = "SELECT t.ordinal_position as id, t.column_name as name, t.data_type as type, " +
				" (case when t.column_key='PRI' then 'true' else 'false' end) as isKey, " +
				" (case when t.column_key='PRI' then 'true' else 'false' end) as isParam " +
				" FROM INFORMATION_SCHEMA.COLUMNS t " +
				" where t.table_schema='blog' and t.table_name='a1_c' order by t.ordinal_position";
		
		s.query(sql);
	}
	
	public List<Map<String, String>> query(String sql) {

		System.out.println();
		System.out.println("------------------   query   --------------------");
		System.out.println("sql : " + sql);
		System.out.println("------------------   data    --------------------");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			List<String> colList = new ArrayList<String>();
			for (int i = 0; i < col; i++) {
				colList.add(rsmd.getColumnLabel(i + 1));
				System.out.print(rsmd.getColumnLabel(i + 1));
				System.out.print("\t");
			}
			System.out.println();

			while (rs.next()) {
				Map<String, String> rowMap = new LinkedHashMap<String, String>();
				for (int i = 0; i < col; i++) {
					rowMap.put(colList.get(i), rs.getString(i + 1));
					System.out.print(rs.getString(i + 1));
					System.out.print("\t");
				}
				System.out.println();
				result.add(rowMap);
			}
			System.out.println();
		} catch (ClassNotFoundException e1) {
			System.out.println("数据库驱动不存在！");
			System.out.println(e1.toString());
		} catch (SQLException e2) {
			System.out.println("数据库存在异常！");
			System.out.println(e2.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			System.out.println("------------------    end    --------------------");
			System.out.println();			
		}

		return result;
	}



}