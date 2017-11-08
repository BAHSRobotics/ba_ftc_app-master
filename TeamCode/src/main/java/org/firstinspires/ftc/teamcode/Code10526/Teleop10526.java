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

package org.firstinspires.ftc.teamcode.Code10526;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 *
 * @TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
@TeleOp(name = "10526 Driver", group = "Iterative Opmode")
public class Teleop10526 extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeft, chainMotor, backRight = null;
    private Servo left, right = null;
    private int n;
    private boolean aPressed, bPressed, xPressed, yPressed;

    @Override
    public void init_loop() {

    }

    @Override
    public void init() {


        telemetry.addData("Status", "Initializing");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        chainMotor = hardwareMap.get(DcMotor.class, "chainMotor");
        left.setPosition(0.0);
        right.setPosition(1.0);
        chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.log().add("Gyro Calibrating. Do Not Move!");

        n = 1;
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        chainMotor.setDirection(DcMotor.Direction.FORWARD);

        telemetry.clear();
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Encoder Position", chainMotor.getCurrentPosition());
    }

    @Override
    public void start() {
        runtime.reset();
        chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addData("N-value", n);
    }

    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            backLeft.setPower(1.0);
            backRight.setPower(1.0);
        } else if (gamepad1.right_bumper) {
            backLeft.setPower(-1.0);
            backRight.setPower(-1.0);

        } else if (gamepad1.dpad_down) {
            backLeft.setPower(1.0);
            backRight.setPower(-1.0);
        }
        else if (gamepad1.a && aFlag) {
            chainMotor.setTargetPosition(100 * n);
            if (n < 4) {
                n++;
                chainMotor.setPower(0.5);
                telemetry.addData("N-value", n);
            }
            aFlag = false;
        }

        // Opens Servo's
        else if (gamepad1.b && bFlag) {
            if (n > 2) {
                --n;
                telemetry.addData("N-value", n);

            } //decrement
            chainMotor.setTargetPosition(100 * n);
            chainMotor.setPower(0.5);
            bFlag = false;

        } else if (gamepad1.x && xFlag) {
            chainMotor.setTargetPosition(10);
            chainMotor.setPower(.5);
            n = 4;
        } else if (gamepad1.y && yFlag) {
            chainMotor.setTargetPosition(0);
            chainMotor.setPower(.5);
            n = 1;
        } else if (gamepad1.dpad_up) {
            backLeft.setPower(-1.0);
            backRight.setPower(1.0);
        } else if (!aFlag ) {
            aFlag = true;

        } else if (!bFlag) {
            bFlag = true;
        } else if (!xFlag) {
            xFlag = true;
        } else if (!yFlag) {
            yFlag = true;
        } else {

            backLeft.setPower(0);
            backRight.setPower(0);
            chainMotor.setPower(0);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
