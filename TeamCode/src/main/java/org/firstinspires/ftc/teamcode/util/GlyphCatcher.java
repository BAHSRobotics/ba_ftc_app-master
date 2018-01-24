package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

class GlyphCatcher {

    private Servo leftServo;
    private Servo rightServo;
    private String left;
    private String right;

    GlyphCatcher(String leftDevice, String rightDevice) {
        leftServo = null;
        rightServo = null;
        left = leftDevice;
        right = rightDevice;
    }
    void init(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,left);
        rightServo = hardwareMap.get(Servo.class, right);
    }
    void closeClaw() {
        leftServo.setPosition(0.7);
        rightServo.setPosition(0.3);
    }
    void openClaw() {
        leftServo.setPosition(0.30 + (1/6.0));
        rightServo.setPosition(0.7 - (1/6.0));
    }
}