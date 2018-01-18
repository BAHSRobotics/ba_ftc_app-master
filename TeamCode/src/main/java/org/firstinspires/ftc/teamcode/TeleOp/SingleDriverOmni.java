package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.ButtonHandler;
import org.firstinspires.ftc.teamcode.util.RobotWrapper;

@TeleOp(name = "Solo OmniDrive TeleOp", group = "Iterative Opmode")
public class SingleDriverOmni extends OpMode {

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
        else if (buttonHandler.isPressed(gamepad1.dpad_left))       robot.driveRight();
        else if (buttonHandler.isPressed(gamepad1.dpad_right))      robot.driveLeft();
        else if (buttonHandler.isPressed(gamepad1.left_bumper))     robot.turnLeft();
        else if (buttonHandler.isPressed(gamepad1.right_bumper))    robot.turnRight();
        else if (buttonHandler.isPressed(gamepad1.left_trigger))    robot.retractGlyphtoN(0);
        else if (buttonHandler.isPressed(gamepad1.right_trigger))   robot.extendGlyphtoN(2);
        else if (buttonHandler.isPressed(gamepad1.x))               robot.grabGlyph();
        else if (buttonHandler.isPressed(gamepad1.y))               robot.dropGlyph();
        else if (buttonHandler.isAbsolutelyPressed(gamepad1.a))     robot.toggleRelicPincher();
        else if (buttonHandler.isAbsolutelyPressed(gamepad1.b))     robot.toggleRelicLift();
        else if (buttonHandler.isPressed(gamepad1.start))           robot.extendRelictoN(8);
        else if (buttonHandler.isPressed(gamepad1.back))            robot.retractRelictoN(1);
        else                                                        robot.stopWheels();
    }

    @Override
    public void stop() {
    }

}
