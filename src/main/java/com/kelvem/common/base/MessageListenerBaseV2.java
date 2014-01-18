package com.kelvem.common.base;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.JmsUtils;
  
public abstract class MessageListenerBaseV2 implements MessageListener {
	
	private static Log log = LogFactory.getLog(MessageListenerBase.class);
  
//    private String user = ActiveMQConnection.DEFAULT_USER;
//    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
//    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    @Autowired private ActiveMQConnectionFactory connectionFactory = null;
  
    private String destinationName = null;
    
    private boolean persistent = true;
  
    private long timeToLive = 0;
    
    private boolean sessionTransacted = true;
  
    private Destination destination = null;
  
    private Connection connection = null;
  
    private Session session = null;
  
    private MessageConsumer consumer = null;
  
    private MessageProducer producer = null;
    
    // 初始化   
    protected void initialize() throws JMSException, Exception {
    	
   	 	try {
   	    	
			// 连接工厂是用户创建连接的对象，这里使用的是ActiveMQ的ActiveMQConnectionFactory根据url，username和password创建连接工厂。
			// ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
			// 连接工厂创建一个jms connection
			connection = connectionFactory.createConnection();
			// 是生产和消费的一个单线程上下文。会话用于创建消息的生产者，消费者和消息。会话提供了一个事务性的上下文。
			session = connection.createSession(sessionTransacted, Session.AUTO_ACKNOWLEDGE);  //不支持事务 
			// 目的地是客户用来指定他生产消息的目标还有他消费消息的来源的对象，两种消息传递方式：点对点和发布/订阅
			destination = session.createQueue(destinationName);
			// 会话创建消息的生产者将消息发送到目的地
			consumer = session.createConsumer(destination);
			producer = session.createProducer(destination);
			
			if (persistent) {
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			} else {
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			}
			if (timeToLive != 0) {
				producer.setTimeToLive(timeToLive);
			}
			
			//connection.start();
			
			log.info("Queue Start Listening..." + connectionFactory.getBrokerURL() + "/" + this.destinationName);
			// 开始监听   
			consumer.setMessageListener(this);

   	 	} catch (Exception e) {
			log.error("Queue Connection Fail : " + connectionFactory.getBrokerURL() + "/" + this.destinationName, e);
		}
    }
  
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
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) message;
				String msg = txtMsg.getText();
				
				log.info("[" + this.destinationName + "]Received: " + msg);
				// 调用子类方法处理文本消息
				onTextMessage(msg);

			} else {
				log.info("[" + this.destinationName + "][" + message.getClass() + "]Skip: " + message);
			}
		} catch (JMSException e) {
			log.error("[" + this.destinationName + "]Received Fail: " + message, e);
		} catch (Exception e) {
			log.error("[" + this.destinationName + "]Received Fail: " + message, e);
		}
	}

	public void sendMessage(String msg) {

		try {
			TextMessage message = session.createTextMessage(msg);

			log.info("[" + this.destinationName + "]Send: " + msg);
			producer.send(message);
			
			JmsUtils.commitIfNecessary(session);
		} catch (Exception e) {
			log.error("[" + this.destinationName + "]Send Fail: " + msg, e);
			try {
				JmsUtils.rollbackIfNecessary(session);
			} catch (JMSException e1) {
				log.error("[" + this.destinationName + "]Rollback Fail: " + msg, e1);
			}
		}
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