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

    private final double BALANCE_TO_CRYPTOBOX   = 20;
    private final double BALANCE_TO_RIGHT       = 5.5;
    private final double BALANCE_TO_CENTER      = BALANCE_TO_RIGHT + 8;
    private final double BALANCE_TO_LEFT        = BALANCE_TO_CENTER + 7;
    private final double CRYPTOBOX_DEPTH        = 6;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        tracker.init();
        
        waitForStart();

        while (opModeIsActive()) {
            if (tracker.vumarkFound().equals(RelicRecoveryVuMark.LEFT)) {
                robot.dropGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.driveRightWithEncoders(BALANCE_TO_LEFT);
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.grabGlyph();
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.CENTER)) {
                robot.dropGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.driveRightWithEncoders(BALANCE_TO_CENTER);
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.grabGlyph();
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                break;
            } else if (tracker.vumarkFound().equals(RelicRecoveryVuMark.RIGHT)) {
                robot.dropGlyph();
                robot.driveForwardWithEncoders(BALANCE_TO_CRYPTOBOX);
                robot.driveRightWithEncoders(BALANCE_TO_RIGHT);
                robot.driveForwardWithEncoders(CRYPTOBOX_DEPTH);
                robot.grabGlyph();
                robot.driveBackwardWithEncoders(CRYPTOBOX_DEPTH);
                break;
            }
        }
    }
}
