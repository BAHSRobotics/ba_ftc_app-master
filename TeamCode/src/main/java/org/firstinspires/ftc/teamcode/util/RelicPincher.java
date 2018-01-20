package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static com.sun.tools.javac.main.Option.H;

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
        pincher.setPosition(0);
        wrist.setPosition(0);
    }
    void pinch() {
        if (pincher.getPosition() == 1.0)   pincher.setPosition(0);
        else if (pincher.getPosition() == 0)  pincher.setPosition(1);

    }
    void lift() {
        if (wrist.getPosition() == 0)   wrist.setPosition(.7);
        else if (wrist.getPosition() == .7)  wrist.setPosition(Servo.MAX_POSITION);
        else if (wrist.getPosition() == 1) wrist.setPosition(.7);
    }
    void setZero(){
        pincher.setPosition(0.0);
    }
}
