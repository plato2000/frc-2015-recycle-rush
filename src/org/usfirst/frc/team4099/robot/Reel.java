package org.usfirst.frc.team4099.robot;

import org.usfirst.frc.team4099.control.FlightStick;
import org.usfirst.frc.team4099.control.Gamepad;

import edu.wpi.first.wpilibj.Relay;

public class Reel {
	private static int LEFT_RELAY_PIN = 0;
	private static int RIGHT_RELAY_PIN = 1;
	private Relay leftRelay;
	private Relay rightRelay;
	
	public Reel() {
		leftRelay = new Relay(LEFT_RELAY_PIN);
		rightRelay = new Relay(RIGHT_RELAY_PIN);
	}
	
	public void move(Gamepad control, FlightStick flight) {
		if (control.isXButtonPressed()) {
			//reel stuff in
			leftRelay.set(Relay.Value.kOn);
			rightRelay.set(Relay.Value.kOn);
		} else {
			leftRelay.set(Relay.Value.kOff);
			rightRelay.set(Relay.Value.kOff);
		}
	}
}
