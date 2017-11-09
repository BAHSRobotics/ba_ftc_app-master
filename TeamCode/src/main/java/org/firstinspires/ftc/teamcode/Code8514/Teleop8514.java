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
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.SharedFiles.GlyphCatcher;
import org.firstinspires.ftc.teamcode.SharedFiles.TankDrive;

import static android.R.attr.height;
import static com.sun.tools.doclint.Entity.or;

@TeleOp(name="8514 Teleop", group="Iterative Opmode")
public class Teleop8514 extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor chainMotor = null;
    private DcMotor linearSlide = null;
    private Servo left = null;
    private Servo right = null;
   // private Servo Autonomous = null;
    private Servo slideClawJoint = null;
    private Servo slideClaw = null;
    int height;
    double clawPos = 0;
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        slideClawJoint = hardwareMap.get(Servo.class, "clawJoint");
        slideClaw = hardwareMap.get(Servo.class, "slideClaw");
       // Autonomous = hardwareMap.get(Servo.class, "autoServo");
        chainMotor = hardwareMap.get(DcMotor.class, "chainMotor");
        chainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyroSensor");
        gyro = modernRoboticsI2cGyro;

        // Start calibrating the gyro. This takes a few seconds and is worth performing
        // during the initialization phase at the start of each opMode.
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        modernRoboticsI2cGyro.calibrate();


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        chainMotor.setDirection(DcMotor.Direction. FORWARD);
        linearSlide.setDirection(DcMotor.Direction.FORWARD);
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        height = 1;

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
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideClawJoint.setPosition(0.0);

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        if (gamepad1.left_bumper) {


        }
        else if (gamepad1.right_bumper) {
            frontLeft.setPower(-1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(-1.0);

        }
        else if (gamepad1.dpad_down) {
            frontLeft.setPower(1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(1.0);
            backRight.setPower(-1.0);

        }
        // Closes Servos
        else if (gamepad1.a) {
            left.setPosition(1.0);
            right.setPosition(0.0);
            chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            chainMotor.setTargetPosition(1650 * height);
            chainMotor.setPower(0.6);
            height++;
            }
        // Opens Servos
        else if (gamepad1.b) {
            // while(chainMotor.getCurrentPosition()>0 && aWasPressed){
            left.setPosition(0.0);
            right.setPosition(1.0);
            //chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            chainMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            height--;
            chainMotor.setTargetPosition(-1650 * height);
            chainMotor.setPower(0.6);


        } else if (gamepad1.right_trigger > 0.5){
            linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linearSlide.setPower(0.5);
            linearSlide.setTargetPosition(1650 * 7);

        } else if (gamepad1.left_trigger == 1.0){
            clawPos = clawPos + 0.1;
            slideClawJoint.setPosition(clawPos);
        } else if (clawPos == 1.0){
            clawPos = 0.0;
        }
        else if (gamepad1.x) {
            slideClaw.setPosition(1.0);
        }
        else if (gamepad1.dpad_up) {
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
    }

}
