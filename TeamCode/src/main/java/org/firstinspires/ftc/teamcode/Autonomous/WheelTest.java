package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHandler;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Wheel Test", group = "Tests")
public class WheelTest extends LinearOpMode {

    RobotHandler robot = new RobotHandler();
    private VuforiaTracker tracker = new VuforiaTracker();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        robot.startWheelEncoder();
        while (opModeIsActive()) {
            if (gamepad1.left_bumper)
                robot.turnLeftWithEncoders(0.5);
            else if (gamepad1.dpad_up)
                robot.driveForwardWithEncoders(12);
            else if(gamepad1.dpad_left)
                robot.driveLeftWithEncoders(12);
            else if (gamepad1.dpad_down)
                robot.driveBackwardWithEncoders(12);
            else if (gamepad1.dpad_right)
                robot.driveRightWithEncoders(12);
            else if (gamepad1.right_bumper)
                robot.turnRightWithEncoders(1);
            else
                robot.stopWheelsWithEncoders();
        }
    }
}
