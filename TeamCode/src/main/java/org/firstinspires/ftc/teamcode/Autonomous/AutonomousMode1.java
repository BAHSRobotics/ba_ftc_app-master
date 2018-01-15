package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHandler;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "Autonomous 1", group = "Autonomous")
public class AutonomousMode1 extends LinearOpMode {

    private RobotHandler robot = new RobotHandler();
    private VuforiaTracker tracker = new VuforiaTracker();

    private final double ROTATION_AMOUNT = 0.25;
    private final double BALANCE_TO_LEFT = 25;
    private final double BALANCE_TO_CENTER = BALANCE_TO_LEFT + 8;
    private final double BALANCE_TO_RIGHT = BALANCE_TO_CENTER + 7;
    private final double DISTANCE_TO_CRYPTOBOX = 6;
    private final double CRYPTOBOX_DEPTH = 6;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        tracker.init();

        waitForStart();

        while (opModeIsActive()) {
            if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                robot.dropGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_LEFT);
                robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.grabGlyph();
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER)) {
                robot.dropGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_CENTER);
                robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.grabGlyph();
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                robot.dropGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_RIGHT);
                robot.turnLeftWithEncoders(ROTATION_AMOUNT);
                robot.driveForwardWithEncoders(DISTANCE_TO_CRYPTOBOX);
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.grabGlyph();
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                break;
            }
        }
    }
}
