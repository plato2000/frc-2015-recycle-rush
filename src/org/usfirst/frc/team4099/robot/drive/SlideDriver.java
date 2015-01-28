package org.usfirst.frc.team4099.robot.drive;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;

public class SlideDriver {
    private SpeedController sliderTalon;
    private RobotDrive arcadeDrive;

    public SlideDriver(RobotDrive arcadeDrive, int slider) {
        this.arcadeDrive = arcadeDrive;
        this.sliderTalon = new Talon(slider);
    }

    private void slide(double speed) {
        sliderTalon.set(speed);
    }

    public void slideDrive(double forward, double turn, double strafe) {
        arcadeDrive.arcadeDrive(forward, turn);
        this.slide(strafe);
    }
}
