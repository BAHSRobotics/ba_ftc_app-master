package org.firstinspires.ftc.teamcode.util;

/**
 * Created by Caleb Trevino on 11/8/2017.
 */


import android.util.Log;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity.TAG;


public class GlyphCatcher {

    private Servo leftServo;
    private Servo rightServo;

    public GlyphCatcher() {
        leftServo = null;
        rightServo = null;
    }
    public void init(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,"leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
    }
    public void openClaw() {
        leftServo.setPosition(0.40);
        rightServo.setPosition(0.60);
    }
    public void closeClaw() {
        leftServo.setPosition(1.0);
        rightServo.setPosition(0.0 );
    }
}