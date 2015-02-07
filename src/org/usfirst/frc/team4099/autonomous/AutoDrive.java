package org.usfirst.frc.team4099.autonomous;

import org.usfirst.frc.team4099.camera.*;
import org.usfirst.frc.team4099.robot.drive.Driver;
import org.usfirst.frc.team4099.robot.drive.SlideDrive;

import edu.wpi.first.wpilibj.RobotDrive;


public class AutoDrive {
	private RobotCamera camera;
	private SlideDrive slideDrive;
	
	public AutoDrive(RobotCamera camera, SlideDrive slideDrive) {
		this.camera = camera;
		this.slideDrive = slideDrive;
	}
	
	public void autoDrive() {
		Direction dir;
		dir = camera.getDirection();
		switch(dir) {
			case LEFT:
				slideDrive.slideDrive(0,0,-.5/Driver.REDUCTION_FACTOR);
				break;
			case RIGHT:
				slideDrive.slideDrive(0,0,.5/Driver.REDUCTION_FACTOR);
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
}