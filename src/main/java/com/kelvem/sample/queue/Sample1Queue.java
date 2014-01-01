package com.kelvem.sample.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kelvem.common.base.MessageListenerBaseV1;


public class Sample1Queue extends MessageListenerBaseV1 {
	
	private static Log log = LogFactory.getLog(Sample1Queue.class);

	@Override
	protected void onTextMessage(String msg) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("Err", e);
		}
	}
	
}
