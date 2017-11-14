package org.firstinspires.ftc.teamcode.SharedFiles;

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
    private int revolution = 1650;
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
        spool.setTargetPosition(n * revolution);
        n++;
        spool.setPower(0.5);
    }
    public void retractOnce() {
        Log.v(TAG, "retractOnce called");
        n--;
        spool.setTargetPosition(n * revolution);
        spool.setPower(-0.5);
    }
    public void extendFull() {
        Log.v(TAG, "extendFull called");
        n = 3;
        spool.setTargetPosition(n * revolution);
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
    public void stop() {
        spool.setPower(0);
    }


}
