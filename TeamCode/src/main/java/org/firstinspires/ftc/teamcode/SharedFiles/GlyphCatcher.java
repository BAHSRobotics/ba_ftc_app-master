package org.firstinspires.ftc.teamcode.SharedFiles;

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
        Log.v(TAG, "openClaw called");
        leftServo.setPosition(0.0);
        rightServo.setPosition(1.0);
    }
    public void closeClaw() {
        Log.v(TAG, "closeClaw called");
        leftServo.setPosition(1.0);
        rightServo.setPosition(0.0);
    }
}