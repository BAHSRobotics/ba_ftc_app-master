package org.firstinspires.ftc.teamcode.util;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity.TAG;

//TODO make more Polymorphic


/**
 * Created by 4014465 on 11/8/2017.
 */

public class LinearSlide {
    private DcMotor spool;
    //private int currentEncoderValue;
    private static final int REVOLUTION = 1650;
    private int n; //number of revolutions

    public LinearSlide() {
       spool = null;
    }
    public void init(HardwareMap hardwareMap) {
        spool = hardwareMap.get(DcMotor.class, "spool");
        spool.setMode(STOP_AND_RESET_ENCODER);
        spool.setMode(RUN_TO_POSITION);
    }
    public void extendOnce() {
        Log.v(TAG, "extendOnce called");
        spool.setTargetPosition(n * REVOLUTION);
        n++;
        spool.setPower(0.5);
        if (encoderWithinBounds()) {
            stop();
        }
    }
    public void retractOnce() {
        Log.v(TAG, "retractOnce called");
        n--;
        spool.setTargetPosition(n * REVOLUTION);
        spool.setPower(-0.5);
        if (encoderWithinBounds()) {
            stop();
        }
    }
    public void extendFull() {
        Log.v(TAG, "extendFull called");
        n = 3;
        spool.setTargetPosition(n * REVOLUTION);
        spool.setPower(.5);
    }
    public void retractFull(){
        Log.v(TAG, "retractFull called");
        spool.setTargetPosition(0);
        n = 0;
        spool.setPower(-.5);
    }
    public int getEncoder() {
        return spool.getCurrentPosition();
    }
    public int getTarget() {return spool.getTargetPosition();}
    public boolean encoderWithinBounds() {
        return getEncoder() < getTarget() + 15 && getTarget() - 15 < getEncoder();
    }
    public void stop() {
        spool.setPower(0);
    }


}
