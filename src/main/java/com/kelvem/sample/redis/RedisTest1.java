package com.kelvem.sample.redis;

import redis.clients.jedis.Jedis;

public class RedisTest1 {

	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("192.168.1.6", 6379);
//		Jedis jedis = new Jedis("192.168.74.128", 6379);
		
		jedis.set("name", "kelvem");
		
		
		System.out.println(jedis.get("name"));
	}

}