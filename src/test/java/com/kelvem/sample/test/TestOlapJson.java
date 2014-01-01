package com.kelvem.sample.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestOlapJson {

	/**
	 * <p>方法描述</p>
	 * 
	 * @param args void
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void main(String[] args) {

		JSONObject json = new JSONObject();
		
		//item  ### id name type[row col data] key param
		
		//param ### id name type[exp><=in] value
		
		JSONArray arr = new JSONArray();
		ArrayList<Item> items = new ArrayList<Item>();
		
		HashMap m = new HashMap();
		m.put("id", "1");
		m.put("name", "name1");
		arr.add(m);
		
		HashMap m2 = new HashMap();
		m2.put("id", "2");
		m2.put("name", "name2");
		arr.add(m2);
		
		json.put("items", arr);
		json.put("items1", arr);
		
//		ArrayList<Item> items = new ArrayList<Item>();
//		Item item = new Item();
//		item.id="1";
//		item.name="Item1";
//		item.type="row";
//		item.isKey=true;
//		item.isParam=true;
//		items.add(item);
//		JSONObject i = JSONObject.fromObject(item);
//		arr.add(i);
//		
//		item = new Item();
//		item.id="2";
//		item.name="Item2";
//		items.add(item);
//		
//		item = new Item();
//		item.id="3";
//		item.name="Item3";
//		items.add(item);
//		
//		item = new Item();
//		item.id="4";
//		item.name="Item4";
//		items.add(item);
		
//		JSONArray arr = new JSONArray();
//		arr.
//		json.put("items", arr);
	
		System.out.println(json);
	}
}

class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3048267290814382470L;
	
	public String id;
	public String name;
	public String type;
	public boolean isKey = false;
	public boolean isParam = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getIsKey() {
		return isKey;
	}
	public void setIsKey(boolean isKey) {
		this.isKey = isKey;
	}
	public boolean getIsParam() {
		return isParam;
	}
	public void setIsParam(boolean isParam) {
		this.isParam = isParam;
	}
}

class param {
	String id;
	String name;
	String type;
	String value;
}
