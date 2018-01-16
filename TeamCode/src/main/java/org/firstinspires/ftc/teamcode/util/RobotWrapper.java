package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotWrapper {

    private ElapsedTime runtime = new ElapsedTime();
    private GlyphCatcher catcher = new GlyphCatcher();
    private OmniDrive wheels = new OmniDrive();
    private LinearSlide relicArm = new LinearSlide("relicArm");
    private LinearSlide lift = new LinearSlide("spool");
    private RelicPincher relicPincher = new RelicPincher();
    private TouchHandler touch = new TouchHandler();

    public RobotWrapper() {}
    // Initialization
    public void init(HardwareMap hardwareMap) {
        catcher.init(hardwareMap);
        wheels.init(hardwareMap);
        lift.init(hardwareMap);
        relicPincher.init(hardwareMap);
        catcher.openClaw();
        relicArm.init((hardwareMap));
        touch.init(hardwareMap);
        //runtime.reset();
    }
    // Drive System
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
    // Encoded Drive System
    public void startWheelEncoder() {wheels.startEncoder();}
    public void driveForwardWithEncoders(double distanceInInches) {wheels.driveForwardWithEncoders(distanceInInches);}
    public void driveBackwardWithEncoders(double distanceInInches) {wheels.driveBackwardWithEncoders(distanceInInches);}
    public void turnRightWithEncoders(double rotations) {wheels.turnRightWithEncoders(rotations);}
    public void turnLeftWithEncoders(double rotations) {wheels.turnLeftWithEncoders(rotations);}
    public void driveRightWithEncoders(double distanceInInches) {wheels.driveRightWithEncoders(distanceInInches);}
    public void driveLeftWithEncoders(double distanceInInches) {wheels.driveLeftWithEncoders(distanceInInches);}
    public void stopWheelsWithEncoders() {wheels.stopEncoders();}
    //Linear Slides
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
    //Glyph Grabber
    public void grabGlyph() {
        catcher.closeClaw();
    }
    public void dropGlyph() {
        catcher.openClaw();
    }
    //Relic Claw
    public void toggleRelicPincher() {
        relicPincher.pinch();
    }
    public void toggleRelicLift() {
        relicPincher.lift();
    }
    public void zeroPincher(){relicPincher.setZero();}
    // Inputs
    public boolean touchSensorPressed(){return touch.isPressed();}
    public boolean touchSensorNotPressed(){return touch.isNotPressed();}
    public void resetRuntime() {runtime.reset();}
    public double getRuntime() {return runtime.milliseconds();}

}