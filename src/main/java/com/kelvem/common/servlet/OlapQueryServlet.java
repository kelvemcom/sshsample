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
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

// http://kelvem-pc2:8080/SSH2Sample/SomeServlet/query
// http://kelvem-pc2:8080/SSH2Sample/SomeServlet/init?table=a1_c
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
    public String schema = "products_center_v1";
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
				" (case when t.column_key='PRI' then 'col' else '' end) as type, " +
				" (case when t.column_key='PRI' then 'true' else 'false' end) as isKey, " +
				" (case when t.column_key='PRI' then 'true' else 'false' end) as isParam " +
				" from INFORMATION_SCHEMA.COLUMNS t " +
				" where t.table_schema='" + schema + "' and t.table_name='" + tableName + "' order by t.ordinal_position";
		
		List<Map<String, String>> result = query(sql);
		
		JSONObject json = new JSONObject();
		json.put("items", result);
		
		System.out.println(json.toString());
		return json.toString();
		
//		return "{'items':[{'id':'101','name':'name1','type':'col','isKey':'true','isParam':'true'}," +
//				"{'id':'102','name':'name2','type':'row','isKey':'false','isParam':'true'}]," +
//				"'params':[{'id':'101','name':'name1','type':'1','value':'1'}," +
//				"{'id':'102','name':'name2','type':'2','value':'2'}]}";
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
		System.out.println(sqlData);
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
         
         Connection con = null;
         Statement stmt = null;
         ResultSet rs = null;
         List<Map<String, String>> result = new ArrayList<Map<String, String>>();
         try{
             Class.forName(driver);
             con = DriverManager.getConnection(url, user, password);
             stmt = con.createStatement();
             rs = stmt.executeQuery(sql);
             
             ResultSetMetaData rsmd = rs.getMetaData();
             int col = rsmd.getColumnCount();
             List<String> colList = new ArrayList<String>();
             for(int i = 0; i<col; i++)
             {
            	 colList.add(rsmd.getColumnLabel(i+1));
                 System.out.print(rsmd.getColumnLabel(i+1));
                 System.out.print("\t");
             }
             System.out.println();
             
             while(rs.next())
             {
            	 Map<String, String> rowMap = new HashMap<String, String>();
                 for(int i=0;i<col;i++)
                 {
                	 rowMap.put(colList.get(i), rs.getString(i+1));
                     System.out.print(rs.getString(i+1));
                     System.out.print("\t");
                 }
                 System.out.println();
                 result.add(rowMap);
             }
             System.out.println();
         }
         catch(ClassNotFoundException e1)
         {
             System.out.println("数据库驱动不存在！");
             System.out.println(e1.toString());
         }
         catch(SQLException e2)
         {
             System.out.println("数据库存在异常！");
             System.out.println(e2.toString());
         }
         finally
         {
             try
             {
                 if(rs != null) rs.close();
                 if(stmt != null) stmt.close();
                 if(con != null) con.close();
             }
             catch(SQLException e)
             {
                 System.out.println(e.toString());
             }            
         }
         
         return result;
     }



}