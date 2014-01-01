package com.kelvem.common.cache;

import java.util.Properties;
import java.util.Set;

import com.kelvem.common.utils.PropertiesUtil;

public class PropertiesCache {

	
	public static void getJmsProperty(){
		
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		
		Properties properties = PropertiesUtil.readProperties("D:/Project/MyEclipse10/Workspaces/sshstudy/src/main/resources/jms.properties");
		
		Set<Object> set = properties.keySet();
		
		for (Object object : set) {
			System.out.println(object);
		}
		
		
	}
	
	public static void main(String[] args) {
		getJmsProperty();
	}


}
