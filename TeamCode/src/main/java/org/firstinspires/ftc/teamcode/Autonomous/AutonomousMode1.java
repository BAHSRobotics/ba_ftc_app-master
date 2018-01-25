package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

import static org.firstinspires.ftc.teamcode.Autonomous.RobotMath.convertInchesToEncoder;

@Autonomous(name = "Robotonics 1 Blue", group = "Autonomous")
public class AutonomousMode1 extends LinearOpMode {

    private RobotWrapper robot = new RobotWrapper();
    private VuforiaTracker tracker = new VuforiaTracker();

    private final double ROTATION_AMOUNT = 0.25;
    private final double BALANCE_TO_CENTER = 28;
    private final double BALANCE_TO_LEFT = BALANCE_TO_CENTER - 7.63;
    private final double BALANCE_TO_RIGHT = BALANCE_TO_CENTER + 7.63;
    private final double CRYPTOBOX_DEPTH = 12;
    private final double WAIT_TIME = 10;
    private boolean vumarkNotFound = true;
    private boolean glyphNotFound = true;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        initRobot();
        lookForVumark();
        
        scoreGlyph();
        lookForGlyph();
        scoreGlyph();
        stop();
    }

    private void initRobot() {
        robot.init(hardwareMap);
        tracker.init();
        robot.resetRuntime();
        robot.grabBackGlyph();
    }
    private void scoreGlyph() {
        if (!glyphNotFound) {
            robot.driveBackwardWithEncoders(robot.getEncoderValue());
            robot.turnRightWithEncoders(2 * ROTATION_AMOUNT);
            robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
            robot.dropBottomGlyph();
            robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH / 2);
        } else if (!vumarkNotFound){
            robot.turnRightWithEncoders(ROTATION_AMOUNT);
            robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
            robot.dropBackGlyph();
            robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH / 2);
        }

    }
    private void lookForVumark() {
        while (vumarkNotFound) { //VuMark not always seen immediately, so check until it is seen
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
    }
    private void lookForGlyph() {
        while (glyphNotFound) {
            robot.driveForwardWithEncoders(3);
            if (robot.isGlyphWithin(5)) {
                robot.grabBottomGlyph();
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
    }
}