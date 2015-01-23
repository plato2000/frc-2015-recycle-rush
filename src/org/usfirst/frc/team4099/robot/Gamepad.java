/**
 * Gamepad class. For use for the Logitech Gamepad for FRC.
 * Usage: Gamepad gp = new Gamepad(port);
 * 
 * Defines common functionality such as getting the button presses
 *     and axis values.
 */

package org.usfirst.frc.team4099.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends Joystick {
	
	/* Joystick Axes */
	public static final int LEFT_X_AXIS = 0;
	public static final int LEFT_Y_AXIS = 1;
	
	public static final int RIGHT_X_AXIS = 4;
	public static final int RIGHT_Y_AXIS = 5;
	
	/* Joystick Click (Buttons) */
	public static final int LEFT_JOYSTICK_BUTTON = 11;
	public static final int RIGHT_JOYSTICK_BUTTON = 12;
	
	/* Buttons (Right Side) */
	public static final int X_BUTTON = 1;
	public static final int Y_BUTTON = 4;
	public static final int A_BUTTON = 2;
	public static final int B_BUTTON = 3;
	
	/* DPAD */
	// although the DPAD is known as an "axis", it only gives +/-1 readings,
	// essentially making it a button. here, we get the axis numbers.
	public static final int DPAD_HORIZONTAL = 5;
	public static final int DPAD_VERTICAL = 6;
	
	/* Shoulder Buttons (Sit above the trigger buttons) */
	public static final int LEFT_SHOULDER_BUTTON = 5;
	public static final int RIGHT_SHOULDER_BUTTON = 6;
	
	/* Trigger buttons */
	public static final int LEFT_TRIGGER_BUTTON = 7;
	public static final int RIGHT_TRIGGER_BUTTON = 8;
	

	public Gamepad(int port) {
		super(port);
	}
	
	// treat these as buttons
	public boolean isDPadUpPressed() {
		return this.getRawAxis(DPAD_VERTICAL) < -0.5;
	}
	
	public boolean isDPadDownPressed() {
		return this.getRawAxis(DPAD_VERTICAL) > 0.5;
	}
	
	public boolean isDPadLeftPressed() {
		return this.getRawAxis(DPAD_HORIZONTAL) < -0.5;
	}
	
	public boolean isDPadRightPressed() {
		return this.getRawAxis(DPAD_HORIZONTAL) > 0.5;
	}
	
	public double getLeftHorizontalAxis() {
		return this.getRawAxis(LEFT_X_AXIS);
	}
	
	public double getRightHorizontalAxis() {
		return this.getRawAxis(RIGHT_X_AXIS);
	}
	
	public double getLeftVerticalAxis() {
		return -this.getRawAxis(LEFT_Y_AXIS);
	}
	
	public double getRightVerticalAxis() {
		return -this.getRawAxis(RIGHT_Y_AXIS);
	}
	
	public boolean isAButtonPressed() {
		return this.getRawButton(A_BUTTON);
	}
	
	public boolean getBButtonPressed() {
		return this.getRawButton(B_BUTTON);
	}
	
	public boolean getXButtonPressed() {
		return this.getRawButton(X_BUTTON);
	}
	
	public boolean getYButtonPressed() {
		return this.getRawButton(Y_BUTTON);
	}
	
	public boolean isLeftTriggerPressed() {
		return this.getRawButton(LEFT_TRIGGER_BUTTON);
	}
	
	public boolean isRightTriggerPressed() {
		return this.getRawButton(RIGHT_TRIGGER_BUTTON);
	}
	
	public boolean isLeftShoulderPressed() {
		return this.getRawButton(LEFT_SHOULDER_BUTTON);
	}
	
	public boolean isRightShoulderPressed() {
		return this.getRawButton(RIGHT_SHOULDER_BUTTON);
	}
}
