package org.seckill.dao.cache;

import org.seckill.entity.Seckill;
import org.seckill.util.SerializaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final JedisPool jedisPool;
	public RedisDao(String ip,int port) {
		jedisPool = new JedisPool(ip,port);
	}
	
	
	public Seckill getSeckill(Long seckillId) {
		Jedis jedis = jedisPool.getResource();
		try {
			String key = "seckill:" + seckillId;
			byte[] bytes = jedis.get(key.getBytes());
			Seckill seckill = (Seckill) SerializaUtils.deserialize(bytes);
			return seckill;
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			jedis.close();
		}
		return null;
	}
	public String putSeckill(Seckill seckill) {
		Jedis jedis = jedisPool.getResource();
		String key = "seckill:" + seckill.getSeckill_id();
		byte[] bytes = SerializaUtils.serialize(seckill);
		String result = jedis.setex(key.getBytes(), 3600, bytes);
		return result;
	}
}
