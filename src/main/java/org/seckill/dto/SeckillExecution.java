package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillEnum;

public class SeckillExecution {
	private long seckillId;
	private int state;
	private String stateInfo;
	private SuccessKilled successKilled;
	
	public SeckillExecution(long seckillId, SeckillEnum seckillEnum) {
		super();
		this.seckillId = seckillId;
		this.state = seckillEnum.getState();
		this.stateInfo = seckillEnum.getStateInfo();
	}
	public SeckillExecution(long seckillId, SeckillEnum seckillEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = seckillEnum.getState();
		this.stateInfo = seckillEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
}
