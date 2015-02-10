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
	
	private Talon leftElevatorTalon;
	private Talon rightElevatorTalon;
	private Encoder encoder;
	
	public Elevator() {
		this.leftElevatorTalon = new Talon(LEFT_ELEVATOR);
		this.rightElevatorTalon = new Talon(RIGHT_ELEVATOR);
		this.encoder = new Encoder(UP_ENCODER_CHANNEL, DOWN_ENCODER_CHANNEL);
		this.encoder.reset();
	}
	
	public void move(Gamepad control) {
		if (control.isDPadUpPressed()) {
			this.leftElevatorTalon.set(0.4);
			this.rightElevatorTalon.set(0.4);
		} else {
			if (this.encoder.getDistance() > 0) {
				double travelled = this.encoder.getDistance();
				this.encoder.reset();
				while (this.encoder.getDistance()<=travelled) {
					this.leftElevatorTalon.set(-0.2);
					this.rightElevatorTalon.set(-0.2);
				}
				this.leftElevatorTalon.set(0);
				this.rightElevatorTalon.set(0);
				this.encoder.reset();
			}
		}
		/*
		if (control.isBButtonPressed()) {
			this.leftEncoder.set(0.4);
			this.rightEncoder.set(0.4);
		} else if (control.isAButtonPressed()) {
			this.leftEncoder.set(-0.225);
			this.rightEncoder.set(-0.225);
		} else {
			this.leftEncoder.set(0.4);
			this.rightEncoder.set(0.4);
		}
		*/
	}
}