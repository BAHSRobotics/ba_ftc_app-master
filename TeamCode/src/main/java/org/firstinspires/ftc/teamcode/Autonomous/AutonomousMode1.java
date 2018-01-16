package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Autonomous 1", group = "Autonomous")
public class AutonomousMode1 extends LinearOpMode {

    private RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    private final double ROTATION_AMOUNT = 0.25;
    private final double BALANCE_TO_LEFT = 25;
    private final double BALANCE_TO_CENTER = BALANCE_TO_LEFT + 8;
    private final double BALANCE_TO_RIGHT = BALANCE_TO_CENTER + 7;
    private final double DISTANCE_TO_CRYPTOBOX = 6;
    private final double CRYPTOBOX_DEPTH = 6;
    private boolean vumarkNotFound = true;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        tracker.init();

        waitForStart();
        robot.resetRuntime();
        while (opModeIsActive()) {
            while (vumarkNotFound) {
                if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                    robot.dropGlyph();
                    robot.driveForwardWithEncoders(BALANCE_TO_LEFT);
                    robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                    robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                    robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                    robot.grabGlyph();
                    robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                    telemetry.addData("Scored in", robot.getRuntime());
                    vumarkNotFound = false;
                    break;
                } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER)) {
                    robot.dropGlyph();
                    robot.driveForwardWithEncoders(BALANCE_TO_CENTER);
                    robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                    robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                    robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                    robot.grabGlyph();
                    robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                    vumarkNotFound = false;
                    break;
                } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                    robot.dropGlyph();
                    robot.driveForwardWithEncoders(BALANCE_TO_RIGHT);
                    robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                    robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                    robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                    robot.grabGlyph();
                    robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                    vumarkNotFound = false;
                    break;
                }
            }
            // Wild movement to find glyph
            while (robot.touchSensorNotPressed() && robot.getRuntime() < 1000 * (30 - 7)) {
                robot.driveBackwardWithEncoders(6);
                robot.turnLeftWithEncoders(0.5);
            }
        }
    }
}
