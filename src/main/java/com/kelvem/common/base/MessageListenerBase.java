package com.kelvem.common.base;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
  
public abstract class MessageListenerBase /*extends DefaultMessageListenerContainer*/ implements MessageListener {
	
	private static Log log = LogFactory.getLog(MessageListenerBase.class);
  
//    private String user = ActiveMQConnection.DEFAULT_USER;
//    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
//    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    @Autowired private ActiveMQConnectionFactory connectionFactory = null;
    @Autowired private JmsTemplate jmsTemplate = null;
  
    private String destinationName = null;
    
//    private boolean persistent = true;
    
    private long timeToLive = 0;
  
//    private Destination destination = null;
  
    private Connection connection = null;
  
    private Session session = null;
  
    private MessageConsumer consumer = null;
  
//    private MessageProducer producer = null;
    
    private boolean sessionTransacted = false;
    
    // 初始化   
    public void initialize() {
    	
   	 	try {
   	 		// Proceed with actual listener initialization.
//   			super.initialize();
//   			
////   			this.setBeanName(destinationName);
//   			destinationName = ((ActiveMQQueue)this.getDestination()).getPhysicalName();
   			
   	 		// 会话创建消息的生产者将消息发送到目的地
			jmsTemplate.setDefaultDestinationName(destinationName);
			
			sessionTransacted = jmsTemplate.isSessionTransacted();
//			this.setMessageListener(this);
			
//			if (persistent) {
//				jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
//			} else {
				jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//			}
//			if (timeToLive != 0) {
//				jmsTemplate.setTimeToLive(timeToLive);
//			}
			
			log.info("Queue Start Listening..." + connectionFactory.getBrokerURL() + "/" + this.destinationName + "(Transacted=" + sessionTransacted + ")");
			log.info("TimeToLive=" + jmsTemplate.getTimeToLive());
			log.info("DeliveryMode=" + jmsTemplate.getDeliveryMode());
			log.info("SessionTransacted=" + jmsTemplate.isSessionTransacted());
			log.info("DeliveryMode=" + jmsTemplate.getDeliveryMode());
			
			// 开始监听   
//			consumer.setMessageListener(this);
//			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// Message message = consumer.receive();
			
		} catch (Exception e) {
			log.error("Queue Connection Fail : " + connectionFactory.getBrokerURL() + "/" + this.destinationName, e);
		}
    }
    
    // 初始化   
//    protected void initialize1() throws JMSException, Exception {
//    	
//   	 	try {
//   	    	
//			// 连接工厂是用户创建连接的对象，这里使用的是ActiveMQ的ActiveMQConnectionFactory根据url，username和password创建连接工厂。
//			// ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
//			// 连接工厂创建一个jms connection
//			connection = connectionFactory.createConnection();
//			// 是生产和消费的一个单线程上下文。会话用于创建消息的生产者，消费者和消息。会话提供了一个事务性的上下文。
//			// session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);		//不支持事务 
//			session = connection.createSession(true, Session.SESSION_TRANSACTED);		//支持事务 
//			// 目的地是客户用来指定他生产消息的目标还有他消费消息的来源的对象，两种消息传递方式：点对点和发布/订阅
//			destination = session.createQueue(queueName);
//			// 会话创建消息的生产者将消息发送到目的地
//			consumer = session.createConsumer(destination);
//
//			producer = session.createProducer(destination);
//			if (persistent) {
//				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//			} else {
//				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//			}
//			if (timeToLive != 0) {
//				producer.setTimeToLive(timeToLive);
//			}
//	        
//			connection.start();
//			   
//			log.info("Queue Start Listening..." + connectionFactory.getBrokerURL() + "/" + this.queueName);
//			// 开始监听   
//			consumer.setMessageListener(this);
//			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//			// Message message = consumer.receive();
//			
//		} catch (Exception e) {
//			log.error("Queue Connection Fail : " + connectionFactory.getBrokerURL() + "/" + this.queueName, e);
//		}
//    }
  
	// 关闭连接
	public void close() throws JMSException {
		log.info("Queue Start Closing..." + connectionFactory.getBrokerURL() + "/" + this.destinationName);
		if (consumer != null)
			consumer.close();
		if (session != null)
			session.close();
		if (connection != null)
			connection.close();
	}
	  
	// 消息处理方法
//	public void onMessage(Message message) {
//		try {
//			if (message instanceof TextMessage) {
//				TextMessage txtMsg = (TextMessage) message;
//				String msg = txtMsg.getText();
//
//				log.info("[" + this.queueName + "]Received: " + msg);
//				// 调用子类方法处理文本消息
//				onTextMessage(msg);
//				// log.info("[" + this.queueName + "]Received Complete: " + msg);
//
//			} else {
//				log.info("[" + this.queueName + "][" + message.getClass() + "]Skip: " + message);
//			}
//		} catch (JMSException e) {
//			log.error("[" + this.queueName + "]Received Fail: " + message, e);
//		} catch (Exception e) {
//			log.error("[" + this.queueName + "]Received Fail: " + message, e);
//		}
//	}
	  
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
		
//		jmsTemplate.send(destination, new MessageCreator() {
//	        public Message createMessage(Session session) throws JMSException {
//	        	TextMessage message = session.createTextMessage(msg);
//    			log.info("[" + queueName + "]Send: " + msg);
//	    		return message;
//	        }
//	    });
		
		log.info("[" + destinationName + "]Send: " + msg);
		jmsTemplate.convertAndSend(msg);
	}
    
	// 消息处理文本方法
	protected abstract void onTextMessage(String msg);
	
	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

//	public boolean isPersistent() {
//		return persistent;
//	}
//
//	public void setPersistent(boolean persistent) {
//		this.persistent = persistent;
//	}

	public long getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}
}