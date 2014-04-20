package com.kelvem.sample.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kelvem.common.base.MessageListenerBaseV4;

public class Sample4Queue extends MessageListenerBaseV4 {
	
	private static Log log = LogFactory.getLog(Sample4Queue.class);

	@Override
	protected void onTextMessage(String msg) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Err", e);
		}
	}
	
}
