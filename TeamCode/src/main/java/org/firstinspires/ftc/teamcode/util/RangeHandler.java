package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

class RangeHandler {
    private ModernRoboticsI2cRangeSensor rangeSensor;
    RangeHandler() {
        rangeSensor = null;
    }
    void init(HardwareMap hardwareMap) {
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");
    }
    double getOpticalDistance() {
        return rangeSensor.cmOptical();
    }
    double getSonicDistance() {
        return rangeSensor.cmUltrasonic();
    }
    double getDistance() {return rangeSensor.getDistance(DistanceUnit.CM);}
    boolean isObjectWithinDistance(double distanceInCm) {return (getDistance() < distanceInCm);}
    boolean isObjectBeyondDistance(double distanceInCm) {return (getDistance() > distanceInCm);}
}
