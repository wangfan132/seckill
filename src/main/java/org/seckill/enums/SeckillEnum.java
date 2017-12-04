package org.seckill.enums;

public enum SeckillEnum {
	SUCCESS(1,"��ɱ�ɹ�"),
	END(0,"��ɱ����"),
	REPEAT(-1,"�ظ���ɱ"),
	INNER_ERROR(-2,"ϵͳ�쳣"),
	DATE_REWRITE(-3,"���ݴ۸�");
	private int state;
	private String stateInfo;
	
	private SeckillEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
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
	public static SeckillEnum stateOf(int index) {
		for(SeckillEnum s : values()) {
			if(s.getState() == index) {
				return s;
			}
		}
		return null;
	}
}
