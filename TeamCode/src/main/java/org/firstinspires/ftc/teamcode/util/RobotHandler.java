package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotHandler {

    private ElapsedTime runtime = new ElapsedTime();
    private GlyphCatcher catcher = new GlyphCatcher();
    private OmniDrive wheels = new OmniDrive();
    private LinearSlide relicArm = new LinearSlide("relicArm");
    private LinearSlide lift = new LinearSlide("spool");
    private RelicPincher relicPincher = new RelicPincher();

    public RobotHandler() {}

    public void init(HardwareMap hardwareMap) {
        catcher.init(hardwareMap);
        wheels.init(hardwareMap);
        lift.init(hardwareMap);
        relicPincher.init(hardwareMap);
        catcher.openClaw();
        relicArm.init((hardwareMap));
    }

    public void driveForward() {
        wheels.driveForward();
    }
    public void driveBackward() {
        wheels.driveBackward();
    }
    public void driveLeft() {
        wheels.driveLeft();
    }
    public void driveRight() {
        wheels.driveRight();
    }
    public void turnLeft() {
        wheels.turnLeft();
    }
    public void turnRight() {
        wheels.turnRight();
    }
    public void stopWheels() {wheels.stop();}

    public void startWheelEncoder() {wheels.startEncoder();}
    public void driveForwardWithEncoders(double distanceInInches) {wheels.driveForwardWithEncoders(distanceInInches);}
    public void driveBackwardWithEncoders(double distanceInInches) {wheels.driveBackwardWithEncoders(distanceInInches);}
    public void turnRightWithEncoders(double rotations) {wheels.turnRightWithEncoders(rotations);}
    public void turnLeftWithEncoders(double rotations) {wheels.turnLeftWithEncoders(rotations);}
    public void driveRightWithEncoders(double distanceInInches) {wheels.driveRightWithEncoders(distanceInInches);}
    public void driveLeftWithEncoders(double distanceInInches) {wheels.driveLeftWithEncoders(distanceInInches);}
    public void stopWheelsWithEncoders() {wheels.stopEncoders();}

    public void extendGlyphtoN(int n) {
        lift.extendToN(n);
    }
    public void retractGlyphtoN(int n) {
        lift.retractToN(n);
    }
    public void extendRelictoN(int n) {
        relicArm.extendToN(n);
    }
    public void retractRelictoN(int n) {
        relicArm.extendToN(n);
    }

    public void grabGlyph() {
        catcher.closeClaw();
    }
    public void dropGlyph() {
        catcher.openClaw();
    }

    public void toggleRelicPincher() {
        relicPincher.pinch();
    }
    public void toggleRelicLift() {
        relicPincher.lift();
    }
    public void zeroPincher(){relicPincher.setZero();}
}
