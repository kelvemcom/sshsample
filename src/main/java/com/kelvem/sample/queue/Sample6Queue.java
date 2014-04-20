package com.kelvem.sample.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kelvem.common.base.MessageListenerBaseV6;

public class Sample6Queue extends MessageListenerBaseV6 {
	
	private static Log log = LogFactory.getLog(Sample6Queue.class);

	@Override
	protected void onTextMessage(String msg) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Err", e);
		}
	}
	
}
