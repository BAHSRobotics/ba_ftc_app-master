package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

class TouchHandler {
    private TouchSensor touch;

    TouchHandler() {
        touch = null;
    }
    void init(HardwareMap hardwareMap) {touch = hardwareMap.get(TouchSensor.class, "touch");}
    boolean isPressed() {
        return touch.isPressed();
    }
    boolean isNotPressed() {
        return !touch.isPressed();
    }
}
