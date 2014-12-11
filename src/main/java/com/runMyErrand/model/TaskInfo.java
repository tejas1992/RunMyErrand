package com.runMyErrand.model;

//Task Model
public class TaskInfo {
	
	private int taskid;
	private String taskDescription;
	private float points;
	private String start_date;
	private String end_date;
	private int completed;
	private String useremail;
	private String room;
	private String recurrence = "no";
	private int masterId;
	private int number_of_days;

	
	public int getNumber_of_days() {
		return number_of_days;
	}
	public void setNumber_of_days(int number_of_days) {
		this.number_of_days = number_of_days;
	}
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	public String getRecurrence() {
		return recurrence;
	}
	
	public float getPoints() {
		return points;
	}
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public String getRoom() {
		return room;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getMasterId() {
		return masterId;
	}
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	                            
}
