package org.seckill.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seckill.dao.SecKillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SecKillServiceImpl implements SeckillService {
	String slat = "DSFONIONION())(JMOPM{L}OLPIHNI{OJMPJM";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SecKillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;

	public List<Seckill> getSeckillList() {
		// TODO Auto-generated method stub
		return seckillDao.queryAll(0, 3);
	}

	public Seckill getById(long seckillId) {
		// TODO Auto-generated method stub
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		// TODO Auto-generated method stub
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) {
			seckill = seckillDao.queryById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			} else {
				redisDao.putSeckill(seckill);
			}
		}
		Date startTime = seckill.getStart_time();
		Date endTime = seckill.getEnd_time();
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5 = getMd5(seckillId);

		return new Exposer(true, md5, seckillId);
	}

	private String getMd5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	public SeckillExecution executeSeckill(long seckillId, String user_Phone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException {
		// TODO Auto-generated method stub
		int insertCount = successKilledDao.insertSuccessKilled(seckillId, user_Phone);
		if (insertCount <= 0) {
			throw new RepeatKillException("seckill repeated");
		}
		int updateCount = seckillDao.reduceNumber(seckillId, new Date());
		if (updateCount <= 0) {
			throw new SeckillCloseException("seckill closed");
		}

		if (md5 == null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillException("skill data rewite");
		} else {
			SuccessKilled successKilled = successKilledDao.queryByIdWithSuccessKilled(seckillId, user_Phone);
			return new SeckillExecution(seckillId, SeckillEnum.SUCCESS, successKilled);
		}
	}

	public SeckillExecution executeSeckillProducer(long seckillId, String user_Phone, String md5) {
		if (md5 == null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillException("skill data rewite");
		}
		Date killTime = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seckillId", seckillId);
		map.put("phone", user_Phone);
		map.put("killTime", killTime);
		map.put("result", null);
		try {
			seckillDao.killByProcudure(map);
			Integer result = (Integer) map.get("result");
			if (result == 1) {
				SuccessKilled sk = successKilledDao.queryByIdWithSuccessKilled(seckillId, user_Phone);
				return new SeckillExecution(seckillId, SeckillEnum.SUCCESS, sk);
			} else {
				return new SeckillExecution(seckillId, SeckillEnum.stateOf(result));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new SeckillExecution(seckillId, SeckillEnum.INNER_ERROR);
		}

	}
}
