package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "HuntBot", group = "Tests")
public class WiggleBot extends LinearOpMode {
    // A proof of concept and test file to test use of range sensor in autonomous
    private RobotWrapper robot = new RobotWrapper();

    int zComponent, zDistance, xComponent, xDistance, turnComponent, turnDistance = 0;
    boolean glyphNotFound = true;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        robot.init(hardwareMap);
        glyphNotFound = true;

        while(opModeIsActive()) {
            if (gamepad1.a) {
                while (glyphNotFound) {
                    robot.driveForwardWithEncoders(3);
                    if (robot.isGlyphWithin(5)) {
                        robot.grabGlyph();
                        glyphNotFound = false;
                        break;
                    } else if (robot.runtimeGreaterThan(15)) {
                        glyphNotFound = false;
                        break;
                    } else if (robot.getEncoderValue() >= convertInchesToEncoder(48)) {
                        glyphNotFound = false;
                        break;
                    }
                }
                robot.driveBackwardWithEncoders(robot.getEncoderValue());
                robot.turnRightWithEncoders(0.5);
                robot.driveForwardWithEncoders(6);
                robot.dropGlyph();
                robot.driveBackwardWithEncoders(6 / 2);
                stop();
            }
            if (gamepad1.b) {

            }
            if (gamepad1.x) {

            }
            if (gamepad1.y) {

            }
        }
        stop();
    }
    private int convertInchesToEncoder(double distance) {
        return (int)(89.78 * distance);
    }
}