package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Wheel Test", group = "Tests")
public class WheelTest extends LinearOpMode {

    RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        robot.startWheelEncoder();
        while (opModeIsActive()) {
            if (gamepad2.left_bumper)
                robot.turnLeftWithEncoders(0.5);
            else if (gamepad2.dpad_up)
                robot.driveForwardWithEncoders(12);
            else if(gamepad2.dpad_left)
                robot.driveLeftWithEncoders(12);
            else if (gamepad2.dpad_down)
                robot.driveBackwardWithEncoders(12);
            else if (gamepad2.dpad_right)
                robot.driveRightWithEncoders(12);
            else if (gamepad2.right_bumper)
                robot.turnRightWithEncoders(1);

            else if (gamepad1.dpad_up)
                robot.driveForward();
            else if (gamepad1.dpad_down)
                robot.driveBackward();
            else if (gamepad1.dpad_left)
                robot.driveLeft();
            else if (gamepad1.dpad_right)
                robot.driveRight();
            else if (gamepad1.left_bumper)
                robot.turnLeft();
            else if (gamepad1.right_bumper)
                robot.turnRight();
            else
                robot.stopWheelsWithEncoders();

        }
    }
}
