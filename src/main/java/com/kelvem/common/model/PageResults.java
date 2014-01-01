package com.kelvem.common.model;

import java.io.Serializable;
import java.util.ArrayList;
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
public class PageResults<T extends Serializable>  implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6387086497638925166L;
	
	/**
	 * 当前页数据结果集
	 */
	private List<T> results = new ArrayList<T>();
	
	/**
	 * 总数
	 */
	private int totalCount = 0;
	/**
	 * 一页大小
	 */
	private int pageSize = 10;
	/**
	 * 当前页号
	 */
	private int currentPage = 1;
	
	/**
	 * 排序
	 */
	private String orderBy;
	/**
	 * 是否升序
	 */
	private boolean isASC;
    
    public boolean isASC() {
		return isASC;
	}

	public void setASC(boolean isASC) {
		this.isASC = isASC;
	}

    public PageResults() {
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        
        return (this.totalCount+this.pageSize-1)/this.pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
//    	if(currentPage<=0) currentPage=1;
    	if(currentPage<=0) currentPage=0;
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public int getResultsFrom(){
		return (currentPage)*pageSize;
	}
	
	public int getResultsEnd(){
		return pageSize*currentPage;
		
	}
    
}
