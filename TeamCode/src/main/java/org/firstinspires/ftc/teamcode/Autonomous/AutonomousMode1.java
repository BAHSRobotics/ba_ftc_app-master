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

    private final double ROTATION_AMOUNT        = 0.25;
    private final double BALANCE_TO_LEFT        = 24;
    private final double BALANCE_TO_CENTER      = 31.25;
    private final double BALANCE_TO_RIGHT       = 38.75;
    private final double DISTANCE_TO_CRYPTOBOX  = 5;
    private final double CRYPTOBOX_DEPTH        = 4;
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
                robot.turnRightWithEncoders(ROTATION_AMOUNT);
                robot.driveBackwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.dropGlyph();
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH / 2);
            stop();
             //Wild movement to find glyph
            /*while (robot.isGlyphWithin(3) && robot.getRuntime() < 1000 * (30 - 7)) {
                robot.driveBackwardWithEncoders(6);
                robot.turnLeftWithEncoders(0.5);*/
            }
        }