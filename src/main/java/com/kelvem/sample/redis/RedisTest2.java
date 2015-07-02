package com.kelvem.sample.redis;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest2 {

	public static void main(String[] args) {
		
		
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		
		JedisPoolConfig config = new JedisPoolConfig();
		String host = bundle.getString("redis.host");
		config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		
		JedisPool pool = new JedisPool(config, host);
		
		Jedis jedis = pool.getResource();
		
		jedis.set("name", "kelvem");
		
		System.out.println(jedis.get("name"));
	}

}