package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.control.FlightStick;
import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;

public class Elevator {
	public static final int LEFT_ELEVATOR = 6;
	public static final int RIGHT_ELEVATOR = 7;
	
	private double kLift_P = 0.5;
	private double kLift_I = 0.05;
	private double kLift_D = 0.005;
	
	private boolean usingPID = false;
	
	private Talon leftTalon;
	private Talon rightTalon;
	private Encoder encoder;
	
	private PIDController leftLiftPID;
	private PIDController rightLiftPID;
	
	public static final int ENCODER_PIN_1 = 4;
	public static final int ENCODER_PIN_2 = 5;
	private double currentHeight = 0;
	private double DISTANCE_PER_PULSE = 0.1;
	
	public Elevator() {
		leftTalon = new Talon(LEFT_ELEVATOR);
		rightTalon = new Talon(RIGHT_ELEVATOR);
		
		encoder = new Encoder(ENCODER_PIN_1, ENCODER_PIN_2);
		encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
		encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		
		leftLiftPID = new PIDController(kLift_P, kLift_I, kLift_D, encoder, leftTalon);
		rightLiftPID = new PIDController(kLift_P, kLift_I, kLift_D, encoder, rightTalon);
		//leftLiftPID.enable();
		//rightLiftPID.enable();
		encoder.reset();
	}
	
	public void moveOther(FlightStick flight) {
		leftTalon.set(flight.getSlider() / 2.0);
		rightTalon.set(flight.getSlider() / 2.0);
	}
	
	public void moveOtherOther(Joystick control) {
		
	}
	
	public void move(Gamepad control) {
		if (control.isDPadUpPressed()) {
			currentHeight += 0.001;
		} else if (control.isDPadDownPressed()) {
			currentHeight -= 0.001;
		}
		
		if (currentHeight > 10) {
			currentHeight = 10;
		}
		
		if (currentHeight < 0) {
			currentHeight = 0;
		}
		
		/*if (control.isYButtonPressed()) {
			leftTalon.set(-0.6);
			rightTalon.set(-0.6);
		} else {
			leftLiftPID.enable();
			rightLiftPID.enable();
			leftTalon.set(0);
			rightTalon.set(0);
		}*/
		leftTalon.set(Math.abs(control.getRightVerticalAxis()) / -1.5);
		rightTalon.set(Math.abs(control.getRightVerticalAxis()) / -1.5);

		if (control.isAButtonPressed()) {
			setHeight(currentHeight);
		}
	}

	public void setHeight(double height) {
		leftLiftPID.setSetpoint(height);
		rightLiftPID.setSetpoint(height);
	}
}