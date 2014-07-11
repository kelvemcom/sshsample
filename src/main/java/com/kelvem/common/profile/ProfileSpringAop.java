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
public class ProfileSpringAop implements MethodInterceptor {

    
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Object objTarget = invocation.getThis();
		Class<?> clazzTarget = objTarget.getClass();
		
		ProfileContext.push(clazzTarget.getSimpleName() + "." + invocation.getMethod().getName());
		Object obj = invocation.proceed();
		ProfileContext.pop();
		
		return obj;
	}
	
}
