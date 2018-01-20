package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Autonomous 3", group = "Autonomous")
public class AutonomousMode3 extends LinearOpMode {

    private RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    private final double BALANCE_TO_CRYPTOBOX = 23;
    private final double QUARTER_TURN = 0.25;
    private final double BALANCE_TO_RIGHT = 4.75;
    private final double BALANCE_TO_CENTER = 12;
    private final double BALANCE_TO_LEFT = 19.5;
    private final double CRYPTOBOX_DEPTH = 6;
    private final double WAIT_TIME = 2;
    private boolean vumarkNotFound = true;
    //TODO some todo comment, android studio doesnt like pushing these
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
                robot.turnRightWithEncoders(QUARTER_TURN);
                robot.driveForwardWithEncoders(BALANCE_TO_LEFT);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER) ||
                            robot.runtimeGreaterThan(WAIT_TIME)) {
                robot.driveBackwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnRightWithEncoders(QUARTER_TURN);
                robot.driveForwardWithEncoders(BALANCE_TO_CENTER);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                robot.driveBackwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.turnRightWithEncoders(QUARTER_TURN);
                robot.driveForwardWithEncoders(BALANCE_TO_RIGHT);
                vumarkNotFound = false;
                break;

            }
        }
        robot.turnLeftWithEncoders(QUARTER_TURN);
        robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
        robot.dropGlyph();
        robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH / 2);
        stop();
    }
}
