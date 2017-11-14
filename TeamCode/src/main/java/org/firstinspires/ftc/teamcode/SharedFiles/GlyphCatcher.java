package org.firstinspires.ftc.teamcode.SharedFiles;

/**
 * Created by Caleb Trevino on 11/8/2017.
 */

import android.util.Log;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.util.RobotLog.TAG;

public class GlyphCatcher {

    private Servo leftServo;
    private Servo rightServo;


    public GlyphCatcher(Servo left, Servo right) {
        leftServo = left;
        rightServo = right;
    }
    public void init(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,"leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
    }
    public void openClaw() {
        leftServo.setPosition(0.0);
        rightServo.setPosition(1.0);
    }
    public void closeClaw() {
        leftServo.setPosition(1.0);
        rightServo.setPosition(0.0);
    }
}