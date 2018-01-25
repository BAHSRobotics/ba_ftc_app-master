package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;

class LinearSlide {
    private DcMotor motor;
    //private int currentEncoderValue;
    private static final int REVOLUTION = 1650;
    private String device;
    private static final int MAX_VALUE = 1650 * 3;
    private static final int CHANGE_IN_THETA = 10;

    LinearSlide(String deviceName) {
       motor = null;
       device = deviceName;
    }
    void init(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, device);
        motor.setMode(STOP_AND_RESET_ENCODER);
        motor.setMode(RUN_TO_POSITION);
    }
    void extend() {
        if(motor.getCurrentPosition() <= MAX_VALUE - CHANGE_IN_THETA)
        motor.setTargetPosition(motor.getCurrentPosition() + CHANGE_IN_THETA);
        motor.setPower(1.0);
    }
    void retract() {
        if (motor.getCurrentPosition() >=  CHANGE_IN_THETA)
            motor.setTargetPosition(motor.getCurrentPosition() - CHANGE_IN_THETA);
        else
            motor.setTargetPosition(0);
        motor.setPower(-1.0);
    }
    void extendToN(double n) {
        motor.setTargetPosition((int) n * REVOLUTION);
        motor.setPower(1.0);
    }
    void retractToN(int n) {
        motor.setTargetPosition( n * REVOLUTION);
        motor.setPower(-1.0);
    }
    int getEncoder() {
        return motor.getCurrentPosition();
    }
    int getTarget() {return motor.getTargetPosition();}

    boolean encoderWithinBounds() {
        return (getEncoder() < getTarget() + 15) && (getTarget() - 15 < getEncoder());
    }
    void stop() {
        motor.setPower(0);
    }
}
