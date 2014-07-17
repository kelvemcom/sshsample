package com.kelvem.common.profile;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 记录方法运行时信息的AOP
 * </p>
 * 
 * @author kelvem
 * 
 */
@Component("profileSpringAop")
public class ProfileSpringAop implements MethodInterceptor {

    
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Object objTarget = invocation.getThis();
		Class<?> clazzTarget = objTarget.getClass();
		
		ProfileContext.push(clazzTarget.getSimpleName() + "." + invocation.getMethod().getName());
		Object obj = invocation.proceed();
		ProfileContext.pop();
		
		return obj;
	}
	
	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {

		/*
		 * 判断需要忽略的函数
		 */
//		boolean isIgnore = this.isIgnoreMethod(joinPoint);
//		if (isIgnore == true) {
//			return joinPoint.proceed();
//		}

		/*
		 * 是否包含传递空的参数的方法
		 */
		boolean isNullParam = false;
		
		/*
		 * 初始化
		 */
		Object objTarget = joinPoint.getTarget();
		Class<?> clazzTarget = objTarget.getClass();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		//String methodLongName = signature.toLongString();

		/*
		 * 生成方法运行时信息
		 * Cost:10ms V3ConfigService.getQueueIsOpenOrder([String]aaa, [int]1) Return:[boolean]true [含有为Null的参数]
		 */
		StringBuilder sb = new StringBuilder();
		sb.append(clazzTarget.getSimpleName());
		sb.append(".");
		sb.append(methodName);

		/*
		 * 输出方法运行时信息
		 */
		if (isNullParam == true){
			sb.append("#含有为Null的参数");
		}
		
		ProfileContext.push(sb.toString().replaceAll("\n", " "));
		// 运行方法
		Object result = joinPoint.proceed();
		ProfileContext.pop();
		
		return result;
	}

}
