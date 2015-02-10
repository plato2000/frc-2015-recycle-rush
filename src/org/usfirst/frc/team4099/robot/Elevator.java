package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;

public class Elevator {
	public static final int LEFT_ELEVATOR = 6;
	public static final int RIGHT_ELEVATOR = 7;
	public static final int UP_ENCODER_CHANNEL = 4;
	public static final int DOWN_ENCODER_CHANNEL = 5;
	
	private Talon upElevatorTalon;
	private Talon downElevatorTalon;
	private Encoder encoder;
	
	public Elevator() {
		this.upElevatorTalon = new Talon(LEFT_ELEVATOR);
		this.downElevatorTalon = new Talon(RIGHT_ELEVATOR);
		this.encoder = new Encoder(UP_ENCODER_CHANNEL, DOWN_ENCODER_CHANNEL);
		this.encoder.reset();
	}
	
	public void move(Gamepad control) {
		if (control.isDPadUpPressed()) {
			this.upElevatorTalon.set(0.4);
		} 
		/*
		if (control.isBButtonPressed()) {
			this.upEncoder.set(0.4);
			this.downEncoder.set(0.4);
		} else if (control.isAButtonPressed()) {
			this.upEncoder.set(-0.225);
			this.downEncoder.set(-0.225);
		} else {
			this.upEncoder.set(0.4);
			this.downEncoder.set(0.4);
		}
		*/
	}
}