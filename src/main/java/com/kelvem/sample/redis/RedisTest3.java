package com.kelvem.sample.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest3 {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context-redis.xml");
		JedisPool pool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = pool.getResource();

		pool.returnResource(jedis);

		jedis.set("name", "kelvem");

		System.out.println(jedis.get("name"));
		
	}

}