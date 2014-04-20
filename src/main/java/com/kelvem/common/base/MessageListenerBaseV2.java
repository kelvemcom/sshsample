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

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>MessageListenerBaseV2</p>
 * <p>http://127.0.0.1:8161/admin/</p>
 * <p>default   admin:admin</p>
 * http://127.0.0.1:8161/admin/queues.jsp
 * 
 * <p>使用基础类实现JMS, 不使用Spring等其他增强</p>
 * connectionFactory注入
 * 
 * <p>Copyright: 版权所有 (c) 2010 - 2014</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName MessageListenerBaseV2
 * @author kelvem
 * @version 1.0
 * 
 */
public abstract class MessageListenerBaseV2 implements MessageListener {
	
	private static Log log = LogFactory.getLog(MessageListenerBaseV2.class);
  
    public final static String user = ActiveMQConnection.DEFAULT_USER;
    public final static String password = ActiveMQConnection.DEFAULT_PASSWORD;
    public final static String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    @Autowired private ActiveMQConnectionFactory connectionFactory = null;
  
    private String destinationName = null;
    
    private boolean persistent = true;
    
    private long timeToLive = 0;
  
    private Destination destination = null;
  
    private Connection connection = null;
  
    private Session session = null;
  
    private MessageConsumer consumer = null;
  
    private MessageProducer producer = null;
    
    private boolean sessionTransacted = false;
    
    // 初始化   
    public void initialize() {
    	
   	 	try {
   	    	
			// 连接工厂是用户创建连接的对象，这里使用的是ActiveMQ的ActiveMQConnectionFactory根据url，username和password创建连接工厂。
			//connectionFactory = new ActiveMQConnectionFactory(user, password, brokerURL);
			// 连接工厂创建一个jms connection
			connection = connectionFactory.createConnection();
			// 是生产和消费的一个单线程上下文。会话用于创建消息的生产者，消费者和消息。会话提供了一个事务性的上下文。
			if (sessionTransacted == true) {
				session = connection.createSession(true, Session.SESSION_TRANSACTED);		//支持事务  0
			} else {
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);		//不支持事务 1
			}
			// 目的地是客户用来指定他生产消息的目标还有他消费消息的来源的对象，两种消息传递方式：点对点和发布/订阅
			destination = session.createQueue(destinationName);
			// 会话创建消息的生产者将消息发送到目的地
			consumer = session.createConsumer(destination);
			
			// 开始监听   
			consumer.setMessageListener(this);
			// Message message = consumer.receive();

			producer = session.createProducer(destination);
			
			// 消息是否持久化保存
			if (persistent == true) {
				producer.setDeliveryMode(DeliveryMode.PERSISTENT); // 2
			} else {
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT); // 1
			}
			// 消息的存活时间, 0表示永久
			if (timeToLive != 0) {
				producer.setTimeToLive(timeToLive);
			}
	        
			// 启动mq服务
			connection.start();
			   
			log.info("Queue Start Listening..." + connectionFactory.getBrokerURL() + "/" + this.destinationName);
			
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
		try {
			log.info("[" + destinationName + "]Send: " + msg);
			ActiveMQTextMessage message = new ActiveMQTextMessage();
	        message.setText(msg);
			producer.send(message);
		} catch (JMSException e) {
			log.error("[" + this.destinationName + "]Send Fail: " + msg, e);
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

	public boolean getPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	public long getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public boolean getSessionTransacted() {
		return sessionTransacted;
	}

	public void setSessionTransacted(boolean sessionTransacted) {
		this.sessionTransacted = sessionTransacted;
	}

	public ActiveMQConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
}