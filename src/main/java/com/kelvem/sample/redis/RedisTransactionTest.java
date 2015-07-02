package com.kelvem.sample.redis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public class RedisTransactionTest {

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context-redis.xml");
		JedisPool pool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = pool.getResource();

		pool.returnResource(jedis);

		
		// Transaction
		Transaction tx = jedis.multi();
		for(int i = 0;i < 10;i ++) {
		     tx.set("key" + i, "value" + i); 
		     System.out.println("--------key" + i);
		     Thread.sleep(1000);  
		}
		List<Object> results = tx.exec();
		System.out.println(jedis.keys("*"));

		
	}

}