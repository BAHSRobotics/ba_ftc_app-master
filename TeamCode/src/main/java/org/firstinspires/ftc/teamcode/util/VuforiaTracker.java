package org.firstinspires.ftc.teamcode.util;

import android.os.DropBoxManager;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.teamcode.util.Constants.VUFORIAKEY;

/*
 * Created by 4014465 on 11/30/2017.
 */


public class VuforiaTracker extends VuforiaHandler{
    VuforiaTrackable relicTemplate;
    VuforiaTrackables relicTrackables;
    Telemetry telemetry;
    OpenGLMatrix lastLocation;
    VuforiaLocalizer vuforia;
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

    public VuforiaTracker(){}
    public void init() {
        parameters.vuforiaLicenseKey = VUFORIAKEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();
    }
    public RelicRecoveryVuMark vumarkFound() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate); //check if template visible
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) { // if found (visible)
            telemetry.addData("VuMark", "%s visible", vuMark);
            return vuMark;
        }
        else {
            //vumark not found (visible)
            return null;
        }
    }
    public void vumarkPosition() {
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();

        if (pose != null) {
            VectorF trans = pose.getTranslation();
            Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

            double tX = trans.get(0);
            double tY = trans.get(1);
            double tZ = trans.get(2);

            double rX = rot.firstAngle;
            double rY = rot.secondAngle;
            double rZ = rot.thirdAngle;
        }


    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}


