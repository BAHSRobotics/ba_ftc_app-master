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
        ++n;
        spool.setTargetPosition(n * REVOLUTION);
        spool.setPower(0.5);
        if (encoderWithinBounds()) {
            stop();
        }
    }
    public void retractOnce() {
        --n;
        spool.setTargetPosition(n * REVOLUTION);
        spool.setPower(-0.5);
        if (encoderWithinBounds()) {
            stop();
        }
    }
    public void extendFull() {
        n = 3;
        spool.setTargetPosition(n * REVOLUTION);
        spool.setPower(1.0);
    }
    public void retractFull(){
        n = 0;
        spool.setTargetPosition(n * REVOLUTION);
        spool.setPower(-1.0);
    }
    public int getEncoder() {
        return spool.getCurrentPosition();
    }
    public int getTarget() {return spool.getTargetPosition();}
    public boolean encoderWithinBounds() {
        return (getEncoder() < getTarget() + 15) && (getTarget() - 15 < getEncoder());
    }
    public void stop() {
        spool.setPower(0);
    }


}
