package org.firstinspires.ftc.teamcode.SharedFiles;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;

/**
 * Created by 4014465 on 11/8/2017.
 */

public class LinearSlide {
    private DcMotor spool;
    //private int currentEncoderValue;
    private int revolution = 1650;
    private int n; //number of revolutions

    public LinearSlide(HardwareMap hardwareMap) {
        spool = hardwareMap.get(DcMotor.class, "spool");
        spool.setMode(STOP_AND_RESET_ENCODER);
        spool.setMode(RUN_TO_POSITION);

    }
    public void extendOnce() {
        /*spool.setTargetPosition(currentEncoderValue + 1650); // Turn the motor 1 revolution
        currentEncoderValue = spool.getCurrentPosition();*/
        spool.setTargetPosition(n * revolution);
        n++;
        spool.setPower(0.5);
    }
    public void retractOnce() {
        n--;
        spool.setTargetPosition(n * revolution);
        spool.setPower(0.5);
    }
    public void extendFull() {
        spool.setTargetPosition(4 * revolution);
        n= 4;
    }
    public void retractFull(){
        spool.setTargetPosition(0);
        n= 0;
    }


}
