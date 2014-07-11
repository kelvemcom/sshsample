package com.kelvem.common.base;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

/**
 * <p>MessageListenerBaseV3</p>
 * <p>http://127.0.0.1:8161/admin/</p>
 * <p>default   admin:admin</p>
 * http://127.0.0.1:8161/admin/queues.jsp
 * 
 * <p>使用基础类实现JMS, 不使用Spring等其他增强</p>
 * connectionFactory注入
 * 使用JmsTemplate
 * 
 * <p>Copyright: 版权所有 (c) 2010 - 2014</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName MessageListenerBaseV3
 * @author kelvem
 * @version 1.0
 * 
 */
public abstract class MessageListenerBaseV3 implements MessageListener  {
	
	private static Log log = LogFactory.getLog(MessageListenerBaseV3.class);
  
    public final static String user = ActiveMQConnection.DEFAULT_USER;
    public final static String password = ActiveMQConnection.DEFAULT_PASSWORD;
    public String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
	@Autowired protected JmsTemplate jmsTemplate = null;
  
    private String destinationName = null;
  
    private Destination destination = null;
  
    private Connection connection = null;
  
    private Session session = null;
  
    private MessageConsumer consumer = null;
    
    // 初始化   
    public void initialize() {
    	
   	 	try {
	        
   	 		brokerURL = ((ActiveMQConnectionFactory)jmsTemplate.getConnectionFactory()).getBrokerURL();
   	 		
			// 连接工厂是用户创建连接的对象，这里使用的是ActiveMQ的ActiveMQConnectionFactory根据url，username和password创建连接工厂。
//			connectionFactory = (ActiveMQConnectionFactory)jmsTemplate.getConnectionFactory();
	        
			// 连接工厂创建一个jms connection
			connection = jmsTemplate.getConnectionFactory().createConnection();
	        
			// 启动mq服务
			connection.start();
			
			
			// 是生产和消费的一个单线程上下文。会话用于创建消息的生产者，消费者和消息。会话提供了一个事务性的上下文。
			session = connection.createSession(jmsTemplate.isSessionTransacted(), jmsTemplate.getSessionAcknowledgeMode());
			
			// 目的地是客户用来指定他生产消息的目标还有他消费消息的来源的对象，两种消息传递方式：点对点和发布/订阅
			destination = session.createQueue(destinationName);
	        
			// 会话创建消息的生产者将消息发送到目的地
			consumer = session.createConsumer(destination);
			
			// 开始监听
			consumer.setMessageListener(this);
//			 Message message = consumer.receive();
			   
			log.info("Queue Start Listening..." + this.brokerURL + "/" + this.destinationName);
			
		} catch (Exception e) {
			log.error("Queue Connection Fail : " + this.brokerURL + "/" + this.destinationName, e);
		}
    }
  
	// 关闭连接
	public void close() throws JMSException {
		log.info("Queue Start Closing..." + this.brokerURL + "/" + this.destinationName);
		if (consumer != null)
			consumer.close();
		if (session != null)
			session.close();
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
				// log.info("[" + this.queueName + "]Received Complete: " + msg);
				
				// 提交jms事务
				// session.commit();
				JmsUtils.commitIfNecessary(session);
			} else {
				log.info("[" + this.destinationName + "][" + message.getClass() + "]Skip: " + message);
			}
		} catch (JMSException e) {
			log.error("[" + this.destinationName + "]Received Fail: " + message, e);
		} catch (Exception e) {
			log.error("[" + this.destinationName + "]Received Fail: " + message, e);
		}
	}

	// 消息处理方法
	public void sendMessage(final String msg) {

		log.info("[" + destinationName + "]Send: " + msg);
		jmsTemplate.convertAndSend(destination, msg);
		
//		jmsTemplate.send(destination, new MessageCreator() {
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