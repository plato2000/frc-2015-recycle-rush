package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;

public class Elevator {
	public static final int LEFT_ELEVATOR = 6;
	public static final int RIGHT_ELEVATOR = 7;
	
	private double kLift_P = 0.5;
	private double kLeft_I = 0.05;
	private double kLift_D = 0.005;
	
	private Talon leftTalon;
	private Talon rightTalon;
	private Encoder encoder;
	
	private PIDController liftPID;
	
	private double currentHeight = 0;
	private double DISTANCE_PER_PULSE = 0.1;
	
	public Elevator() {
		leftTalon = new Talon(LEFT_ELEVATOR);
		rightTalon = new Talon(RIGHT_ELEVATOR);
		
		encoder = new Encoder(4, 5);
		encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
		encoder.setDistancePerPulse(1);
		encoder.reset();
	}
	
	public void move(Gamepad control) {
	
	}
}