package com.kelvem.sample.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kelvem.common.base.MessageListenerBaseV3;


public class Sample3Queue extends MessageListenerBaseV3 {
	
	private static Log log = LogFactory.getLog(Sample3Queue.class);

	@Override
	protected void onTextMessage(String msg) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Err", e);
		}
	}
	
}
