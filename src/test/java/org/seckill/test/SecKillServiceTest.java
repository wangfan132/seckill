package org.seckill.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SecKillServiceTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;
	@Test
	public void test1() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
	}
	@Test
	public void test2() {
		Seckill seckill = seckillService.getById(1001);
		logger.info("seckill={}",seckill);
	}
	@Test
	public void test3() {
		Exposer exposer = seckillService.exportSeckillUrl(1000);
		logger.info("exposer={}",exposer);
	}
	@Test
	public void test4() {
		Exposer exposer = seckillService.exportSeckillUrl(1001);
		String md5 = exposer.getMd5();
		SeckillExecution seckillExecution = seckillService.executeSeckillProducer(1001, "17302555201", md5);
		int result = seckillExecution.getState();
		logger.info("result:" + result);
	}
}
