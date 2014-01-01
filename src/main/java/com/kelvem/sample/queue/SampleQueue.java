package com.kelvem.sample.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.base.MessageListenerBase;


public class SampleQueue extends MessageListenerBase {
	
	private static Log log = LogFactory.getLog(SampleQueue.class);

	@Autowired Sample1Queue sample2Queue;
	@Autowired Sample2Queue sample3Queue;

	@Override
	protected void onTextMessage(String msg) {

		log.info("SampleQueue Recieve ： " + msg);
//		sample2Queue.sendTextMessage(msg);
//		sample3Queue.sendTextMessage(msg);
		throw new RuntimeException("MQ Err");
	}
}
