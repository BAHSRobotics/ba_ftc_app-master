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
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    }


    public void tracker() { //move this
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
    }
    public void navigator() { //move this
        VuforiaTrackables stonesAndChips = this.vuforia.loadTrackablesFromAsset("StonesAndChips");
        VuforiaTrackable redTarget = stonesAndChips.get(0);
        redTarget.setName("RedTarget");  // Stones

        VuforiaTrackable blueTarget  = stonesAndChips.get(1);
        blueTarget.setName("BlueTarget");  // Chips

        /** For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(stonesAndChips);
    }
}
