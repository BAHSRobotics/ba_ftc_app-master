package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by 4014465 on 11/28/2017.
 */

public class GyroHandler {
    private GyroSensor gyro;
    private Telemetry telemetry;
    public GyroHandler() {
        gyro = null;
    }
    public void init(HardwareMap hardwareMap) {
        gyro = hardwareMap.get(GyroSensor.class,"gyro");
        gyro.calibrate();
        telemetry.addData();
    }
    public void
    public void getValue() {

    }
}
