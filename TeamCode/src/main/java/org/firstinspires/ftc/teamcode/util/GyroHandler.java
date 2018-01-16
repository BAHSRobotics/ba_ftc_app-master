package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by 4014465 on 11/28/2017.
 */

class GyroHandler {
    private GyroSensor gyro;
    private Telemetry telemetry;
    GyroHandler() {
        gyro = null;
    }
    void init(HardwareMap hardwareMap) {
        gyro = hardwareMap.get(GyroSensor.class,"gyro");
        gyro.calibrate();
     //   telemetry.addData();
    }
    //public void
    //public void getValue() {

    }
//}
