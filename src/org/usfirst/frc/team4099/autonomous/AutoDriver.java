package org.usfirst.frc.team4099.autonomous;

import org.usfirst.frc.team4099.camera.*;
import org.usfirst.frc.team4099.robot.drive.Driver;


public class AutoDriver extends Driver {
	RobotCamera camera;
	
	public void enterAutonomousMode() {
		this.arcadeDrive.setSafetyEnabled(true);
	}
	
	public void autoDrive() {
		Direction dir;
		dir = camera.getDirection();
		switch(dir) {
			case LEFT:
				slideDrive.slideDrive(0,0,-.5/REDUCTION_FACTOR);
				break;
			case RIGHT:
				slideDrive.slideDrive(0,0,.5/REDUCTION_FACTOR);
				break;
			case NO_BOX:
				//move away, there is no yellow box in view
				//slideDrive.slideDrive(-.5 / REDUCTION_FACTOR, 0, 0);
				break;
			case FORWARD:
				//move forward to pick up box
				//slideDrive.slideDrive(.5 / REDUCTION_FACTOR, 0, 0);
				break;
		}
	}
	
	public AutoDriver(RobotCamera camera) {
		super();
		this.camera = camera;
	}
}
