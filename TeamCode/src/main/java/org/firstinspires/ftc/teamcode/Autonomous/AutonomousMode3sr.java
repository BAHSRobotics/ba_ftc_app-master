package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Structural 3 Red", group = "Autonomous")
public class AutonomousMode3sr extends LinearOpMode {

    private RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    private final double BALANCE_TO_CRYPTOBOX = 22;
    private final double QUARTER_TURN = 0.25;

    private final double BALANCE_TO_RIGHT = 1;
    private final double BALANCE_TO_CENTER = BALANCE_TO_RIGHT + 7.63;
    private final double BALANCE_TO_LEFT = BALANCE_TO_RIGHT + (2 *7.63);
    private final double CRYPTOBOX_DEPTH = 6;
    private final double WAIT_TIME = 10;
    private boolean vumarkNotFound = true;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        robot.init(hardwareMap);
        tracker.init();

        robot.resetRuntime();
        robot.grabGlyph();
        while(vumarkNotFound) {
            if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                robot.driveBackwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnLeftWithEncoders(QUARTER_TURN);
                robot.driveBackwardWithEncoders(BALANCE_TO_LEFT);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER) ||
                    robot.runtimeGreaterThan(WAIT_TIME)) {
                robot.driveBackwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnLeftWithEncoders(QUARTER_TURN);
                robot.driveBackwardWithEncoders(BALANCE_TO_CENTER);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                robot.driveBackwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnLeftWithEncoders(QUARTER_TURN); //The glyph is already oriented correctly.
                robot.driveBackwardWithEncoders(BALANCE_TO_RIGHT); //Because it equals zero.
                vumarkNotFound = false;
                break;
            }
        }
        robot.turnLeftWithEncoders(QUARTER_TURN);
        robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
        robot.dropGlyph();
        robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH / 2);
        stop();
    }
}
