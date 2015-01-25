package org.usfirst.frc.team4099.robot.drive;

import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedController;

public class Driver extends RobotDrive {
	private DriveMode currentMode = DriveMode.ARCADE;
	private SpeedController slider;
	
	public Driver(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor, int slider) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.slider = new Talon(slider);
	}
	
	public void setDriveMode(DriveMode type) {
		currentMode = type;
	}
	
	public DriveMode getCurrentDriveMode() {
		return currentMode;
	}
	
	public void toggleDriveMode() {
		switch (currentMode) {
		case ARCADE:
			setDriveMode(DriveMode.SLIDE);
			break;
			
		case SLIDE:
			setDriveMode(DriveMode.ARCADE);
			break;
		}
	}
	
	public void omniWheelDrive(double forward, double side) {
		this.slider.set(side);
		this.m_frontLeftMotor.set(forward);
		this.m_frontRightMotor.set(forward);
		this.m_rearLeftMotor.set(forward);
		this.m_rearRightMotor.set(forward);
	}
	
	public void drive(Gamepad controller) {
		switch (currentMode) {
		case ARCADE:
			this.arcadeDrive(controller.getLeftVerticalAxis(), controller.getLeftHorizontalAxis());
			break;

		case SLIDE:
			this.omniWheelDrive(controller.getLeftVerticalAxis(), controller.getLeftHorizontalAxis());
			break;
		}
	}
	
}
