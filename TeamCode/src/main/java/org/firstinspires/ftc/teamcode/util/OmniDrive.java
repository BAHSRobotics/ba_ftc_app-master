package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


class







OmniDrive {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    int prevEncoderValue;
    int encoderValue;

    OmniDrive() {
        frontLeft = null;
        frontRight = null;
        backLeft = null;
        backRight = null;
    }

    void init(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
    }

    //JOYSTICK MOTION
    void moveRobotRightSide(double theta, double r) {
        double newTheta = theta - 0.785398163; //rotate coordinate system 45 degrees counterclockwise pi/4
        frontRight.setPower(r*Math.sin(newTheta));
        backRight.setPower(r*Math.cos(newTheta));
    }

    void moveRobotLeftSide(double theta, double r) {
        double newTheta = theta - 0.785398163; //rotate coordinate system 45 degrees counterclockwise pi/4
        frontLeft.setPower(-r*Math.cos(newTheta));
        backLeft.setPower(-r*Math.sin(newTheta));
    }

    //DPAD MOTION
    void driveForward() {
        frontLeft.setPower(-1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(1.0);
    }
    void driveBackward() {
        frontLeft.setPower(1.0);
        frontRight.setPower(-1.0);
        backLeft.setPower(1.0);
        backRight.setPower(-1.0);
    }
    void driveRight() {
        frontLeft.setPower(-1.0);
        backLeft.setPower(1.0);
        backRight.setPower(1.0);
        frontRight.setPower(-1.0);
    }
    void driveLeft() {
        frontLeft.setPower(1.0);
        backLeft.setPower(-1.0);
        frontRight.setPower(1.0);
        backRight.setPower(-1.0);
    }
    void turnLeft() {
        frontLeft.setPower(1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(1.0);
        backRight.setPower(1.0);
    }
    void turnRight() {
        frontLeft.setPower(-1.0);
        frontRight.setPower(-1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(-1.0);
    }
    void stop() {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }

    //ENCODER BASED MOTION
    void startEncoder() {
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void driveForwardWithEncoders(double distanceInInches) {
        frontRight.setTargetPosition(convertInchesToEncoder(distanceInInches));
        while ((encoderValue = frontRight.getCurrentPosition()) < frontRight.getTargetPosition()) {
            driveForward();
        }
        stopEncoders();
    }
    void driveBackwardWithEncoders(double distanceInInches) {
        frontRight.setTargetPosition(-convertInchesToEncoder(distanceInInches));
        while (frontRight.getCurrentPosition() > frontRight.getTargetPosition()) {
            driveBackward();
        }
        stopEncoders();
    }
    void driveLeftWithEncoders(double distanceInInches) {
        frontRight.setTargetPosition(-convertInchesToLossyEncoder(distanceInInches));
        while (frontRight.getCurrentPosition() > frontRight.getTargetPosition()) {
            driveLeft();
        }
        stopEncoders();
    }
    void driveRightWithEncoders(double distanceInInches) {
        frontRight.setTargetPosition(convertInchesToLossyEncoder(distanceInInches));
        while (frontRight.getCurrentPosition() < frontRight.getTargetPosition()) {
            driveRight();
        }
        stopEncoders();
    }
    void turnLeftWithEncoders(double target) {
        frontRight.setTargetPosition(convertRotationsToEncoder(target));
        while (frontRight.getCurrentPosition() < frontRight.getTargetPosition()) {
            turnLeft();
        }
        stopEncoders();
    }
    void turnRightWithEncoders(double target) {
        frontRight.setTargetPosition(-convertRotationsToEncoder(target));
        while (frontRight.getCurrentPosition() > frontRight.getTargetPosition()) {
            turnRight();
        }
        stopEncoders();
    }
    void stopEncoders() {
        stop();
        startEncoder();
    }

    int getEncoderValue() {
        return frontRight.getCurrentPosition();
    }
    private int convertInchesToEncoder(double distance) {
        return (int)(89.78 * distance); //TODO test maths
    }
    private int convertInchesToLossyEncoder(double distance) {
        return (int) (156.25 * distance * 0.6);
    }
    private int convertRotationsToEncoder(double rotations){return (int)(6400 * rotations );}
}