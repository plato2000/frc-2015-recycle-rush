package org.usfirst.frc.team4099.control;

import edu.wpi.first.wpilibj.Joystick;

public class FlightStick extends Joystick {
	public static final int FRONT_BUTTON = 0;
	public static final int STICK_HORIZONTAL = 1;
	public static final int STICK_VERTICAL = 1;
	public static final int STICK_TWIST = 2;
	public static final int SLIDER = 3;
	public static final int POV_NORTH = 0;
	public static final int POV_NORTHEAST = 45;
	public static final int POV_EAST = 90;
	public static final int POV_SOUTHEAST = 135;
	public static final int POV_SOUTH = 180;
	public static final int POV_SOUTHWEST = 225;
	public static final int POV_WEST = 270;
	public static final int POV_NORTHWEST = 315;
	
	public FlightStick(int port) {
		super(port);
	}
	
	public double getHorizontalAxis() {
		return this.getRawAxis(STICK_HORIZONTAL);
	}
	
	public double getVerticalAxis() {
		return this.getRawAxis(STICK_VERTICAL);
	}
	
	public double getTwist() {
		return this.getRawAxis(STICK_TWIST);
	}
	
	public double getSlider() {
		return this.getRawAxis(SLIDER)-1;
	}
	
	public boolean isFrontButtonPressed() {
		return this.getRawButton(FRONT_BUTTON);
	}
	
	public boolean isPOVDownPressedStrict() {
		int dir = this.getPOV();
		return (dir == POV_SOUTH);
	}
}
