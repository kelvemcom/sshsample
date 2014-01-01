package com.kelvem.sample.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.MessageListenerBaseV4;

@Component("sample4QueueService")
public class Sample4QueueService extends MessageListenerBaseV4 {
	
	private static Log log = LogFactory.getLog(Sample4QueueService.class);

	@Override
	protected void onTextMessage(String msg) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Err", e);
		}
	}
	
}
