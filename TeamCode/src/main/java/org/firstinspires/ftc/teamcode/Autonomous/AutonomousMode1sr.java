package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Structural 1 Blue", group = "Autonomous")
public class AutonomousMode1sr extends LinearOpMode {

    private RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    private final double ROTATION_AMOUNT        = 0.25;
    private final double BALANCE_TO_CENTER      = 28;
    private final double BALANCE_TO_LEFT        = BALANCE_TO_CENTER - 7.63;
    private final double BALANCE_TO_RIGHT       = BALANCE_TO_CENTER + 7.63;
    private final double CRYPTOBOX_DEPTH        = 12;
    private final double WAIT_TIME              = 10;
    private boolean vumarkNotFound              = true;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        robot.init(hardwareMap);
        tracker.init();

        robot.resetRuntime();

        robot.grabGlyph();
        while(vumarkNotFound) { //VuMark not always seen immediately, so check until it is seen
            if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                robot.driveForwardWithEncoders(BALANCE_TO_LEFT);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER) ||
                    robot.runtimeGreaterThan(WAIT_TIME)) {
                robot.driveForwardWithEncoders(BALANCE_TO_CENTER);
                vumarkNotFound = false;
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                robot.driveForwardWithEncoders(BALANCE_TO_RIGHT);
                vumarkNotFound = false;
                break;
            }
        }
        robot.turnLeftWithEncoders(ROTATION_AMOUNT);
        robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
        robot.dropGlyph();
        robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH / 2);
        stop();
            }
        }