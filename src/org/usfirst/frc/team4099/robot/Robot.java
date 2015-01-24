package org.usfirst.frc.team4099.robot;

import java.util.ArrayList;

import org.usfirst.frc.team4099.camera.RobotCamera;
import org.usfirst.frc.team4099.control.Gamepad;
import org.usfirst.frc.team4099.robot.drive.Driver;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;


public class Robot extends SampleRobot {
	public static final String CAMERA_IP = "10.40.99.11";
	public static final int SERVO_YAW = 10;
	public static final int SERVO_PITCH = 11;
	
	public static final int FRONT_LEFT_MOTOR = 0;
	public static final int REAR_LEFT_MOTOR = 1;
	public static final int FRONT_RIGHT_MOTOR = 6;
	public static final int REAR_RIGHT_MOTOR = 7;

	private RobotCamera camera = new RobotCamera(CAMERA_IP, SERVO_YAW, SERVO_PITCH);
	private Driver robotDrive;
    Gamepad controller = new Gamepad(0);
    ArrayList<Talon> talons = new ArrayList<Talon>();
    
    public Robot() {
    	robotDrive = new Driver(FRONT_LEFT_MOTOR,
    							REAR_LEFT_MOTOR,
    							FRONT_RIGHT_MOTOR,
    							REAR_RIGHT_MOTOR);

        robotDrive.setExpiration(0.1);
    }
    
    public void robotinit() {
    	System.out.println("Robot initialized...");
    }
    
    public void disabled() {
    	System.out.println("Robot disabled...");
    }

    public void autonomous() {
    	System.out.println("Entering autonomous mode...");
    	while (isAutonomous() && isEnabled()) {
    		
    	}
    }
    
    public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		System.out.println("Entering teleoperated mode...");

		while (isOperatorControl() && isEnabled()) {
			// *******************
			// ** DRIVING ROBOT **
			// *******************
			robotDrive.drive(controller);
			
			// moving camera
			camera.moveCamera(controller);

			// *********************
			// ** BUTTON COMMANDS **
			// *********************

			// take photos
			if (controller.isAButtonPressed()) {
				camera.takePhoto();
			}
			// switch drive mode
			if (controller.isYButtonPressed()) {
				System.out.println("Current Drive Mode: " + robotDrive.getCurrentDriveMode());
			}

			// wait for motor update
            Timer.delay(0.005);
        }
    }
    
    public void test() {
    	System.out.println("Entering testing mode...");
    }
}