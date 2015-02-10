package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Elevator {
	public static final int LEFT_ELEVATOR = 6;
	public static final int RIGHT_ELEVATOR = 7;
	
	private SpeedController leftElevator;
	private SpeedController rightElevator;
	
	public Elevator() {
		this.leftElevator = new Talon(LEFT_ELEVATOR);
		this.rightElevator = new Talon(RIGHT_ELEVATOR);
	}
	
	public void move(Gamepad control) {
		if (control.isBButtonPressed()) {
			this.leftElevator.set(0.4);
			this.rightElevator.set(0.4);
		} else if (control.isAButtonPressed()) {
			this.leftElevator.set(-0.225);
			this.rightElevator.set(-0.225);
		} else {
			this.leftElevator.set(0.4);
			this.rightElevator.set(0.4);
		}
	}
}