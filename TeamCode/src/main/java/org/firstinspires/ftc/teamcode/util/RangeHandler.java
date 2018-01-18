package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
}
