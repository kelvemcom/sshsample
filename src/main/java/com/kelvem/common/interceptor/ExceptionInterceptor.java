/**============================================================
   * 版权： kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.ExceptionInterceptor.java
 * 所含类: ExceptionInterceptor
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-6-14       kelvem       创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.common.interceptor;

import org.apache.log4j.Logger;

import com.kelvem.common.utils.ExceptionUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * <p>ExceptionInterceptor</p>
 *
 * <p>类用途详细说明</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName ExceptionInterceptor
 * @author kelvem
 * @version 1.0
 */

public class ExceptionInterceptor extends AbstractInterceptor {
	
	private static final Logger log = Logger.getLogger(ExceptionInterceptor.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 638190881274469503L;
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String result = "";
		try {
			result = invocation.invoke();
		}catch(Exception ex){
			log.error("异常信息" + ex);
			ex = ExceptionUtil.getException(ex);
			throw ex;
		}
		return result;
	}
}
