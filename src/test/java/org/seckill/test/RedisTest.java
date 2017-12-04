package org.seckill.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.cache.RedisDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisTest {
	@Autowired
	private RedisDao redisDao;
	@Test
	public void test1() {
		Seckill seckill = new Seckill();
		seckill.setCreate_time(new Date());
		seckill.setEnd_time(new Date());
		seckill.setName("wang");
		seckill.setNumber(30);
		seckill.setSeckill_id(1009);
		redisDao.putSeckill(seckill);
	}
}
