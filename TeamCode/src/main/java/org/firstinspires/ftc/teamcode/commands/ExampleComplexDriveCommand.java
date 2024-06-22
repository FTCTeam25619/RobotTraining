package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ExampleSubsystem;

public class ExampleComplexDriveCommand extends CommandBase {
    private final ExampleSubsystem mSubsystem;

    private final Telemetry mTelemetry;

    public ExampleComplexDriveCommand(ExampleSubsystem subsystem, Telemetry telemetry) {
        mSubsystem = subsystem;
        mTelemetry = telemetry;

        addRequirements(mSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double power = mSubsystem.getPowerByDistance();

        mTelemetry.addData("Power by Distance", power);

        mSubsystem.setMotorPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        mSubsystem.setMotorPower(0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
