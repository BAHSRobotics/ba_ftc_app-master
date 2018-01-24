/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.util.ButtonHandler;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;
//y no work

@TeleOp(name = "OmniDrive TeleOp", group = "Iterative Opmode")
public class DoubleDriverOmni extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private RobotWrapper robot = new RobotWrapper();
    private ButtonHandler buttonHandler = new ButtonHandler();


    @Override
    public void init_loop() {

    }

    @Override
    public void init() {
        robot.init(hardwareMap);


        telemetry.addData("Status", "Initializing");
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        telemetry.clear();
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        if      (buttonHandler.isPressed(gamepad1.dpad_up))         robot.driveForward();
        else if (buttonHandler.isPressed(gamepad1.dpad_down))       robot.driveBackward();
        else if (buttonHandler.isPressed(gamepad1.dpad_left))       robot.driveLeft();
        else if (buttonHandler.isPressed(gamepad1.dpad_right))      robot.driveRight();
        else if (buttonHandler.isPressed(gamepad1.left_bumper))     robot.turnLeft();
        else if (buttonHandler.isPressed(gamepad1.right_bumper))    robot.turnRight();
        else if (buttonHandler.isPressed(gamepad1.left_trigger))    robot.retractGlyphtoN(0);
        else if (buttonHandler.isPressed(gamepad1.right_trigger))   robot.extendGlyphtoN(3);
        else if (buttonHandler.isPressed(gamepad1.x))               robot.grabBottomGlyph();
        else if (buttonHandler.isPressed(gamepad1.y))               robot.dropBottomGlyph();
        else if (buttonHandler.isPressed(gamepad1.a))               robot.grabTopGlyph();
        else if (buttonHandler.isPressed(gamepad1.b))               robot.dropTopGlyph();
        else if (buttonHandler.isPressed(gamepad1.start))           robot.extendRelictoN(8);
        else if (buttonHandler.isPressed(gamepad1.back))            robot.retractRelictoN(1);

        else if (buttonHandler.isPressed(gamepad2.y))               robot.dropBackGlyph();
        else if(buttonHandler.isPressed(gamepad2.x))                robot.grabBackGlyph();
        else if (buttonHandler.isPressed(gamepad2.dpad_down))       robot.driveForward();
        else if (buttonHandler.isPressed(gamepad2.dpad_up))         robot.driveBackward();
        else if (buttonHandler.isPressed(gamepad2.right_trigger))   robot.extendGlyph();
        else if (buttonHandler.isPressed(gamepad2.left_trigger))    robot.retractGlyph();
        else if (buttonHandler.isAbsolutelyPressed(gamepad2.a))     robot.toggleRelicPincher();
        else if (buttonHandler.isAbsolutelyPressed(gamepad2.b))     robot.toggleRelicLift();
        else if (buttonHandler.isPressed(gamepad2.start))           robot.extendRelic();
        else if (buttonHandler.isPressed(gamepad2.back))            robot.retractRelic();
        else                                                        robot.stopWheels();
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
