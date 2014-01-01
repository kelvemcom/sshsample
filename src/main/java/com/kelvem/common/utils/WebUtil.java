/**
 * 
 */
package com.kelvem.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;



/**
 * @author kelvem
 *
 */
public class WebUtil {

	private WebUtil(){
		// do nothing
	}


//	public static ServletContext getServletContext(){
//		return ServletActionContext.getServletContext();
//	}
//
//	public static WebApplicationContext  getWebApplicationContext(){
//		ServletContext servletContext = getServletContext();
//		return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//	}
//
//	public static Object getBean(String name){
//		WebApplicationContext wac = getWebApplicationContext();
//		return wac.getBean(name);
//	}


//	public static ServletContext getServletContext(){
//		return ServletActionContext.getServletContext();
//	}
//
//	public static WebApplicationContext  getWebApplicationContext(){
//		ServletContext servletContext = getServletContext();
//		return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//	}

	public static Object getBean(String beanName){
		return getApplicationContext().getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}

	private static ApplicationContext ctx = null;
	public static ApplicationContext getApplicationContext(){
	
		if (ctx == null) {
			ctx = ContextLoader.getCurrentWebApplicationContext();
		}
		return ctx;
	}
	
//	public static String getBaseUrl(ServletRequest servletRequest){
//		
//		System.out.println(ObjectUtil.toString(servletRequest));
//		return "";
//	}
}
