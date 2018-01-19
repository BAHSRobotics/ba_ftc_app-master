package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

@Autonomous(name = "WiggleBot", group = "Tests")
public class WiggleBot extends LinearOpMode {
    // A proof of concept and test file to test use of range sensor in autonomous
    private RobotWrapper robot = new RobotWrapper();

    int zComponent, zDistance, xComponent, xDistance = 0;
    boolean glyphNotGrabbed = true;
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        glyphNotGrabbed = true;

        while (glyphNotGrabbed) {
            if(robot.isGlyphWithin(3)) {
                robot.grabGlyph();
                glyphNotGrabbed = false;
                break;
            }
            else {
                robot.driveForwardWithEncoders(zDistance);
                zComponent += zDistance;
                robot.driveRightWithEncoders(xDistance);
                xComponent += xDistance;
            }
        }
        //robot
        stop();
    }
}