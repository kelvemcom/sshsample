package com.kelvem.sample.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kelvem.common.base.MessageListenerBaseV5;

public class Sample5Queue extends MessageListenerBaseV5 {
	
	private static Log log = LogFactory.getLog(Sample5Queue.class);

	@Override
	protected void onTextMessage(String msg) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Err", e);
		}
	}
	
}
