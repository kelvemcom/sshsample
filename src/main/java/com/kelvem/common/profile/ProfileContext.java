package com.kelvem.common.profile;

import java.util.Date;

public class ProfileContext {

	private static ThreadLocal<ProfileItem> context = new ThreadLocal<ProfileItem>();

	static {
		ProfileItem root = new ProfileItem();
		root.setContent("root");
		context.set(root);
	}
	
	public static void push(ProfileItem item){

		ProfileItem current = context.get();

		item.setStartDate(new Date());
		
		if (current != null) {
			item.setLevel(current.getLevel() + 1);
			current.addChild(item);
			item.setParent(current);
		}
		context.set(item);
	}
	
	public static ProfileItem pop(){
		ProfileItem item = context.get();
		item.setEndDate(new Date());
		
		ProfileItem parent = item.getParent();
		context.set(parent);
		
		return item;
	}

	public static ProfileItem get() {
		return context.get();
	}
	
	
	
}
