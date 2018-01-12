package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHandler;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Autonomous Implementation Test", group = "Tests")
public class AutonomousImplementationTest extends LinearOpMode {

    RobotHandler robot = new RobotHandler();
    private VuforiaTracker tracker = new VuforiaTracker();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        robot.startWheelEncoder();
        robot.turnLeftWithEncoders(0.5);
        robot.driveForwardWithEncoders(6);
        robot.driveLeftWithEncoders(6);
        robot.driveBackwardWithEncoders(6);
        robot.driveRightWithEncoders(6);
        robot.turnRightWithEncoders(1);
        robot.stopWheelsWithEncoders();

        tracker.init();

    }
}
