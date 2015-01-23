package org.usfirst.frc.team4099.robot;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Robot extends SampleRobot {
    RobotDrive robotDrive; // the current drive method
    Gamepad controller = new Gamepad(0); // uses port 0 by default
    ArrayList<Talon> talons = new ArrayList<Talon>();
    AxisCamera camera = new AxisCamera("10.40.99.11");
    boolean isSlideDrive = false;
    int imgCount = 0;
    
    public Robot() {
        robotDrive = new RobotDrive(0, 1);
        robotDrive.setExpiration(0.1);
    }
    
    public void robotinit() {
    	for (int i=2; i<=7; i++) {
    		talons.add(new Talon(i));
    		System.out.println(talons.size());
    	}
    	System.out.println("Robot initialized...");
    }
    
    public void disabled() {
    	
    }

    /**
     * Autonomous mode for the robot.
     */
    public void autonomous() {
    	while (isAutonomous() && isEnabled()) {
    		
    	}
    }
    
    /**
     * Teleoperated mode for the robot.
     */
    public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		robotinit();
		System.out.println("Entered teleoperated mode.");

		while (isOperatorControl() && isEnabled()) {
			if (controller.isAButtonPressed()) {
				ColorImage img;
				try {
					img = camera.getImage();
					File output = new File("/home/admin/savedimgs/img-" + imgCount + ".jpg");
					ImageIO.write((RenderedImage) img, "jpg", output);
					imgCount++;
				} catch (NIVisionException e) {
					System.out.println("Could not take image.");
				} catch (IOException e) {
					System.out.println("Count not save image.");
				}
			}

			if (isSlideDrive) {
				for (int i = 2; i <= 7; i++) {
					if (i % 2 == 0) {
						talons.get(i - 2).set(controller.getLeftVerticalAxis());
					} else {
						talons.get(i - 2)
								.set(-controller.getLeftVerticalAxis());
					}
				}
			} else {
                /* will be arcade drive */
        		robotDrive.arcadeDrive(controller.getLeftVerticalAxis(), controller.getLeftHorizontalAxis());
        	}
			
			if (controller.getYButtonPressed()) {
				isSlideDrive = !isSlideDrive;
				System.out.println("Slide drive? " + isSlideDrive);
			}

            Timer.delay(0.005);	// wait for a motor update time
        }
    }
    
    /* Set up drive modes */
    
    public void switchDriveMode() {
    	System.out.println("trying to switch.");
    }
    
    public void setArcadeDriveMode() {
    	//robotDrive.arcadeDrive(controller.getLeftVerticalAxis(), controller.getLeftHorizontalAxis());
    }
    
    public void setOmniDriveMode() {
    }

    /**
     * Runs during test mode
     */
    public void test() {

    }
}
