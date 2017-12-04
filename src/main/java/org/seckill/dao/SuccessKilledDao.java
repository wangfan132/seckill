package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	int insertSuccessKilled(long seckill_id,String userPhone);
	
	SuccessKilled queryByIdWithSuccessKilled(long seckilledId,String user_phone);
}
