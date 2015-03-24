package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.camera.RobotCamera;
import org.usfirst.frc.team4099.autonomous.AutoMode;
import org.usfirst.frc.team4099.control.Gamepad;
import org.usfirst.frc.team4099.robot.drive.Driver;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.*;

import java.util.ArrayList;

public class Robot extends SampleRobot {
	public static final String CAMERA_IP = "10.40.99.11";
	public static final int YAW_SERVO = 8;
	public static final int PITCH_SERVO = 9;
	private RobotCamera camera = new RobotCamera(CAMERA_IP, YAW_SERVO, PITCH_SERVO);
	private LimitSwitches limitswitches = new LimitSwitches();
	private Driver robotDrive;
    private Gamepad controller = new Gamepad(0);
    
    public String recordPath = "";
    public ArrayList moves = new ArrayList(10000);

    private Elevator elevator = new Elevator();
    
    public static final String DEBUG_FILE = "/tmp/debug.txt";
    public static Debug debug = new Debug(DEBUG_FILE);
    
    public Robot() {
    	SendableChooser sendableChooser = new SendableChooser();
    	sendableChooser.addDefault("Tote and Bin", AutoMode.PICK_UP_TOTE_AND_MOVE_TO_AUTO_ZONE);
    	sendableChooser.addObject("Move w/o Picking", AutoMode.MOVE_TO_AUTO_ZONE);
    	sendableChooser.addObject("DO NOT USE", AutoMode.PICK_AND_STACK_TOTES_AND_MOVE_TO_AUTO_ZONE);
    	robotDrive = new Driver(camera);
    }
    
    public void robotinit() {
    	debug.println("Robot initialized...");
    }
    
    public void disabled() {
    	debug.println("Robot disabled...");
    }

    public void autonomous() {
    	debug.println("Entering autonomous mode...");
        robotDrive.enterAutonomousMode();
        while (isAutonomous() && isEnabled()) {
        	robotDrive.autoDrive();
    	}
    }
    
    public void operatorControl() {
    	limitswitches.addToSmartDashboard();
        robotDrive.enterTeleoperatedMode();
		debug.println("Entering teleoperated mode...");

		while (isOperatorControl() && isEnabled()) {
			robotDrive.record = SmartDashboard.getString("PathToRecord");
			if(controller.isAButtonPressed() && controller.isBButtonPressed()) {
				System.out.println("Recording moves... be quick!");
				double[] inputs = {
						controller.getLeftVerticalAxis(),
						controller.getLeftHorizontalAxis(),
						controller.getRightHorizontalAxis(),
				};
				
			}
			
			
			robotDrive.drive(controller);

			// move elevator
			elevator.move(controller);
			
			// moving camera
			//camera.moveCamera(controller);

			//take a photo
			if (controller.isAButtonPressed()) {
                Timer.delay(1.0);
				camera.takePhoto();
			}

			// wait for motor update
            Timer.delay(0.005);
        }
    }
    
    public void test() {
    	debug.println("Entering testing mode...");
    }
}
