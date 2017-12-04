package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation.TAG;

/*
 * Created by 4014465 on 11/30/2017.
 */

public class VuforiaNavigator extends VuforiaHandler {

    private float mmPerInch = 25.4f;
    private float mmBotWidth = 18 * mmPerInch;            // ... or whatever is right for your robot
    private float mmFTCFieldWidth = (12 * 12 - 2) * mmPerInch;   // the FTC field is ~11'10" center-to-center of the glass panels
    private List<VuforiaTrackable> allTrackables = new ArrayList<>();
    private Telemetry telemetry = null;

    public VuforiaNavigator() {
        VuforiaTrackables stonesAndChips = vuforia.loadTrackablesFromAsset("StonesAndChips");
        VuforiaTrackable redTarget = stonesAndChips.get(0);
        redTarget.setName("RedTarget");  // Stones

        VuforiaTrackable blueTarget = stonesAndChips.get(1);
        blueTarget.setName("BlueTarget");  // Chips

        allTrackables.addAll(stonesAndChips);

        OpenGLMatrix redTargetLocationOnField = OpenGLMatrix
                .translation(-mmFTCFieldWidth / 2, 0, 0)
                .multiplied(
                        Orientation.getRotationMatrix(
                            AxesReference.EXTRINSIC, AxesOrder.XZX, AngleUnit.DEGREES,
                            90, 90, 0
                        )
                );
        redTarget.setLocation(redTargetLocationOnField);
        RobotLog.ii(TAG, "Red Target=%s", format(redTargetLocationOnField));

        OpenGLMatrix blueTargetLocationOnField = OpenGLMatrix
                .translation(0, mmFTCFieldWidth / 2, 0)
                .multiplied(
                        Orientation.getRotationMatrix(
                            AxesReference.EXTRINSIC, AxesOrder.XZX,
                            AngleUnit.DEGREES, 90, 0, 0
                        )
                );
        blueTarget.setLocation(blueTargetLocationOnField);
        RobotLog.ii(TAG, "Blue Target=%s", format(blueTargetLocationOnField));

        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(mmBotWidth / 2, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZY,
                        AngleUnit.DEGREES, -90, 0, 0));
        RobotLog.ii(TAG, "phone=%s", format(phoneLocationOnRobot));

        ((VuforiaTrackableDefaultListener) redTarget.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        ((VuforiaTrackableDefaultListener) blueTarget.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
/*
         * A brief tutorial: here's how all the math is going to work:
         *
         * C = phoneLocationOnRobot  maps   phone coords -> robot coords
         * P = tracker.getPose()     maps   image target coords -> phone coords
         * L = redTargetLocationOnField maps   image target coords -> field coords
         *
         * So
         *
         * C.inverted()              maps   robot coords -> phone coords
         * P.inverted()              maps   phone coords -> imageTarget coords
         *
         * Putting that all together,
         *
         * L x P.inverted() x C.inverted() maps robot coords to field coords.
         *
         * @see VuforiaTrackableDefaultListener#getRobotLocation()
 */

        stonesAndChips.activate();

    }

    public void loopable() {
        for (VuforiaTrackable trackable : allTrackables) {

            telemetry.addData(trackable.getName(),
                    (
                            (VuforiaTrackableDefaultListener) trackable.getListener()).isVisible() ?
                            "Visible" :
                            "Not Visible");

            OpenGLMatrix robotLocationTransform = (
                    (VuforiaTrackableDefaultListener) trackable.getListener()
            ).getUpdatedRobotLocation();
            if (robotLocationTransform != null) {
                lastLocation = robotLocationTransform;
            }
        }

        if (lastLocation != null) {
            telemetry.addData("Pos", format(lastLocation));
        } else {
            telemetry.addData("Pos", "Unknown");
        }
        telemetry.update();
    }

    String format(OpenGLMatrix transformationMatrix) {
        return transformationMatrix.formatAsTransform();
    }

}