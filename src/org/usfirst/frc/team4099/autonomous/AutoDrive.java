package org.usfirst.frc.team4099.autonomous;

import org.usfirst.frc.team4099.camera.*;
import org.usfirst.frc.team4099.robot.Elevator;
import org.usfirst.frc.team4099.robot.drive.SlideDrive;

import edu.wpi.first.wpilibj.Timer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class AutoDrive {
	private RobotCamera camera;
	private SlideDrive slideDrive;
	private Elevator elevator;
	private AutoMode mode;
	
	
	public Boolean movedToAutoZone = false;
	
	private DatagramSocket dsocket;
	private byte[] buffer = new byte[256];
	private DatagramPacket packet;
	
	private final double FORWARD_50_INCHES_TIME = 1.5;
	private final double PIVOT_90_DEGREES_TIME = 2.3;
	private final double SLIDE_50_INCHES_TIME = 4.0;
	
	
	
	public AutoDrive(RobotCamera camera, SlideDrive slideDrive) {
		this.camera = camera;
		this.slideDrive = slideDrive;
		String modeString = SmartDashboard.getString("AutoMode");
		if(modeString.equalsIgnoreCase("move")) {
			this.mode = AutoMode.MOVE_TO_AUTO_ZONE;
		} else if(modeString.equalsIgnoreCase("stack")) {
			System.err.println("ye fewl what you mad be?");
			this.mode = AutoMode.PICK_AND_STACK_TOTES_AND_MOVE_TO_AUTO_ZONE;
		} else {
			System.err.println("Defaulting to stack bin and tote and move");
			this.mode = AutoMode.PICK_UP_TOTE_AND_MOVE_TO_AUTO_ZONE;
		}
		/*
		try {
		      int port = 90;
		      dsocket = new DatagramSocket(port);
		      packet = new DatagramPacket(buffer, buffer.length);
		} catch (Exception e) {
				System.err.println(e);
		}
		*/
	}
	
	public void move(double forwardDistance, double pivotDegrees, double slideDistance) {
		double[] powers = new double[3];
		
		//Set which direction to run the motors
		if(forwardDistance > 0) {
			powers[0] = 1;
		} else if(forwardDistance < 0){
			powers[0] = -1;
		}
		else powers[0] = 0;
		
		if(pivotDegrees > 0) {
			powers[1] = 1;
		} else if(pivotDegrees < 0){
			powers[1] = -1;
		}
		else powers[1] = 0;
		
		if(slideDistance > 0) {
			powers[2] = 1;
		} else if(slideDistance < 0){
			powers[2] = -1;
		}
		else powers[2] = 0;
		
		
		double[] times = new double[3];
		times[0] = Math.abs(forwardDistance * FORWARD_50_INCHES_TIME / 50);
		times[1] = Math.abs(pivotDegrees * PIVOT_90_DEGREES_TIME / 90);
		times[2] = Math.abs(slideDistance * SLIDE_50_INCHES_TIME / 50);
		
		int[] order = new int[3];
		
		// Set the order for them to turn off
		if(times[0] >= times[1] && times[1] >= times[2]) {
			order[0] = 2;
			order[1] = 1;
			order[2] = 0;
		} else if (times[0] >= times[1] && times[0] >= times[2]) {
			order[0] = 1;
			order[1] = 2;
			order[2] = 0;
		} else if (times[1] >= times[0] && times[0] >= times[2]) {
			order[0] = 2;
			order[1] = 0;
			order[2] = 1;
		} else if (times[1] >= times[0] && times[1] >= times[2]) {
			order[0] = 0;
			order[1] = 2;
			order[2] = 1;
		} else if (times[2] >= times[0] && times[0] >= times[1]) {
			order[0] = 1;
			order[1] = 0;
			order[2] = 2;
		} else {
			order[0] = 0;
			order[1] = 1;
			order[2] = 2;
		}
		
		
		//Drive, wait, reset, repeat
		slideDrive.slideDrive(powers[0], powers[1], powers[2]);
		Timer.delay(times[order[0]]);
		powers[order[0]] = 0;
		slideDrive.slideDrive(powers[0], powers[1], powers[2]);
		Timer.delay(times[order[0]] - times[order[1]]);
		powers[order[1]] = 0;
		slideDrive.slideDrive(powers[0], powers[1], powers[2]);
		Timer.delay(times[order[0]] - times[order[1]] - times[order[2]]);
		slideDrive.slideDrive(0, 0, 0);
	}
	
	public void timingMoveToAutoZone() {
		// Into recycling bin
		move(20, 0, 0);
		// TODO: pick up bin
		elevator.setHeight(1);
		// Out of recycling bin
		move(-30, 0, 0);
		
		// Move over to tote
		move(0, 28, 0);
		move(17, 0, 0);
		//TODO: place recycling bin
		elevator.setHeight(.5);
		move(-17, 0, 0);
		elevator.setHeight(0);
		// Move into auto zone
		simpleMoveToAuto();
	}
	
	public void simpleMoveToAuto() {
		move(154, 0, 0);
	}
	
	public void turnAround() {
		move(0, 0, 90);
		move(-20, 0, 0);
		move(0, 0, 90);
	}
	
	
	public void autoDrive() {
		if(!movedToAutoZone) {
			switch(mode) {
			case MOVE_TO_AUTO_ZONE:
				simpleMoveToAuto();
				break;
			case PICK_UP_TOTE_AND_MOVE_TO_AUTO_ZONE:
				timingMoveToAutoZone();
				break;
			default:
				timingMoveToAutoZone();
			}
			movedToAutoZone = true;
		}
		
	    /*try {
			dsocket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String msg = new String(buffer, 0, packet.getLength());
	    System.out.println(packet.getAddress().getHostName() + ": " + msg);
	    
	    // Reset the length of the packet before reusing it.
	    packet.setLength(buffer.length);
		
	    //TODO: actually process message received, act on it
	    */
	    
		/*Direction dir;
		dir = camera.getDirection();
		switch(dir) {
			case LEFT:
				slideDrive.slideDrive(0,0,-.5/Driver.REDUCTION_FACTOR);
				break;
			case RIGHT:
				slideDrive.slideDrive(0,0,.5/Driver.REDUCTION_FACTOR);
				break;
			case NO_BOX:
				//move away, there is no yellow box in view
				//slideDrive.slideDrive(-.5 / REDUCTION_FACTOR, 0, 0);
				break;
			case FORWARD:
				//move forward to pick up box
				//slideDrive.slideDrive(.5 / REDUCTION_FACTOR, 0, 0);
				break;
		}
		*/
	}
}