package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class DriveReverse extends CommandBase {
    private final Drivetrain mSubsystem;

    public DriveReverse(Drivetrain subsystem) {
        mSubsystem = subsystem;

        addRequirements(mSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        mSubsystem.driveReverse();
    }

    @Override
    public void end(boolean interrupted) {
        mSubsystem.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

