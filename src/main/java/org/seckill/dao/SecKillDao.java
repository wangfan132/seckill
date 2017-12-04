package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.seckill.entity.Seckill;

public interface SecKillDao {
	int reduceNumber(long seckillId,Date killedTime);
	
	Seckill queryById(long seckillId);
	
	List<Seckill> queryAll(int offset,int limit);
	
	void killByProcudure(Map<String,Object> paramMap);
}
