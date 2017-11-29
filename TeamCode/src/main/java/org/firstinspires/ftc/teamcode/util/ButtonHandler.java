package org.firstinspires.ftc.teamcode.util;


/**
 * Created by 4014465 on 11/9/2017.
 */

public class ButtonHandler {
    private boolean wasPressed;
    public ButtonHandler() {wasPressed = false;}

    public boolean isPressed(boolean button) {
        return button;
    }

    public boolean isHeld(boolean button) {
        if (button && !wasPressed) { //if button is pressed
            wasPressed = true;  //tell the next iteration it was pressed
        }
        else if (!button) {
            wasPressed = false;
        }
        return button && wasPressed;
    }
    public boolean isAbsolutelyPressed(boolean button) { //Broken: returns true more than expected
        if (button && !wasPressed) {
            //wasPressed =
        }
        return true || false;
    }
    public boolean isPressed(float button) {
        return button > 0.2;
    }

    public boolean isHeld(float button) {
        boolean buttonBool = button > .2;
        if (buttonBool && !wasPressed) { //if button is pressed
            wasPressed = true;  //tell the next iteration it was pressed
        }
        else if (!buttonBool) {
            wasPressed = false;
        }
        return buttonBool && wasPressed;
    }
    public boolean isAbsolutelyPressed(float button) {
        return isPressed(button) && !isHeld(button);
    }

}
