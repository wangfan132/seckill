package org.seckill.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SecKillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class mybatisTest {
	@Resource
	private SecKillDao secKillDao;
	@Resource
	private SuccessKilledDao successKilledDao;
    @Test
    public void test() {
    	int a = secKillDao.reduceNumber(1000, new Date());
    	System.out.println(a);
    }
    @Test
    public void test1() {
    	long id = 1000;
    	Seckill seckill = secKillDao.queryById(id);
    	System.out.println(seckill.toString());
    }
    @Test
    public void test2() {
    	System.out.println("1");
    	List<Seckill> list = new ArrayList<Seckill>();
    	list = secKillDao.queryAll(0, 3);
    	for(Seckill s : list) {
    		System.out.println(s.toString());
    	}
    	
    }
    @Test
    public void test3() {
    	SuccessKilled successKilled = successKilledDao.queryByIdWithSuccessKilled(1000,"17");
    	
    }
}
