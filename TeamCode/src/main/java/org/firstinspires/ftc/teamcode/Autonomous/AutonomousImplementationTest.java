package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.RobotHandler;

@Autonomous(name = "Autonomous Implementation Test", group = "Tests")
public class AutonomousImplementationTest extends LinearOpMode {

    RobotHandler robot = new RobotHandler();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        robot.startWheelEncoder();
        robot.driveForwardWithEncoders(6);
        robot.driveLeftWithEncoders(6);
        robot.driveBackwardWithEncoders(6);
        robot.driveRightWithEncoders(6);
        robot.turnLeftWithEncoders(1650);
        robot.turnRightWithEncoders(1650);
        robot.stopWheelsWithEncoders();
    }
}
