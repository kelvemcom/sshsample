package com.kelvem.common.profile;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * <p>
 * 记录方法运行时信息的AOP
 * </p>
 * 
 * @author kelvem
 * 
 */
public class ProfileSpringInterceptor implements MethodInterceptor {

    
	public Object invoke(MethodInvocation invocation) throws Throwable {

		ProfileItem item = new ProfileItem();
		item.setContent(invocation.getMethod().toString());
		
		ProfileContext.push(item);
		Object obj = invocation.proceed();
		ProfileContext.pop();
		
		return obj;
	}
	
}
