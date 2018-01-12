package org.firstinspires.ftc.teamcode.util;


import android.hardware.Sensor;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by 3115250 on 1/10/2018.
 */

public class ColorSensor {
    private ColorSensor colorSensor;
    private Servo sensorServo;

    ColorSensor() {
        colorSensor = null;
        sensorServo = null;
    }
    void init (HardwareMap hardwareMap){
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        sensorServo = hardwareMap.get(Servo.class,"sensorServo");
    }
    void MoveServo(){
       if (sensorServo.getPosition() == 1.0)   sensorServo.setPosition(0.37);
        else sensorServo.setPosition(1);
    }
    void SetOne (){
        sensorServo.setPosition(1);
    }
    void ColorData (){

    }
}
