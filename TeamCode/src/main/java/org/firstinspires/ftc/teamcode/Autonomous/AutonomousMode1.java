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
    private final double DISTANCE_TO_CRYPTOBOX  = 6;
    private final double CRYPTOBOX_DEPTH        = 6;
    private final double WAIT_TIME              = 10;
    private boolean vumarkNotFound              = true;
    private boolean flag                        = false;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        robot.init(hardwareMap);
        tracker.init();

        robot.resetRuntime();
        while (opModeIsActive()) {
            while (vumarkNotFound) {
                robot.grabGlyph();
                if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                    robot.driveForwardWithEncoders(BALANCE_TO_LEFT);
                    robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                    robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                    vumarkNotFound = false;
                    break;
                } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER) ||
                            robot.runtimeGreaterThan(WAIT_TIME)) {
                    robot.driveForwardWithEncoders(BALANCE_TO_CENTER);
                    robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                    robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                    vumarkNotFound = false;
                    break;
                } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                    robot.driveForwardWithEncoders(BALANCE_TO_RIGHT);
                    robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                    robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                    vumarkNotFound = false;
                    break;
                }
            }
            if (!flag) {
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.dropGlyph();
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH / 2);
                flag = true;
            }
            stop();
             //Wild movement to find glyph
            while (robot.isGlyphWithin(3) && robot.getRuntime() < 1000 * (30 - 7)) {
                robot.driveBackwardWithEncoders(6);
                robot.turnLeftWithEncoders(0.5);
            }
        }
    }
}
