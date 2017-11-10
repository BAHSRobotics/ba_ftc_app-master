package org.firstinspires.ftc.teamcode.SharedFiles;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by 4014465 on 11/9/2017.
 */

public class ButtonHandler {
    public ButtonHandler() {
        boolean wasPressed = false;
    }

    public boolean isPressed(boolean button) {
        return button;
    }
    public boolean isHeld(boolean button) {
        boolean wasPressed = false;
        if (button && !wasPressed) {
            wasPressed = true;
        } else if (button && wasPressed) {
            return true;
        }
    }
    public boolean isPressed(float button) {
        return button > 0.2;
    }
    public boolean isHeld(float button) {

    }

}
