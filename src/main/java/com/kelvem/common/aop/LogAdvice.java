package com.kelvem.common.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.kelvem.common.annotation.MethodDetail;
import com.kelvem.common.utils.DateUtils;

/**
 * <p>
 * 记录方法运行时信息的AOP
 * </p>
 * 
 * @author kelvem
 * 
 */
@Component("logAdvice")
public class LogAdvice {

	private static Log logger = LogFactory.getLog(LogAdvice.class);
	
	private long warningExecuteTime = 500;

	private String logLevel = "info";

	/**
	 * 记录方法运行时信息 该方法会有大约30ms的损耗，建议只用于调试
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	public Object writeLog(ProceedingJoinPoint joinPoint) throws Throwable {

		// 如果不输出Log，则忽略，提高效率
		if (!logger.isInfoEnabled()){
			return joinPoint.proceed();
		}
		// Object objThis = joinPoint.getThis();
		// Class<?> clazzThis = objThis.getClass();
		 Object objTarget = joinPoint.getTarget();
		 Class<?> clazzTarget = objTarget.getClass();
		// String king = joinPoint.getKind();
		// SourceLocation sourceLocation = joinPoint.getSourceLocation();
		// StaticPart staticPart = joinPoint.getStaticPart();

		// String methodShortName = signature.toShortString();
		// String className =
		// joinPoint.getSignature().getDeclaringType().toString();
		// String thisClassName = joinPoint.getThis().toString();
		// String targetClassName = joinPoint.getThis().getClass().toString();
		 
		/*
		 * 初始化
		 */
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String methodLongName = signature.toLongString();
		Object[] args = joinPoint.getArgs();

		// 获取当前的方法对象
		Method method = this.getMethod(joinPoint);
		
		// 生成方法运行时uuid,便于区分多次调用
		// String uuid = UUID.randomUUID().toString().substring(0, 8);
		// String uuid = Integer.toHexString(new
		// Random().nextInt(256*256*256)).toUpperCase();

		// 每行Log的前缀
		// String prefix = "  #" + uuid + "# ";
		String prefix = "  @ ";

		/*
		 * 判断需要忽略的函数
		 */
		boolean isIgnore = this.isIgnoreMethod(joinPoint);

		/*
		 * 判断是否使用了注解MethodDetail
		 */
		boolean isUsedAnnotation = this.isUsedAnnotation(method);

		// 不记录（未使用注解&&（get、set方法||构造函数)）
		if (isUsedAnnotation == false && isIgnore == true) {
			return joinPoint.proceed();
		}

		/*
		 * 是否包含传递空的参数的方法
		 */
		boolean isNullParam = false;

		/*
		 * 是否包含运行时间过长的方法
		 */
		boolean isLongExecTime = false;

		/*
		 * 生成方法运行时信息
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n================ " + clazzTarget.getSimpleName() + "." + methodName + "()  Start" + prefix + " ================\r\n");
		sb.append("*\r\n");

		// 如果包含MethodDetail注解，则输出描述信息
		if (isUsedAnnotation) {
			String description = this.getDescription(method);
			sb.append(prefix + "Desc      : " + description + "\r\n");
		}

		sb.append(prefix + "Method    : " + methodLongName + "\r\n");
		sb.append(prefix + "StartTime : " + DateUtils.getDateTimeString(DateUtils.now()) + "\r\n");
		sb.append(prefix + "ArgsSize  : " + args.length + "\r\n");

		Class<?> argTypes[] = method.getParameterTypes();
		for (int i = 0; i < args.length; i++) {

			sb.append(prefix + "Args" + i + "     : " + argTypes[i].getSimpleName() + " = " + args[i] + "\r\n");

			if (args[i] == null) {
				// Warning
				isNullParam = true;
				sb.append(prefix + "Args" + i + "     : 参数为Null，请确认" + "\r\n");
			}
		}

		sb.append(prefix + "Run ...\r\n");
		long beginTime = System.currentTimeMillis();

		// 运行方法
		Object result = joinPoint.proceed();

		long endTime = System.currentTimeMillis();
		long execTime = endTime - beginTime;

		sb.append(prefix + "ExecTime  : " + execTime + "ms" + "\r\n");

		if (execTime >= warningExecuteTime) {
			// Warning
			isLongExecTime = true;
			sb.append(prefix + "ExecTime  : 运行了" + execTime + "ms，超过运行时间阀值" + warningExecuteTime + "ms，请确认是否正常\r\n");
		}

		sb.append(prefix + "EndTime   : " + DateUtils.getDateTimeString(DateUtils.now()) + "\r\n");
		if (method.getReturnType().getSimpleName().equals("void")) {
			sb.append(prefix + "Return    : void" + "\r\n");
		} else {
			sb.append(prefix + "Return    : " + method.getReturnType().getSimpleName() + " = " + result + "\r\n");
		}

		sb.append("*\r\n");
		sb.append("================ " + clazzTarget.getSimpleName() + "." + methodName + "()   End " + prefix + " ================\r\n\r\n");

		/*
		 * 输出方法运行时信息
		 */
		if (isIgnore == false){
			if (this.getLogLevel().equals("info") || isUsedAnnotation == true || isLongExecTime == true || isNullParam == true) {
				logger.info(sb.toString());
			}
		}
		
		return result;
	}

	private Method getMethod(ProceedingJoinPoint joinPoint) {

		Method methodInterface = ((MethodSignature)joinPoint.getSignature()).getMethod();
		
		// 获取当前的方法对象
		Method method = null;
		try {
			method = joinPoint.getTarget().getClass().getMethod(methodInterface.getName(), methodInterface.getParameterTypes());
		} catch (Exception e) {			
		}
		
		// 理论上不会调用到
		if (method == null) {
			Assert.isTrue(true, "[Error] LogAdvice执行时，未找到对应的方法，请分析原因。" + joinPoint.getSignature().toLongString());
		}

		return method;
	}

	private boolean isIgnoreMethod(ProceedingJoinPoint joinPoint) {

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
			
			try {
				String buf = methodName.substring(3, methodName.length());
				String fieldName = buf.substring(0, 1).toLowerCase() + buf.substring(1);
				clazzTarget.getDeclaredField(fieldName);

				return true;
			} catch (Exception e) {
				// 不包含该Field
			}
		}

		return false;
	}

	private boolean isUsedAnnotation(Method method) {
		
		boolean flag = method.isAnnotationPresent(MethodDetail.class);
			
		return flag;
	}

	private String getDescription(Method method) {

		boolean flag = isUsedAnnotation(method);
		if (flag == true) {
			
			MethodDetail methodDetail = (MethodDetail) method.getAnnotation(MethodDetail.class);			
			String description = methodDetail.description();

			return description;
		} else {
			return null;
		}
	}

	public long getWarningExecuteTime() {
		return warningExecuteTime;
	}

	public void setWarningExecuteTime(long warningExecuteTime) {
		this.warningExecuteTime = warningExecuteTime;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {

		Assert.isTrue(logLevel != null, "<bean>LogAdvice's <property>logLevel's value can't be null");

		logLevel = logLevel.toLowerCase();
		Assert.isTrue(logLevel.equals("info") || logLevel.equals("debug"), "<bean>LogAdvice' <property>logLevels value only can be 'info' or 'debug'");

		this.logLevel = logLevel;
	}

}
