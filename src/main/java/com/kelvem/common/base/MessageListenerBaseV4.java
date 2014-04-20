package com.kelvem.common.base;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>MessageListenerBaseV3</p>
 * <p>http://127.0.0.1:8161/admin/</p>
 * <p>default   admin:admin</p>
 * http://127.0.0.1:8161/admin/queues.jsp
 * 
 * <p>使用基础类实现JMS, 不使用Spring等其他增强</p>
 * connectionFactory注入
 * 使用JmsTemplate
 * 里程碑
 * 
 * <p>Copyright: 版权所有 (c) 2010 - 2014</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName MessageListenerBaseV3
 * @author kelvem
 * @version 1.0
 * 
 */
public abstract class MessageListenerBaseV4 implements MessageListener {

	private static Log log = LogFactory.getLog(MessageListenerBase.class);

	@Autowired protected JmsTemplate jmsTemplate = null;

	private ConnectionFactory connectionFactory = null;

	private String destinationName = null;

	private boolean sessionTransacted = true;

	private Connection connection = null;

	private Session sessionProducer = null;
	private Session sessionConsumer = null;

	private Destination destinationProducer = null;
	private Destination destinationConsumer = null;

	private MessageConsumer consumer = null;

	// 初始化
	protected void initialize() throws JMSException, Exception {

		try {
			connectionFactory = jmsTemplate.getConnectionFactory();
			connection = connectionFactory.createConnection();
			connection.start();

			sessionProducer = connection.createSession(sessionTransacted, Session.AUTO_ACKNOWLEDGE);
			sessionConsumer = connection.createSession(sessionTransacted, Session.AUTO_ACKNOWLEDGE);

			destinationProducer = sessionProducer.createQueue(destinationName);
			destinationConsumer = sessionConsumer.createQueue(destinationName);

			jmsTemplate.setDefaultDestination(destinationProducer);

			log.info("Queue Start Listening..." + this.destinationName);
			// 开始监听
			consumer = sessionConsumer.createConsumer(destinationConsumer);
			consumer.setMessageListener(this);

		} catch (Exception e) {
			log.error("Queue Connection Fail : " + this.destinationName, e);
		}
	}

	// 关闭连接
	public void close() throws JMSException {
		log.info("Queue Start Closing..." + this.destinationName);
		if (consumer != null)
			consumer.close();
		if (sessionProducer != null)
			sessionProducer.close();
		if (sessionConsumer != null)
			sessionConsumer.close();
		if (connection != null)
			connection.close();
	}

	// 消息处理方法
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) message;
				String msg = txtMsg.getText();

				log.info("[" + this.destinationName + "]Received: " + msg);
				// 调用子类方法处理文本消息
				onTextMessage(msg);

				sessionConsumer.commit();
//				throw new RuntimeException("Err");
			} else {
				log.info("[" + this.destinationName + "][" + message.getClass() + "]Skip: " + message);
			}
		} catch (JMSException e) {
			log.error("[" + this.destinationName + "]Received Fail: " + message, e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			log.error("[" + this.destinationName + "]Received Fail: " + message, e);
			throw new RuntimeException(e);
		}
	}

	@Transactional
	public void sendMessage(final String msg) {

		log.info("[" + destinationName + "]Send: " + msg);
		jmsTemplate.convertAndSend(this.destinationProducer, msg);
		
//		jmsTemplate.send(this.destinationProducer, new MessageCreator() {
//	        public Message createMessage(Session session) throws JMSException {
//	        	TextMessage message = session.createTextMessage(msg);
//    			log.info("[" + destinationName + "]Send: " + msg);
//	    		return message;
//	        }
//	    });
	}

	// 消息处理文本方法
	protected abstract void onTextMessage(String msg);

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
}