package org.firstinspires.ftc.teamcode.util;
import java.lang.Math;


/**
 * Created by 4014465 on 11/9/2017.
 */

public class ButtonHandler {
    boolean wasPressed;
    public ButtonHandler() {

    }
   public boolean isPressed(boolean button) {
        return button;
    }


    public boolean isAbsolutelyPressed(boolean button) {
        if (button && wasPressed)     {wasPressed = false; return true;}
        else if (button)               {wasPressed = true; return false;}
        else return false;
    }

// JOYSTICK FUNCTIONS BELOW (Written By Cherryholmes)
    public boolean isPressed(float button) {
    return button > 0.2;
}

    public double findAngleJoystick(float stick_y, float stick_x) {
        double theta = Math.atan2(-stick_y, -stick_x);
        return theta;
    }

    public double findMagnitudeJoystick(float stick_y, float stick_x) {
        double r = Math.hypot(stick_y, stick_x);
        return r;
    }

}
