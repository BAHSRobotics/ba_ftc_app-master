package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.teamcode.util.Constants.*;

/**
 * Created by 4014465 on 11/30/2017.
 */

public class VuforiaHandler {

    OpenGLMatrix lastLocation;
    VuforiaLocalizer vuforia;
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

    public VuforiaHandler() {
        lastLocation = null;
    }
    public void init() {
        parameters.vuforiaLicenseKey = VUFORIAKEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    }

}
