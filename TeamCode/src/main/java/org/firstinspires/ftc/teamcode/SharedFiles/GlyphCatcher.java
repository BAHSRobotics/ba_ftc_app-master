package org.firstinspires.ftc.teamcode.SharedFiles;

/**
 * Created by Caleb Trevino on 11/8/2017.
 */

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GlyphCatcher {

    private Servo leftServo;
    private Servo rightServo;

    public GlyphCatcher(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class, "leftServo");
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