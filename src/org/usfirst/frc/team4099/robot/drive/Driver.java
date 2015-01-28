package org.usfirst.frc.team4099.robot.drive;

import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.RobotDrive;

public class Driver extends RobotDrive {
	private DriveMode currentMode = DriveMode.ARCADE;
	
	public Driver(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
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
	
	public void drive(Gamepad controller) {
		switch (currentMode) {
		case ARCADE:
			this.arcadeDrive(controller.getLeftVerticalAxis(), controller.getLeftHorizontalAxis());
			break;

		case SLIDE:
			//TODO: IMPLEMENT AN OMNIWHEEL (H/SLIDE DRIVE)
			break;
		}
	}
}
