package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

class RuntimeHandler {
    private ElapsedTime runtime = new ElapsedTime();
    void resetRuntime() {
        runtime.reset();
    }
    double timeInMs() {
        return runtime.milliseconds();
    }
    boolean lessThan(double timeInSeconds) {
        return runtime.milliseconds() < timeInSeconds * 1000;
    }
    boolean greaterThan(double timeInSeconds) {
        return runtime.milliseconds() > timeInSeconds * 1000;
    }
}
