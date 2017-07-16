package com.dreamteam.entities;

public class Interruption {
	private int time;
	private Device device;
	private int duration;
	
	public Interruption(int time, Device device, int duration) {
		this.time = time;
		this.device = device;
		this.duration = duration;
	}
	public Interruption() {
		this.time = -1;
		this.device = new Device("null",Integer.MAX_VALUE);
		this.duration = 0;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
