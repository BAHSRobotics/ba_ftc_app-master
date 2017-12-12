package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;


/**
 * Created by 4014465 on 11/8/2017.
 */

public class LinearSlide {
    private DcMotor motor;
    //private int currentEncoderValue;
    private static final int REVOLUTION = 1650;
    private String device;

    public LinearSlide(String deviceName) {
       motor = null;
       device = deviceName;
    }
    public void init(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, device);
        motor.setMode(STOP_AND_RESET_ENCODER);
        motor.setMode(RUN_TO_POSITION);
    }
    public void extendToN(int n) {
        motor.setTargetPosition( n * REVOLUTION);
        motor.setPower(1.0);
    }
    public void retractToN(int n) {
        motor.setTargetPosition( n * REVOLUTION);
        motor.setPower(-1.0);
    }
    public int getEncoder() {
        return motor.getCurrentPosition();
    }
    public int getTarget() {return motor.getTargetPosition();}

    public boolean encoderWithinBounds() {
        return (getEncoder() < getTarget() + 15) && (getTarget() - 15 < getEncoder());
    }
    public void stop() {
        motor.setPower(0);
    }
}
