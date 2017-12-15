package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by 4014465 on 11/16/2017.
 */

 class OmniDrive {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

         OmniDrive(){
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

         void driveForward(){
            frontLeft.setPower(-1.0);
            frontRight.setPower(1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(1.0);
        }

         void driveBackward(){
            frontLeft.setPower(1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(1.0);
            backRight.setPower(-1.0);
        }
         void driveLeft() {
            frontLeft.setPower(-1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(1.0);
            backRight.setPower(1.0);
        }
         void driveRight() {
            frontLeft.setPower(1.0);
            frontRight.setPower(1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(-1.0);
        }
         void turnLeft(){
            frontLeft.setPower(1.0);
            frontRight.setPower(1.0);
            backLeft.setPower(1.0);
            backRight.setPower(1.0);
        }
         void turnRight(){
            frontLeft.setPower(-1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(-1.0);
        }
         void stop() {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
     void driveForwardWithEncoders(){
        frontLeft.setPower(-1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(1.0);
    }

     void driveBackwardWithEncoders(){
        frontLeft.setPower(1.0);
        frontRight.setPower(-1.0);
        backLeft.setPower(1.0);
        backRight.setPower(-1.0);
    }
     void driveLeftWithEncoders() {
        frontLeft.setPower(-1.0);
        frontRight.setPower(-1.0);
        backLeft.setPower(1.0);
        backRight.setPower(1.0);
    }
     void driveRightWithEncoders() {
        frontLeft.setPower(1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(-1.0);
    }
     void turnLeftWithEncoders(){
        frontLeft.setPower(1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(1.0);
        backRight.setPower(1.0);
    }
     void turnRightWithEncoders(){
        frontLeft.setPower(-1.0);
        frontRight.setPower(-1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(-1.0);
    }
     void stopEncoders() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

}
