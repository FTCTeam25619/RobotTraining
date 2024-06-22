package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotTraining;

@TeleOp
public class ExampleTeleop  extends OpMode {
    private RobotTraining robot;

    @Override
    public void init() {
        robot = new RobotTraining(hardwareMap, gamepad1, gamepad2, telemetry);

        telemetry.addData("Robot Status", "Initializing ExampleTeleop");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        telemetry.addData("Robot Status", "Running ExampleTeleop");
    }

    @Override
    public void loop() {
        robot.run();
    }

    @Override
    public void stop() {
        telemetry.addData("Robot Status", "Stopped ExampleTeleop");
        robot.onStop();
        robot.reset();
    }
}
