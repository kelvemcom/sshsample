package com.kelvem.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>PageResults</p>
 *
 * <p>DAO层分页组件类</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName PageResults
 * @author kelvem
 * @version 1.0
 */
public class DataTableResults<T extends Serializable>  implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6387086497638925166L;
	
	
	/**
	 * 总数
	 */
	private long iTotalRecords = 100;
	/**
	 * 一页大小
	 */
	private long iTotalDisplayRecords = 100;
	/**
	 * 当前页号
	 */
	private String sEcho = "AAA";

	private long iDisplayStart = 4;
	
//	private String sSearch;
//	private String sSearch_0;
//	private String sSearch_1;
//	private String sSearch_2;
//	private String sSearch_3;
//	private String sSearch_4;
//	private String sSortDir_0;
	
//	private List<String> sSortDir = new ArrayList<String>();
	

//	private Boolean bServerSide = true;
//	private String sPaginationType = "bootstrap";
//	private String oLanguage = "{\"sLengthMenu\": \" Per Page  _MENU_\"}";
	
	/**
	 * 当前页数据结果集
	 */
	private List<T> aaData = null;
	
	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

//	private String[][] aaData = null;
//
//	public String[][] getAaData() {
//		return aaData;
//	}
//
//	public void setAaData(String[][] aaData) {
//		this.aaData = aaData;
//	}
	
    public DataTableResults() {
    }
    
    public DataTableResults(PageResults<T> result) {
    	
    	aaData = result.getResults();
    	
//    	this.aaData = new String[10][5];
//    	
//    	for (int i = 0; i < 10; i++) {
//			
//			this.aaData[i] = new String[5];
//			for (int j = 0; j < 5; j++) {
//				this.aaData[i][j] = "A_"+i+"_"+j; 
//			}
//		}
    	
//    	this.iTotalDisplayRecords = result.getPageSize();
//    	this.iTotalRecords = result.getTotalCount();
//    	this.sEcho = "" + result.getCurrentPage();
    	
//    	sSortDir.add("asc");
//    	sSortDir.add("asc");
//    	sSortDir.add("asc");
//    	sSortDir.add("asc");
    }


	public long getiTotalRecords() {
		return iTotalRecords;
	}


	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}


	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}


	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}


	public String getsEcho() {
		return sEcho;
	}


	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public long getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(long iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
    
    
}
