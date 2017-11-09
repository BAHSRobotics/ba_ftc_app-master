package org.firstinspires.ftc.teamcode.SharedFiles;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by 3115250 on 11/8/2017.
 */

public class TankDrive {
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    public TankDrive(HardwareMap hardwareMap){
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
    }
    public void setDirection(){
        frontLeft.setDirection(Direction.FORWARD);
        frontRight.setDirection(Direction.FORWARD);
        backLeft.setDirection(Direction.FORWARD);
        backRight.setDirection(Direction.FORWARD);
    }

    public void driveForward(){
        frontLeft.setPower(-1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(1.0);
    }

    public void driveBackward(){
        frontLeft.setPower(1.0);
        frontRight.setPower(-1.0);
        backLeft.setPower(1.0);
        backRight.setPower(-1.0);
    }

    public void turnLeft(){
        frontLeft.setPower(1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(1.0);
        backRight.setPower(1.0);
    }
    public void turnRight(){
        frontLeft.setPower(-1.0);
        frontRight.setPower(-1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(-1.0);
    }
}
