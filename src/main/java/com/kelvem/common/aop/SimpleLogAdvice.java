package com.kelvem.common.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p>
 * 记录方法运行时信息的AOP
 * </p>
 * 
 * @author kelvem
 * 
 */
public class SimpleLogAdvice {

	private static Log logger = LogFactory.getLog(SimpleLogAdvice.class);
	
	/**
	 * 记录方法运行时信息 该方法会有大约30ms的损耗，建议只用于调试
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	public Object writeLog(ProceedingJoinPoint joinPoint) throws Throwable {

		if (this.isIgnoreMethod(joinPoint) == true){
			return joinPoint.proceed();
		}
		
		String methodName = joinPoint.getSignature().toLongString();

		/*
		 * 输出方法运行时信息
		 */
		logger.info(methodName);
		
//		long beginTime = System.currentTimeMillis();

		// 运行方法
		Object result = joinPoint.proceed();

//		long endTime = System.currentTimeMillis();
//		long execTime = endTime - beginTime;
		
		return result;
	}

	private boolean isIgnoreMethod(ProceedingJoinPoint joinPoint) {

		try {
			/*
			 * 判断需要忽略的函数
			 */
			String methodName = joinPoint.getSignature().getName();
			Class<?> clazzTarget = joinPoint.getTarget().getClass();
	
			if (methodName.equals(joinPoint.getTarget().getClass().getName())) { // 构造函数
	
				return true;
			}
			
			if (methodName.startsWith("get") || methodName.startsWith("set")) { // Get Set方法
	
				boolean isExist = false;
				for(Method method : joinPoint.getTarget().getClass().getDeclaredMethods()){				
					if(method.getName().equals(methodName)){
						isExist = true;
						break;
					}
				}
				
				if(isExist == false){
					return true;
				}
				
				String buf = methodName.substring(3, methodName.length());
				String fieldName = buf.substring(0, 1).toLowerCase() + buf.substring(1);
				clazzTarget.getDeclaredField(fieldName);
	
				return true;
			}
		} catch (Exception e) {
			// 不包含该Field
		}

		return false;
	}
	
	
}
