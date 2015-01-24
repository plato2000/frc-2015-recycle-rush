/**
 * Gamepad class. For use for the Logitech Gamepad for FRC.
 * Usage: Gamepad gp = new Gamepad(port);
 * 
 * Defines common functionality such as getting the button presses
 *     and axis values.
 *     
 * IMPORTANT:
 * THE GAMEPAD MUST BE IN X EMULATOR MODE (SWITCH ON BACK) WITH THE MODE LIGHT <i>OFF</i>.
 */

package org.usfirst.frc.team4099.control;

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
	public static final int A_BUTTON = 0;
	public static final int B_BUTTON = 1;
	public static final int X_BUTTON = 2;
	public static final int Y_BUTTON = 3;
	
	/* DPAD */
	public static final int DPAD_POV = 0;
	public static final int DPAD_NORTH = 0;
	public static final int DPAD_NORTHEAST = 45;
	public static final int DPAD_EAST = 90;
	public static final int DPAD_SOUTHEAST = 135;
	public static final int DPAD_SOUTH = 180;
	public static final int DPAD_SOUTHWEST = 225;
	public static final int DPAD_WEST = 270;
	public static final int DPAD_NORTHWEST = 315;
	
	/* Shoulder Buttons (Sit above the trigger axises) */
	public static final int LEFT_SHOULDER_BUTTON = 4;
	public static final int RIGHT_SHOULDER_BUTTON = 5;
	
	/* Trigger Axises */
	public static final int LEFT_TRIGGER_AXIS = 2;
	public static final int RIGHT_TRIGGER_AXIS = 3;
	
	public Gamepad(int port) {
		super(port);
	}
	
	// treat these as buttons
	public boolean isDPadUpPressed() {
		int dir = this.getPOV(DPAD_POV);
		return (dir == DPAD_NORTH || 
				dir == DPAD_NORTHWEST ||
				dir == DPAD_NORTHEAST);
	}
	
	public boolean isDPadDownPressed() {
		int dir = this.getPOV(DPAD_POV);
		return (dir == DPAD_SOUTH ||
				dir == DPAD_SOUTHWEST ||
				dir == DPAD_SOUTHEAST);
	}
	
	public boolean isDPadLeftPressed() {
		int dir = this.getPOV(DPAD_POV);
		return (dir == DPAD_WEST || 
				dir == DPAD_NORTHWEST ||
				dir == DPAD_SOUTHWEST);
	}
	
	public boolean isDPadRightPressed() {
		int dir = this.getPOV(DPAD_POV);
		return (dir == DPAD_EAST ||
				dir == DPAD_NORTHEAST ||
				dir == DPAD_SOUTHEAST);
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
	
	public boolean isBButtonPressed() {
		return this.getRawButton(B_BUTTON);
	}
	
	public boolean isXButtonPressed() {
		return this.getRawButton(X_BUTTON);
	}
	
	public boolean isYButtonPressed() {
		return this.getRawButton(Y_BUTTON);
	}
	
	public boolean isLeftTriggerPressed() {
		return this.getRawButton(LEFT_TRIGGER_AXIS);
	}
	
	public boolean isRightTriggerPressed() {
		return this.getRawButton(RIGHT_TRIGGER_AXIS);
	}
	
	public boolean isLeftShoulderPressed() {
		return this.getRawButton(LEFT_SHOULDER_BUTTON);
	}
	
	public boolean isRightShoulderPressed() {
		return this.getRawButton(RIGHT_SHOULDER_BUTTON);
	}
}