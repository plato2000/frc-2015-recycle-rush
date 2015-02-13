package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.camera.RobotCamera;
import org.usfirst.frc.team4099.control.FlightStick;
import org.usfirst.frc.team4099.control.Gamepad;
import org.usfirst.frc.team4099.robot.drive.Driver;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
	public static final String CAMERA_IP = "10.40.99.11";
	public static final int YAW_SERVO = 8;
	public static final int PITCH_SERVO = 9;
	private RobotCamera camera = new RobotCamera(CAMERA_IP, YAW_SERVO, PITCH_SERVO);
	private LimitSwitches limitswitches = new LimitSwitches();
	private Driver robotDrive;
    private Gamepad controller = new Gamepad(0);
    //private Joystick lifter = new Joystick(1);
    private FlightStick flight = new FlightStick(1);

    private Elevator elevator = new Elevator();
    private Reel reel = new Reel();
    
    public static final String DEBUG_FILE = "/tmp/debug.txt";
    public static Debug debug = new Debug(DEBUG_FILE);
    
    public Robot() {
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
        //robotDrive.enterAutonomousMode();
        while (isAutonomous() && isEnabled()) {
        	robotDrive.autoDrive();
    	}
    }
    
    public void operatorControl() {
    	limitswitches.addToSmartDashboard();
        robotDrive.enterTeleoperatedMode();
		debug.println("Entering teleoperated mode...");
		SmartDashboard.putBoolean("isUsingPID?", false);
		while (isOperatorControl() && isEnabled()) {
			robotDrive.drive(controller, flight);
			// move the reel in wheels
			//reel.move(controller, flight);
			
			// move elevator (pick one)
			elevator.twoManOpHuman(flight);
			//elevator.singleManOpPID(controller);
			//elevator.twoManOpPID(flight);
			
			//elevator.move(controller);
			// moving camera
			//camera.moveCamera(controller);

			// wait for motor update
            Timer.delay(0.005);
        }
    }
    
    public void test() {
    	debug.println("Entering testing mode...");
    }
}
