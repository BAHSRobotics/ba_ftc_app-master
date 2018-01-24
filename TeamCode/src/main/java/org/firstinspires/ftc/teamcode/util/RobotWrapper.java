package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotWrapper {

    private RuntimeHandler runtime = new RuntimeHandler();
    private GlyphCatcher topCatcher = new GlyphCatcher("topLeft", "topRight");
    private GlyphCatcher bottomCatcher = new GlyphCatcher("bottomLeft", "bottomRight");
    private GlyphCatcher backCatcher = new GlyphCatcher("backLeft", "backRight");
    private OmniDrive wheels = new OmniDrive();
    private LinearSlide relicArm = new LinearSlide("relicArm");
    private LinearSlide lift = new LinearSlide("spool");
    private RelicPincher relicPincher = new RelicPincher();
    private RangeHandler range = new RangeHandler();

    public RobotWrapper() {}
    // Initialization
    public void init(HardwareMap hardwareMap) {
        topCatcher.init(hardwareMap);
        bottomCatcher.init(hardwareMap);
        backCatcher.init(hardwareMap);
        wheels.init(hardwareMap);
        lift.init(hardwareMap);
        relicPincher.init(hardwareMap);
        topCatcher.openClaw();
        bottomCatcher.openClaw();
        backCatcher.openClaw();
        relicArm.init(hardwareMap);
        range.init(hardwareMap);
    }
    // Drive System
    public void driveForward()
        {wheels.driveForward();}
    public void driveBackward()
        {wheels.driveBackward();}
    public void driveLeft()
        {wheels.driveLeft();}
    public void driveRight()
        {wheels.driveRight();}
    public void turnLeft()
        {wheels.turnLeft();}
    public void turnRight()
        {wheels.turnRight();}
    public void stopWheels()
        {wheels.stop();}
    /*
     * Encoded Drive System
     */
    public void startWheelEncoder()
        {wheels.startEncoder();}
    public void driveForwardWithEncoders(double distanceInInches)
        {wheels.driveForwardWithEncoders(distanceInInches);}
    public void driveBackwardWithEncoders(double distanceInInches)
        {wheels.driveBackwardWithEncoders(distanceInInches);}
    public void turnRightWithEncoders(double rotations)
        {wheels.turnRightWithEncoders(rotations);}
    public void turnLeftWithEncoders(double rotations)
        {wheels.turnLeftWithEncoders(rotations);}
    public void driveRightWithEncoders(double distanceInInches)
        {wheels.driveRightWithEncoders(distanceInInches);}
    public void driveLeftWithEncoders(double distanceInInches)
        {wheels.driveLeftWithEncoders(distanceInInches);}
    public void stopWheelsWithEncoders()
        {wheels.stopEncoders();}
    public int getEncoderValue() {
        return wheels.getEncoderValue();
    }
    //Linear Slides
    public void extendGlyph() {lift.extend();}
    public void retractGlyph() {lift.retract();}
    public void extendGlyphtoN(double n) {
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
    public void extendRelic() {relicArm.extend();}
    public void retractRelic() {relicArm.retract();}
    //Glyph Grabber
    public void grabTopGlyph() {
        topCatcher.closeClaw();
    }
    public void grabBackGlyph() {

    }
    public void dropBackGlyph() {

    }
    public void dropTopGlyph() {
        topCatcher.openClaw();
    }
    public void grabBottomGlyph() {
        bottomCatcher.closeClaw();
    }
    public void dropBottomGlyph() {
        bottomCatcher.closeClaw();
    }
    //Relic Claw
    public void toggleRelicPincher() {
        relicPincher.pinch();
    }
    public void toggleRelicLift() {
        relicPincher.lift();
    }
    public void zeroPincher(){relicPincher.setZero();}
//=================================================================================================
    // Inputs
    public boolean isGlyphWithin(double distanceInCm){return range.isObjectWithinDistance(distanceInCm);}
    public double nearestGlyph(){return range.getDistance();}
    public void resetRuntime() {runtime.resetRuntime();}
    public double getRuntime() {return runtime.timeInMs();}
    public boolean runtimeLessThan(double timeInSeconds) {return runtime.lessThan(timeInSeconds);}
    public boolean runtimeGreaterThan(double timeInSeconds) {return runtime.greaterThan(timeInSeconds);}

}
