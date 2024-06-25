package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drivetrain extends SubsystemBase {
    private Motor leftDrive;
    private Motor rightDrive;

    private Telemetry mTelemetry;

    public Drivetrain(HardwareMap hardwareMap, Telemetry telemetry) {
        leftDrive = new Motor(hardwareMap, "left_drive");
        rightDrive = new Motor(hardwareMap, "right_drive");

        leftDrive.setInverted(false);
        rightDrive.setInverted(true);

        leftDrive.setRunMode(Motor.RunMode.RawPower);
        rightDrive.setRunMode(Motor.RunMode.RawPower);

        mTelemetry = telemetry;
    }

    @Override
    public void periodic() {
        mTelemetry.addData("Left motor: ", leftDrive.getRate());
    }

    public void driveForward() {
        leftDrive.set(0.5);
        rightDrive.set(0.5);
    }

    public void driveReverse() {
        leftDrive.set(-0.5);
        rightDrive.set(-0.5);
    }

    public void rotateLeft() {
        leftDrive.set(-0.5);
        rightDrive.set(0.5);
    }

    public void rotateRight() {
        leftDrive.set(0.5);
        rightDrive.set(-0.5);
    }

    public void stopDrive() {
        leftDrive.set(0.0);
        rightDrive.set(0.0);
    }
}
