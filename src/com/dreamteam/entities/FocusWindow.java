package com.dreamteam.entities;

public class FocusWindow {
	private Interruption  interruption;
	
	private int focusBegining;
	private int focusEnding;
	private int interruptionRemainingTime;
	public FocusWindow(Interruption interruption, int focusBegining, int focusEnding) {
		this.interruption = interruption;
		this.focusBegining = focusBegining;
		this.focusEnding = focusEnding;
	}
	public FocusWindow(Interruption interruption, int focusBegining) {
		this.interruption = interruption;
		this.focusBegining = focusBegining;
	}
	public Interruption getInterruption() {
		return interruption;
	}
	public void setInterruptio(Interruption interruption) {
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
	public int getInterruptionRemainingTime() {
		return interruptionRemainingTime;
	}
	public void setInterruptionRemainingTime(int interruptionRemainingTime) {
		this.interruptionRemainingTime = interruptionRemainingTime;
	}
	

}
