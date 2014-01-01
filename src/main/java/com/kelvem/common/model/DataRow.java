/**============================================================
 * 版权： kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.model.DataRow.java
 * 所含类: DataRow
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-7-5       kelvem       创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.common.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>DataRow</p>
 *
 * <p>类用途详细说明</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName DataRow
 * @author kelvem
 * @version 1.0
 */

public class DataRow {  
	//定义该行记录在table所处的行数   
	private int rowIndex = -1;
	private List<DataColumn> columns;   
	//table的一个引用   
	private DataTable table;   
	//用于存储数据的Map对象，这里保存的对象不包括顺序信息，数据获取的索引通过行信息标识  
	private Map<String, Object> itemMap = new LinkedHashMap<String, Object>();  
	 
	public DataRow() {  
	 
	}  
	 
	public DataRow(DataTable table) {  
	this.table = table;  
	}  
	 
	/**
	 * 功能描述：  获取当前行的行索引  
	 * @param  
	 * @return: int
	 */  
	public int getRowIndex() {  
	return rowIndex;  
	}  
	 
	/**
	 * 功能描述：  获取当前行所属数据表对象  
	 * @param  
	 * @return: DataTable
	 */  
	public DataTable getTable() {  
	return this.table;  
	}  
	 
	/**
	 * @param columns  
	 */  
	public void setColumns(List<DataColumn> columns) {  
	this.columns = columns;  
	}  
	 
	/**
	 * @return  the columns 
	*/  
	public List<DataColumn> getColumns() {  
	return columns;  
	}  
	 
	public void setValue(int index,  
	  Object value) {  
	setValue(this.columns.get(index), value);  
	}  
	 
//	public void setValue(String columnName, Object value) {  
//		setValue(this.columns..get(columnName), value);
//		
//	}  
	 
	public void setValue(DataColumn column,  
	  Object value) {  
	if (column != null) {  
	  String lowerColumnName = column.getColumnName().toLowerCase();  
	  if (getItemMap().containsKey(lowerColumnName))  
	getItemMap().remove(lowerColumnName);  
	  getItemMap().put(lowerColumnName, column.convertTo(value));  
	}  
	}  
	 
	public Object getValue(int index) {  
	String colName = this.columns.get(index).getColumnName().toLowerCase();  
	return this.getItemMap().get(colName);  
	}  
	 
	public Object getValue(String columnName) {  
	return this.getItemMap().get(columnName.toLowerCase());  
	}  
	 
	/**
	 * @return  the itemMap 
	*/  
	public Map<String, Object> getItemMap() {  
	return itemMap;  
	}  
	 
	/**
	 * @param rowIndex  
	 */  
	public void setRowIndex(int rowIndex) {  
	this.rowIndex = rowIndex;  
	}  
	 
	public void copyFrom(DataRow row) {  
	this.itemMap.clear();//首先请客当前记录  
	for (Object c : this.columns) {  
	  this.itemMap.put(c.toString().toLowerCase(), row.getValue(c.toString())); 
	} 
	} 

}
