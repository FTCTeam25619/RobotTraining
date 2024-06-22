package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ExampleSubsystem;

public class ExampleDriveMotorCommand extends CommandBase {
    private final ExampleSubsystem mSubsystem;
    private final double percentOutput;

    public ExampleDriveMotorCommand(ExampleSubsystem subsystem, double motorPercentOutput) {
        mSubsystem = subsystem;

        percentOutput = motorPercentOutput;

        addRequirements(mSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        mSubsystem.setMotorPower(percentOutput);
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
