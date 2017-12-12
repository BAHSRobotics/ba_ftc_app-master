package org.firstinspires.ftc.teamcode.util;


/**
 * Created by 4014465 on 11/9/2017.
 */

//TODO unbreak

public class ButtonHandler {
    private boolean wasPressed;
    public ButtonHandler() {}

    public boolean isPressed(boolean button) {
        return button;
    }


    public boolean isPressed(float button) {
        return button > 0.2;
    }


}
