package org.firstinspires.ftc.teamcode.util;


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

    public boolean isPressed(float button) {
        return button > 0.2;
    }
    public boolean isAbsolutelyPressed(boolean button) {
        if (button && wasPressed)     {wasPressed = false; return true;}
        else if (button)               {wasPressed = true; return false;}
        else return false;
    }


}
