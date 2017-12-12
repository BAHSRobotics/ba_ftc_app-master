package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.VuforiaHandler;
import org.firstinspires.ftc.teamcode.util.VuforiaNavigator;
import org.firstinspires.ftc.teamcode.util.VuforiaTracker;

/**
 * Created by 4014465 on 12/11/2017.
 */

@Autonomous (name="VuforiaTest", group="Iterative Opmode")
public class VuforiaTests extends LinearOpMode{
    VuforiaHandler vuforia = new VuforiaHandler();
    VuforiaTracker tracker = new VuforiaTracker();


    @Override
    public void runOpMode() throws InterruptedException {
        vuforia.init();
        tracker.init();
        while (true) {
            tracker.vumarkPosition();
        }
    }
}
