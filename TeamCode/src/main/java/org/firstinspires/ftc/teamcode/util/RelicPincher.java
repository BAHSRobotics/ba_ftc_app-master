package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by 4014465 on 12/4/2017.
 */

class RelicPincher {
    private Servo wrist;
    private Servo pincher;

    RelicPincher() {
        wrist = null;
        pincher = null;
    }
    void init(HardwareMap hardwareMap){
        wrist = hardwareMap.get(Servo.class, "wrist");
        pincher = hardwareMap.get(Servo.class, "pincher");
        pincher.setPosition(.332);
        wrist.setPosition(1);
    }
    void pinch() {
        if (pincher.getPosition() == 1.0)   pincher.setPosition(0.332);
        else if (pincher.getPosition() == 0.332)  pincher.setPosition(1);

    }
    void lift() {
        if (wrist.getPosition() == 1.0)   wrist.setPosition(0);
        else if (wrist.getPosition() == 0)  wrist.setPosition(1);
    }
}
