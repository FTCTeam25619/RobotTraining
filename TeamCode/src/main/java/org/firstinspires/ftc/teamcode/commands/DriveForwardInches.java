
package org.firstinspires.ftc.teamcode.commands;

        import com.arcrobotics.ftclib.command.CommandBase;

        import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class DriveForwardInches extends CommandBase {
    private final Drivetrain mSubsystem;
    private double startingPoint;
    private  double targetDistanceInches;
    private  double drivePower;


    public DriveForwardInches(Drivetrain subsystem,double targetDistance,double power) {
        mSubsystem = subsystem;
        drivePower=power;
        targetDistanceInches=targetDistance;
        addRequirements(mSubsystem);
    }

    @Override
    public void initialize() {
        startingPoint=mSubsystem.getCurrentDistanceInches();
    }

    @Override
    public void execute() {
        mSubsystem.driveForward(drivePower);
    }

    @Override
    public void end(boolean interrupted) {
        mSubsystem.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return mSubsystem.getCurrentDistanceInches() - startingPoint > targetDistanceInches;
    }
}
