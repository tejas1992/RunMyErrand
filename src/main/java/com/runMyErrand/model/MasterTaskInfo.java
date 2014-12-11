package com.runMyErrand.model;

public class MasterTaskInfo {
	
	private int masterid;
	private String mastertaskdesc;
	private String room;
	private float points;
	private int defaultdays;

	
	public int getDefaultdays() {
		return defaultdays;
	}
	public void setDefaultdays(int defaultdays) {
		this.defaultdays = defaultdays;
	}
	public int getMasterid() {
		return masterid;
	}
	public void setMasterid(int masterid) {
		this.masterid = masterid;
	}
	public String getMastertaskdesc() {
		return mastertaskdesc;
	}
	public void setMastertaskdesc(String mastertaskdesc) {
		this.mastertaskdesc = mastertaskdesc;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	
	
}
