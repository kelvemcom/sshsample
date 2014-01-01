/**============================================================
   * 版权： kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.aop.MethodCacheAdvice.java
 * 所含类: MethodCacheAdvice
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-21       kelvem       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.aop;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * <p>MethodCacheAdvice</p>
 *
 * <p>类用途详细说明</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName MethodCacheAdvice
 * @author kelvem
 * @version 1.0
 */
public class MethodCacheAdvice {
	
	private static Log log = LogFactory.getLog(MethodCacheAdvice.class);

	@Autowired
	private Cache methodCache;

	public void setMethodCache(Cache methodCache) {
		this.methodCache = methodCache;
	}

	/** 
     *  
     */
	public MethodCacheAdvice() {
		super();
	}

	/**
	 * <p>主方法 如果某方法可被缓存就缓存其结果</p> 
	 * 方法结果必须是可序列化的(serializable)
	 * 
	 * @param MethodInvocation invocation
	 * @return Object 运行结果
	 * @see
	 */
	public Object putCache(ProceedingJoinPoint joinPoint) throws Throwable {
		String targetName = joinPoint.getThis().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Object result;

		log.debug("在缓存中查找方法返回的对象!");
		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Element element = methodCache.get(cacheKey);
		if (element == null) {
			log.debug("正在拦截方法!");
			result = joinPoint.proceed();

			log.debug("正在缓存对象!");
			element = new Element(cacheKey, result);
			methodCache.put(element);
		}
		return element.getObjectValue();
	}

	/**
	 * <p>创建一个缓存对象的标识: targetName.methodName.argument0.argument1... </p> 
	 * 
	 * @param String targetName
	 * @param String methodName
	 * @param Object[] arguments
	 * @return String CacheKey
	 * @see
	 */
	private String getCacheKey(String targetName, String methodName, Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}

		return sb.toString();
	}

	/*
	 * （非 Javadoc）
	 * @see org.springframework.beans.factory.InitializingBeanafterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(methodCache, "需要一个缓存. 使用setCache(Cache)分配一个.");

	}
}
