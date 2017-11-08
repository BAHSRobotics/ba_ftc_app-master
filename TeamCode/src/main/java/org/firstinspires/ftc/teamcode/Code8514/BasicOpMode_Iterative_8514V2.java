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
@TeleOp(name="Basic: Iterative 8514 backup", group="Iterative Opmode")
public class BasicOpMode_Iterative_8514V2 extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor chainMotor = null;
    private Servo left = null;
    private Servo right = null;
    private Servo Autonomous = null;
    boolean aWasPressed = false;
    int revolutionsDone = 0;
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
    ElapsedTime timer = new ElapsedTime();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        left = hardwareMap.get(Servo.class, "leftServo");
        right = hardwareMap.get(Servo.class, "rightServo");
       // Autonomous = hardwareMap.get(Servo.class, "autoServo");
        chainMotor = hardwareMap.get(DcMotor.class, "chainMotor");
        chainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyroSensor");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;

        // Start calibrating the gyro. This takes a few seconds and is worth performing
        // during the initialization phase at the start of each opMode.
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        modernRoboticsI2cGyro.calibrate();


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        chainMotor.setDirection(DcMotor.Direction. FORWARD);
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        int position = chainMotor.getCurrentPosition();
        telemetry.addData("Encoder Position", position);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        left.setPosition(0.0);
        right.setPosition(1.0);
        chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
        chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        if (gamepad1.left_bumper == true) {
            frontLeft.setPower(1.0);
            frontRight.setPower(1.0);
            backLeft.setPower(1.0);
            backRight.setPower(1.0);

        }
        else if (gamepad1.right_bumper == true) {
            frontLeft.setPower(-1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(-1.0);

        }
        else if (gamepad1.dpad_down == true) {
            frontLeft.setPower(1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(1.0);
            backRight.setPower(-1.0);

        }
        // Closes Servo's
      /*  else if (gamepad1.a) {
            left.setPosition(1.0);
            right.setPosition(0.0);
            chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            chainMotor.setTargetPosition(1650);
            revolutionsDone = revolutionsDone + 1650;
            chainMotor.setPower(0.25);
            //aWasPressed = true;
            }*/
        // Opens Servo's
        else if (gamepad1.b) {
           // while(chainMotor.getCurrentPosition()>0 && aWasPressed){
                left.setPosition(0.0);
                right.setPosition(1.0);
                //chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                chainMotor.setTargetPosition(-revolutionsDone);
                chainMotor.setPower(0.35);
          //  }


        } else if (gamepad1.x) {
            left.setPosition(1.0);
            right.setPosition(0.0);
            chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            chainMotor.setTargetPosition(1650*3);
            chainMotor.setPower(0.5);
        }
        else if (gamepad1.dpad_up == true) {
            frontLeft.setPower(-1.0);
            frontRight.setPower(1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(1.0);
        }
        else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        revolutionsDone = 0;
    }

}
