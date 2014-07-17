package com.kelvem.common.profile;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.TimerInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * <p>
 * 记录方法运行时信息的AOP
 * </p>
 * 
 * @author kelvem
 * 
 */
public class ProfileStrutsInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = -8007042975835100109L;
	protected static final Logger LOG = LoggerFactory.getLogger(TimerInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		String log = this.getLog(invocation);
		
		ProfileContext.push("ProfileStrutsInterceptor");
		String result = invocation.invoke();
		ProfileItem pop = ProfileContext.pop();
		pop.setContent(log + " result:" + result);
		
		return result;
	}

	/**
	 * Is called to invoke the action invocation and time the execution time.
	 * 
	 * @param invocation
	 *            the action invocation.
	 * @return the result of the action execution.
	 * @throws Exception
	 *             can be thrown from the action.
	 */
	protected String getLog(ActionInvocation invocation)
			throws Exception {
		
		StringBuilder message = new StringBuilder(100);
		message.append("Executed action [");
		String namespace = invocation.getProxy().getNamespace();
		if ((namespace != null) && (namespace.trim().length() > 0)) {
			message.append(namespace).append("/");
		}
		message.append(invocation.getProxy().getActionName());
		message.append("!");
		message.append(invocation.getProxy().getMethod());
		message.append("]");

		return message.toString();
	}

}
