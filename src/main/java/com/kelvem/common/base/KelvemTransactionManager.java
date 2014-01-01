package com.kelvem.common.base;

import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class KelvemTransactionManager extends HibernateTransactionManager {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8903723909528007575L;
	
	/**
	 * Log4J Logger for this class
	 */
//	private static Log logger = LogFactory.getLog(KelvemTransactionManager.class);
	private static long count = 0;
	
	public KelvemTransactionManager() {
//		count++;
//		logger.info("KelvemTransactionManager : " + count);
	}
	
	@Override
	protected Object doGetTransaction() {
//		count++;
//		logger.info("[Transaction]doGetTransaction : " + count);
		return super.doGetTransaction();
	}
	
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
//		count++;
//		logger.info("[Transaction]doBegin : " + count);
		super.doBegin(transaction, definition);
	}

	@Override
	protected Object doSuspend(Object transaction) {
//		logger.info("[Transaction]doSuspend : " + count);
		return super.doSuspend(transaction);
	}
	
	@Override
	protected void doResume(Object transaction, Object suspendedResources) {
//		logger.info("[Transaction]doResume : " + count);
		super.doResume(transaction, suspendedResources);
	}
	
	@Override
	protected void doCommit(DefaultTransactionStatus status) {
//		count++;
//		logger.info("[Transaction]doCommit : " + count);
		super.doCommit(status);
	}
	
	@Override
	protected void doRollback(DefaultTransactionStatus status) {
//		logger.info("[Transaction]doRollback : " + count);
		super.doRollback(status);
	}

	public static long getCount() {
		return count;
	}

	public static void setCount(long count) {
		KelvemTransactionManager.count = count;
	}
	
	
}
