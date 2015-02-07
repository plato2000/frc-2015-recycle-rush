package org.usfirst.frc.team4099.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimitSwitches {
	private static final int LEFT_LIMIT_SWITCH_PIN = 0;
	private static final int RIGHT_LIMIT_SWITCH_PIN = 1;
	private DigitalInput leftLimitSwitch = new DigitalInput(LEFT_LIMIT_SWITCH_PIN);
	private DigitalInput rightLimitSwitch = new DigitalInput(RIGHT_LIMIT_SWITCH_PIN);
	
	public boolean getLeftLimitSwitch() {
		return leftLimitSwitch.get();
	}
	
	public boolean getRightLimitSwitch() {
		return rightLimitSwitch.get();
	}
	
	public void addToSmartDashboard() {
		SmartDashboard.putString("Left Limit Switch", getLeftLimitSwitch()? "True":"False");
		SmartDashboard.putString("Right Limit Switch", getRightLimitSwitch()? "True":"False");
	}
}
