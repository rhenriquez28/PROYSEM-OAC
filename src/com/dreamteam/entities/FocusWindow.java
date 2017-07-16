package com.dreamteam.entities;

public class FocusWindow {
	private Interruption  interruption;
	
	private int focusBegining;
	private int focusEnding;
	public FocusWindow(Interruption interruption, int focusBegining, int focusEnding) {
		this.interruption = interruption;
		this.focusBegining = focusBegining;
		this.focusEnding = focusEnding;
	}
	public FocusWindow(Interruption interruption, int focusBegining) {
		this.interruption = interruption;
		this.focusBegining = focusBegining;
	}
	public Interruption getDevice() {
		return interruption;
	}
	public void setDevice(Interruption interruption) {
		this.interruption = interruption;
	}
	public int getFocusBegining() {
		return focusBegining;
	}
	public void setFocusBegining(int focusBegining) {
		this.focusBegining = focusBegining;
	}
	public int getFocusEnding() {
		return focusEnding;
	}
	public void setFocusEnding(int focusEnding) {
		this.focusEnding = focusEnding;
	}
	

}
