package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by 4014465 on 12/4/2017.
 */

public class RelicPincher {
    private Servo wrist;
    private Servo pincher;

    public RelicPincher() {
        wrist = pincher = null;
    }
    public void init(HardwareMap hardwareMap){
        wrist = hardwareMap.get(Servo.class, "wrist");
        pincher = hardwareMap.get(Servo.class, "pincher");
    }
    public void pinch() {

    }
}
