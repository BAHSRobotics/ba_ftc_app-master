package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Structural 4 Blue", group = "Autonomous")
public class AutonomousMode4sr extends LinearOpMode {

    private RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    private final double BALANCE_TO_CRYPTOBOX   = 21.5;
    private final double QUARTER_TURN           = 0.25;

    private final double BALANCE_TO_CENTER      = 11;
    private final double BALANCE_TO_LEFT        = BALANCE_TO_CENTER - 7.63;
    private final double BALANCE_TO_RIGHT       = BALANCE_TO_CENTER + 7.63;
    private final double CRYPTOBOX_DEPTH        = 6;
    private final double WAIT_TIME              = 10;
    private boolean vumarkNotFound              = true;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        robot.init(hardwareMap);
        tracker.init();

        robot.resetRuntime();
        robot.grabGlyph();
        while (vumarkNotFound) {
            if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                robot.grabGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnRightWithEncoders(QUARTER_TURN);
                robot.driveForwardWithEncoders(BALANCE_TO_LEFT);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER) ||
                    robot.runtimeGreaterThan(WAIT_TIME)) {
                robot.driveForwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnRightWithEncoders(QUARTER_TURN);
                robot.driveForwardWithEncoders(BALANCE_TO_CENTER);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                robot.grabGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnRightWithEncoders(QUARTER_TURN);
                robot.driveForwardWithEncoders(BALANCE_TO_RIGHT);
                vumarkNotFound = false;
                break;
            }
        }
        robot.turnLeftWithEncoders(QUARTER_TURN);
        robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
        robot.dropGlyph();
        robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH / 3);
        stop();
    }
}
