package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class DriveSticks extends CommandBase {
    private final Drivetrain mSubsystem;

    public DriveSticks(Drivetrain subsystem) {
        mSubsystem = subsystem;

        addRequirements(mSubsystem);
    }

    @Override
    public void initialize() { mSubsystem.startDriving(); }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        mSubsystem.stopDriving();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
