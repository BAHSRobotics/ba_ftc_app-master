package org.firstinspires.ftc.teamcode.util;

/**
 * Created by Caleb Trevino on 11/8/2017.
 */


import android.util.Log;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity.TAG;


class GlyphCatcher {

    private Servo leftServo;
    private Servo rightServo;

    GlyphCatcher() {
        leftServo = null;
        rightServo = null;
    }
    void init(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,"leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
    }
    void openClaw() {
        leftServo.setPosition(0.40);
        rightServo.setPosition(0.60);
    }
    void closeClaw() {
        leftServo.setPosition(1.0);
        rightServo.setPosition(0.0);
    }
}