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

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 @TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
@TeleOp(name="10526 Driver", group="Iterative Opmode")
public class Teleop10526 extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //private DcMotor frontLeft = null;
    //private DcMotor frontRight = null;
    private DcMotor backLeft, chainMotor, backRight = null;
    private Servo left, right = null;
    //private Servo Autonomous = null;
    private int n;
    private boolean xFlag, yFlag, aFlag, bFlag;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        //frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        //frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        left = hardwareMap.get(Servo.class, "leftServo");
        right = hardwareMap.get(Servo.class, "rightServo");
        //Autonomous = hardwareMap.get(Servo.class, "autoServo");
        chainMotor = hardwareMap.get(DcMotor.class, "chainMotor");

        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyroSensor");

        // Start calibrating the gyro. This takes a few seconds and is worth performing
        // during the initialization phase at the start of each opMode.
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        modernRoboticsI2cGyro.calibrate();


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        n = 1;
        //frontLeft.setDirection(DcMotor.Direction.FORWARD);
        //frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        chainMotor.setDirection(DcMotor.Direction. FORWARD);
        // Tell the driver that initialization is complete.
        telemetry.clear();
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Encoder Position", chainMotor.getCurrentPosition());
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        left.setPosition(0.0);
        right.setPosition(1.0);
        chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
        chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        if (gamepad1.left_bumper) {
          //  frontLeft.setPower(1.0);
            //frontRight.setPower(1.0);
            backLeft.setPower(1.0);
            backRight.setPower(1.0);
        }
        else if (gamepad1.right_bumper) {
          //  frontLeft.setPower(-1.0);
            //frontRight.setPower(-1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(-1.0);

        }
        else if (gamepad1.dpad_down) {
          //  frontLeft.setPower(1.0);
            //frontRight.setPower(-1.0);
            backLeft.setPower(1.0);
            backRight.setPower(-1.0);
        }
        // Closes Servo's
        else if (gamepad1.a && aFlag) {
            left.setPosition(1.0);
            right.setPosition(0.0);
            chainMotor.setTargetPosition(1650 * n);
            n++; //increment
            chainMotor.setPower(0.5);
            aFlag = false;
            }

        // Opens Servo's
        else if (gamepad1.b) {
            left.setPosition(0.0);
            right.setPosition(1.0);
            --n; //decrement
            chainMotor.setTargetPosition(1650 * n);
            chainMotor.setPower(0.5);

        } else if (gamepad1.x) {
            chainMotor.setTargetPosition(1650*4);
            n = 4;
        }
        else if(gamepad1.y) {
            chainMotor.setTargetPosition(0);
            n = 1;
        }
        else if (gamepad1.dpad_up) {
           // frontLeft.setPower(-1.0);
            //frontRight.setPower(1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(1.0);
        }
        else if (!aFlag || !bFlag || !xFlag || !yFlag) {
            aFlag = true;
            bFlag = true;
            xFlag = true;
            yFlag = true;
        }
        else {
           // frontLeft.setPower(0);
            //frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
