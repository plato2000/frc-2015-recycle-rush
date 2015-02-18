package org.usfirst.frc.team4099.autonomous;

import org.usfirst.frc.team4099.camera.*;
import org.usfirst.frc.team4099.robot.drive.SlideDrive;

import edu.wpi.first.wpilibj.Timer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;


public class AutoDrive {
	private RobotCamera camera;
	private SlideDrive slideDrive;
	
	public Boolean inAutoZone = false;
	
	private DatagramSocket dsocket;
	private byte[] buffer = new byte[256];
	private DatagramPacket packet;
	
	
	public AutoDrive(RobotCamera camera, SlideDrive slideDrive) {
		this.camera = camera;
		this.slideDrive = slideDrive;
		try {
		      int port = 90;
		      dsocket = new DatagramSocket(port);
		      packet = new DatagramPacket(buffer, buffer.length);
		} catch (Exception e) {
				System.err.println(e);
		}
	}
	
	public void moveToAutoZone() {
		slideDrive.slideDrive(0, 1, 0);
		Timer.delay(.1);
		slideDrive.slideDrive(1, 0, 0);
		Timer.delay(1);
		slideDrive.slideDrive(0, -1, 0);
		Timer.delay(.1);
		slideDrive.slideDrive(0, 0, 0);
		inAutoZone = true;
	}
	
	public void autoDrive() {
		
		if(!inAutoZone) 
			moveToAutoZone();
		
	    try {
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