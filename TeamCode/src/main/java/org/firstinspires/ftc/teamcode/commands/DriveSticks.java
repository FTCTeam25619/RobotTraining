package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class DriveSticks extends CommandBase {
    private final Drivetrain mSubsystem;

    private final GamepadEx mController;
    private Telemetry mTelemetry;

    public DriveSticks(Drivetrain subsystem, GamepadEx controller, Telemetry telemetry) {
        mSubsystem = subsystem;
        mController = controller;
        mTelemetry = telemetry;



        addRequirements(mSubsystem);
    }

    @Override
    public void initialize() { mSubsystem.startDriving(); }

    @Override
    public void execute() {
        mTelemetry.addData("Left stick X", mController.getLeftX());
        mTelemetry.addData("Left stick Y", mController.getLeftY());

    }

    @Override
    public void end(boolean interrupted) {
        mSubsystem.stopDriving();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
