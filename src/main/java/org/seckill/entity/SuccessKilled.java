package org.seckill.entity;

import java.io.Serializable;
import java.util.Date;

public class SuccessKilled implements Serializable{
	private String user_phone;
	private short state;
	private Date create_time;
	private Seckill seckill;
	
	public Seckill getSeckill() {
		return seckill;
	}
	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
	
	
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "SuccessKilled [user_iphone=" + user_phone + ", state=" + state + ", create_time=" + create_time
				+ ", seckill=" + seckill + "]";
	}
	
	
}
