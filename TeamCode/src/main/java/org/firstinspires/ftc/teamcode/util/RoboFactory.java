package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by caleb on 12/7/2017.
 */

public class RoboFactory {

    private GlyphCatcher catcher = new GlyphCatcher();
    private OmniDrive wheels = new OmniDrive();
    private LinearSlide lift = new LinearSlide("spool");
    private RelicPincher relicPincher = new RelicPincher();

    public RoboFactory() {

    }
    public void init(HardwareMap hardwareMap) {
        catcher.init(hardwareMap);
        wheels.init(hardwareMap);
        lift.init(hardwareMap);
        relicPincher.init(hardwareMap);
        catcher.openClaw();
    }
    public void driveForward() {
        wheels.driveForward();
    }
    public void driveBackward() {
        wheels.driveBackward();
    }
    public void turnLeft() {
        wheels.turnLeft();
    }
    public void turnRight() {
        wheels.turnRight();
    }
    public void driveLeft() {
        wheels.driveLeft();
    }
    public void driveRight() {
        wheels.driveRight();
    }
}
