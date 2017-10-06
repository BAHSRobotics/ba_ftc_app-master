package org.firstinspires.ftc.teamcode.Code8514;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.SharedFiles.Constants;
import or // TODO add telemetry
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by 4014465 on 9/22/2017.
 */

public class SimpleAutonomous8514 extends LinearOpMode {

    VuforiaLocalizer vuforia;
    @Override public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters();
        params.vuforiaLicenseKey = Constants.VUFORIAKEY;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(params);
        VuforiaTrackables stonesAndChips = this.vuforia.loadTrackablesFromAsset("RelicVuMark");


    }

}
