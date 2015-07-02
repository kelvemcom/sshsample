package com.kelvem.sample.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisCount implements Runnable {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context-redis.xml");
		JedisPool pool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = pool.getResource();
		jedis.set("cnt", "0");
		jedis.set("lock", "n");

		pool.returnResource(jedis);
		
		for (int i = 0; i < 9; i++) {
			RedisCount rc = new RedisCount("Thread" + i); 
			Thread t = new Thread(rc);
			t.start();
		}

	}

	public String name = "un-set";
	public Integer cc = 0;
	
	public RedisCount(String name){
		this.name = name;
	}
	public void run() {

		System.out.println(name + " start    ##############################");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context-redis.xml");
		JedisPool pool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = pool.getResource();

		pool.returnResource(jedis);
		System.out.println(name + " start1   ##############################");
		
		while(true){
			
			String lock = null;
			try {
				lock = jedis.get("lock");
				if (name.equalsIgnoreCase(lock)) {
					jedis.set("lock", "none");
					lock = null;
					continue;
				}
				if (!lock.equalsIgnoreCase("none")) {
					continue;
				}
				
				jedis.set("lock", name);
				
				lock = jedis.get("lock");
				if (!name.equalsIgnoreCase(lock)) {
					continue;
				}
				
				String cnt = jedis.get("cnt");
				Integer c = Integer.valueOf(cnt);
				
				if (c >= 1000) {
					System.out.println(name + " end    ############################## " + cc);
					break;
				}
				c++;
				cc++;
				jedis.set("cnt", c.toString());
				System.out.println(name + " set " + c);
				
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (name.equalsIgnoreCase(lock)) {
					lock = null;
					jedis.set("lock", "none");
				}
			}
		}
		
	}

}
