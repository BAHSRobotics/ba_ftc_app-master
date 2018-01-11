package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.RobotHandler;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

public class Autonomous10526 extends LinearOpMode {
    private RobotHandler robot = new RobotHandler();
    private VuforiaTracker tracker = new VuforiaTracker();

    @Override
    public void runOpMode() throws InterruptedException {
        tracker.init();
        //if (tracker.vumarkFound() == RelicRecoveryVuMark.LEFT)
        //if (tracker.vumarkFound() == RelicRecoveryVuMark.CENTER)
       // if (tracker.vumarkFound() == RelicRecoveryVuMark.RIGHT)   //  robot.moveRight
        //else
    }
}
