package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drivetrain extends SubsystemBase {
    private final Motor leftDrive;
    private final Motor rightDrive;

    private final GamepadEx mController1;

    private final Telemetry mTelemetry;

    private boolean driving = false;

    public Drivetrain(HardwareMap hardwareMap, GamepadEx controller1, Telemetry telemetry) {
        leftDrive = new Motor(hardwareMap, "left_drive");
        rightDrive = new Motor(hardwareMap, "right_drive");

        leftDrive.setInverted(false);
        rightDrive.setInverted(true);

        leftDrive.setRunMode(Motor.RunMode.RawPower);
        rightDrive.setRunMode(Motor.RunMode.RawPower);

        mController1 = controller1;

        mTelemetry = telemetry;
    }

    @Override
    public void periodic() {
        mTelemetry.addData("X axis", mController1.getLeftX());
        mTelemetry.addData("Y axis", mController1.getLeftY());
        mTelemetry.addData("Driving?", this.driving);

        if (this.driving) {
            // driveStraight(mController1.getLeftY());
            drive(mController1.getLeftY(), mController1.getLeftX());
        }
    }

    public void startDriving() {
        this.driving = true;
    }

    public void stopDriving() {
        this.driving = false;
    }

    private void driveStraight(double forwardPower) {
        mTelemetry.addData("Drive Power L", forwardPower);
        mTelemetry.addData("Drive Power R", forwardPower);
        leftDrive.set(forwardPower);
        rightDrive.set(forwardPower);
    }

    private void drive(double forward, double turn) {
        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(forward) + Math.abs(turn), 1.0);
        double leftPower = (forward + turn) / denominator;
        double rightPower = (forward - turn) / denominator;
        mTelemetry.addData("Drive Power L", leftPower);
        mTelemetry.addData("Drive Power R", rightPower);
        leftDrive.set(leftPower);
        rightDrive.set(rightPower);
    }

    public void driveForward() {
        mTelemetry.addData("Drive Power L", 0.5);
        mTelemetry.addData("Drive Power R", 0.5);
        leftDrive.set(0.5);
        rightDrive.set(0.5);
    }

    public void driveReverse() {
        mTelemetry.addData("Drive Power L", -0.5);
        mTelemetry.addData("Drive Power R", -0.5);
        leftDrive.set(-0.5);
        rightDrive.set(-0.5);
    }

    public void rotateLeft() {
        mTelemetry.addData("Drive Power L", -0.5);
        mTelemetry.addData("Drive Power R", 0.5);
        leftDrive.set(-0.5);
        rightDrive.set(0.5);
    }

    public void rotateRight() {
        mTelemetry.addData("Drive Power L", 0.5);
        mTelemetry.addData("Drive Power R", -0.5);
        leftDrive.set(0.5);
        rightDrive.set(-0.5);
    }

    public void stopDrive() {
        leftDrive.set(0.0);
        rightDrive.set(0.0);
    }
}
