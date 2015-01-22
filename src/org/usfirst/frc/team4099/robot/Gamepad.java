/**
 * Gamepad class. For use for the Logitech Gamepad for FRC.
 * Usage: Gamepad gp = new Gamepad
 * 
 */

package org.usfirst.frc.team4099.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends Joystick {
	
	/* Joystick Axes */
	public static final int LEFT_X_AXIS = 1;
	public static final int LEFT_Y_AXIS = 2;
	
	public static final int RIGHT_X_AXIS = 3;
	public static final int RIGHT_Y_AXIS = 4;
	
	/* Joystick Click (Buttons) */
	public static final int LEFT_JOYSTICK_BUTTON = 11;
	public static final int RIGHT_JOYSTICK_BUTTON = 12;
	
	/* Buttons (Right Side) */
	public static final int X_BUTTON = 1;
	public static final int Y_BUTTON = 4;
	public static final int A_BUTTON = 2;
	public static final int B_BUTTON = 3;
	
	public static final int DPAD_HORIZONTAL = 5;
	public static final int DPAD_VERTICAL = 6;
	
	/* Shoulder Buttons */
	public static final int LEFT_SHOULDER_BUTTON = 5;
	public static final int RIGHT_SHOULDER_BUTTON = 6;
	
	// TODO: Figure out what these do!
	public static final int LEFT_TRIGGER_BUTTON = 7;
	public static final int RIGHT_TRIGGER_BUTTON = 8;
	
	
	public Gamepad(int port) {
		super(port);
	}
	
	private boolean isJoelHot() {
		return true;
	}
	
	private boolean isDPadUp() {
		return this.getRawAxis(DPAD_VERTICAL) < -0.5;
	}
	
	private boolean isDPadDown() {
		return this.getRawAxis(DPAD_VERTICAL) > 0.5;
	}
	
	private boolean isDPadLeft() {
		return this.getRawAxis(DPAD_HORIZONTAL) < -0.5;
	}
	
	private boolean isDPadRight() {
		return this.getRawAxis(DPAD_HORIZONTAL) > 0.5;
	}
	
	private double getLeftHorizontalAxis() {
		return this.getRawAxis(LEFT_X_AXIS);
	}
	
	private double getRightHorizontalAxis() {
		return this.getRawAxis(RIGHT_X_AXIS);
	}
	
	private double getLeftVerticalAxis() {
		return -this.getRawAxis(LEFT_Y_AXIS);
	}
	
	private double getRightVerticalAxis() {
		return -this.getRawAxis(RIGHT_Y_AXIS);
	}
	
	private boolean getAButton() {
		return this.getRawButton(A_BUTTON);
	}
	
	private boolean getBButton() {
		return this.getRawButton(B_BUTTON);
	}
	
	private boolean getXButton() {
		return this.getRawButton(X_BUTTON);
	}
	
	private boolean getYButton() {
		return this.getRawButton(Y_BUTTON);
	}
}
