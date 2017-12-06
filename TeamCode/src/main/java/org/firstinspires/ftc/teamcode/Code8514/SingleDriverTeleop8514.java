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

package org.firstinspires.ftc.teamcode.Code8514;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.ButtonHandler;
import org.firstinspires.ftc.teamcode.util.GlyphCatcher;
import org.firstinspires.ftc.teamcode.util.LinearSlide;
import org.firstinspires.ftc.teamcode.util.OmniDrive;
import org.firstinspires.ftc.teamcode.util.RelicPincher;
//y no work

@TeleOp(name = "Solo OmniDrive TeleOp", group = "Iterative Opmode")
public class SingleDriverTeleop8514 extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private GlyphCatcher catcher = new GlyphCatcher();
    private OmniDrive wheels = new OmniDrive();
    private LinearSlide relicArm = new LinearSlide("relicArm");
    private LinearSlide lift = new LinearSlide("spool");
    private ButtonHandler buttonHandler = new ButtonHandler();
    private RelicPincher relicPincher = new RelicPincher();

    @Override
    public void init_loop() {

    }

    @Override
    public void init() {
        catcher.init(hardwareMap);
        wheels.init(hardwareMap);
        lift.init(hardwareMap);
        relicArm.init(hardwareMap);
        relicPincher.init(hardwareMap);
        //catcher.openClaw();

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
        if      (buttonHandler.isPressed(gamepad1.dpad_up))         wheels.driveForward();
        else if (buttonHandler.isPressed(gamepad1.dpad_down))       wheels.driveBackward();
        else if (buttonHandler.isPressed(gamepad1.dpad_left))       wheels.driveRight();
        else if (buttonHandler.isPressed(gamepad1.dpad_right))      wheels.driveLeft();
        else if (buttonHandler.isPressed(gamepad1.left_bumper))     wheels.turnLeft();
        else if (buttonHandler.isPressed(gamepad1.right_bumper))    wheels.turnRight();
        else if (gamepad1.left_trigger > 0.2)                       lift.retractToN(0);
        else if (gamepad1.right_trigger > 0.2)                      lift.extendToN(3);
        else if (buttonHandler.isPressed(gamepad1.x))               catcher.closeClaw();
        else if (buttonHandler.isPressed(gamepad1.y))               catcher.openClaw();
        else if (buttonHandler.isAbsolutelyPressed(gamepad1.a))     relicPincher.pinch();
        else if (buttonHandler.isAbsolutelyPressed(gamepad1.b))     relicPincher.lift();
        else if (buttonHandler.isPressed(gamepad1.start))           relicArm.extendToN(7);
        else if (buttonHandler.isPressed(gamepad1.back))            relicArm.retractToN(0);
        else                                                        wheels.stop();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
