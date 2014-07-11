package com.kelvem.common.profile;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProfileContext {
	
	private static Log log = LogFactory.getLog(ProfileContext.class);

	private static ThreadLocal<ProfileItem> context = new ThreadLocal<ProfileItem>();
	
	public static void push(String content){

		log.debug("[" + Thread.currentThread().getName() + "] push " + content);
		
		ProfileItem item = new ProfileItem(content);
		item.setStartDate(new Date());
		
		ProfileItem current = context.get();
		
		if (current != null) {
			item.setLevel(current.getLevel() + 1);
			current.addChild(item);
			item.setParent(current);
		}
		
		context.set(item);
	}
	
	public static ProfileItem pop(){
		
		
		ProfileItem item = context.get();
		if (item == null) {
			log.error("[ProfileItem pop()]不应为空");
			return null;
		}
		log.debug("[" + Thread.currentThread().getName() + "] pop " + item.getContent() + " " + item.getLevel());
		
		item.setEndDate(new Date());
		
		ProfileItem parent = item.getParent();
		context.set(parent);
		
		if (item.getParent() == null) {
			if (item.getContent().endsWith(".css") || item.getContent().endsWith(".js") || item.getContent().endsWith(".eot")) {
				// donothing
			} else {
				// 输出profile
				log.info(item.toString());
			}
		}
		
		return item;
	}

	public static ProfileItem get() {
		return context.get();
	}
	
	
	
}
