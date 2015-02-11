package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	public static final int LEFT_ELEVATOR = 6;
	public static final int RIGHT_ELEVATOR = 7;
	
	private double kLift_P = 0.5;
	private double kLift_I = 0.05;
	private double kLift_D = 0.005;
	
	private Talon leftTalon;
	private Talon rightTalon;
	private Encoder encoder;
	
	private PIDController leftLiftPID;
	private PIDController rightLiftPID;
	
	private double currentHeight = 0;
	private double DISTANCE_PER_PULSE = 0.1;
	
	public Elevator() {
		leftTalon = new Talon(LEFT_ELEVATOR);
		rightTalon = new Talon(RIGHT_ELEVATOR);
		
		encoder = new Encoder(4, 5);
		encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
		encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		
		leftLiftPID = new PIDController(kLift_P, kLift_I, kLift_D, encoder, leftTalon);
		rightLiftPID = new PIDController(kLift_P, kLift_I, kLift_D, encoder, rightTalon);
		leftLiftPID.enable();
		rightLiftPID.enable();
		encoder.reset();
	}
	
	public void move(Gamepad control) {
		if (control.isBButtonPressed()) {
			currentHeight += 0.1;
		} else if (control.isAButtonPressed()) {
			currentHeight -= 0.1;
		}
		
		if (currentHeight > 10) {
			currentHeight = 10;
		}
		
		if (currentHeight < 0) {
			currentHeight = 0;
		}
		
		if (control.isYButtonPressed()) {
			leftTalon.set(0.35);
			rightTalon.set(0.35);
		} else {
			leftTalon.set(0.0);
			rightTalon.set(0.0);
		}
		
		SmartDashboard.putString("encoder", encoder.getDistance() + "");
		SmartDashboard.putString("dpad", control.getPOV() + "");
		Robot.debug.println(encoder.getDistance() + "");
		//setHeight(currentHeight);
	}
	
	public void setHeight(double height) {
		leftLiftPID.setSetpoint(height);
		rightLiftPID.setSetpoint(height);
	}
}