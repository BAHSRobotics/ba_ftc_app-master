package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "DanceBot", group = "Tests")
public class DanceBot extends LinearOpMode {

    private RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        tracker.init();
        telemetry.update();

        while (opModeIsActive()) {
            if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                robot.driveRightWithEncoders(6);
                robot.turnRightWithEncoders(1);
                robot.turnLeftWithEncoders(1);
                robot.driveLeftWithEncoders(6);
            }
            else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                robot.driveLeftWithEncoders(6);
                robot.turnLeftWithEncoders(1);
                robot.turnRightWithEncoders(1);
                robot.driveRightWithEncoders(6);
            }
        }
    }
}