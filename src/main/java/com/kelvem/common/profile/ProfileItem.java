package com.kelvem.common.profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileItem {

	private final static String TAB = "\t";
//	private final static String TAB = "  └──";
//	private final static String TAB = "└────";
	
	private ProfileItem parent = null;
	private List<ProfileItem> children = new ArrayList<ProfileItem>();
	
	private int level = 0;
	private String content = "";
	
	private Date startDate = null;
	private Date endDate = null;
	
	public ProfileItem(String content){
		this.content = content;
	}
	
	public String toString(){
	
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		for (int i = 0; i < level; i++) {
			sb.append(TAB);
		}
		sb.append(this.getContent());
		sb.append(" [" + this.getCost() + "ms] ");
		sb.append(this.getLevel());
		for (ProfileItem item : this.getChildren()) {
			sb.append(item);
		}
		return sb.toString();
	}
	
	public long getCost(){
		if (endDate == null || endDate == null) {
			return -1;
		}
		long cost = this.getEndDate().getTime() - this.getStartDate().getTime();
		return cost;
	}
	
	public ProfileItem getParent() {
		return parent;
	}

	public void setParent(ProfileItem parent) {
		this.parent = parent;
	}

	public List<ProfileItem> getChildren() {
		return children;
	}

	public void setChildren(List<ProfileItem> children) {
		this.children = children;
	}

	public void addChild(ProfileItem item) {
		this.children.add(item);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
