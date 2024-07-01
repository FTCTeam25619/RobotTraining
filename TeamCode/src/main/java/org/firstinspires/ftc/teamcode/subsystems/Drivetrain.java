package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drivetrain extends SubsystemBase {
    private Motor leftDrive;
    private Motor rightDrive;

    private Telemetry mTelemetry;
    private GamepadEx driveController;

    private GamepadEx turnController;

    private boolean isDriving = false;

    public Drivetrain(HardwareMap hardwareMap, GamepadEx controller1 , Telemetry telemetry) {
        leftDrive = new Motor(hardwareMap, "left_drive");
        rightDrive = new Motor(hardwareMap, "right_drive");

        leftDrive.setInverted(false);
        rightDrive.setInverted(true);

        leftDrive.setRunMode(Motor.RunMode.RawPower);
        rightDrive.setRunMode(Motor.RunMode.RawPower);

        mTelemetry = telemetry;
        driveController=controller1;
        isDriving = false;
    }

    @Override
    public void periodic() {
        if (isDriving) {
           // driveStraight (driveController.getLeftY());
            drive(driveController.getLeftY(), driveController.getRightX());

        }
    }

    public void startDriving() {
        isDriving = true;
    }

    public void stopDriving() {
        isDriving = false;
    }

    private void driveStraight(double forwardPower) {
        mTelemetry.addData("L power", forwardPower);
        mTelemetry.addData("R power", forwardPower);
        leftDrive.set(forwardPower);
        rightDrive.set(forwardPower);
    }

    private void drive(double forwardPower,double turnPower) {
       double leftPower=forwardPower+turnPower;
       double rightPower=forwardPower-turnPower;
       double maxPower=Math.abs(forwardPower)+Math.abs(turnPower);
       if (maxPower > 1.0){
           leftPower=leftPower/maxPower;
           rightPower=rightPower/maxPower;
       }


        mTelemetry.addData("L power", leftPower);
        mTelemetry.addData("R power", rightPower);
        leftDrive.set(leftPower);
        rightDrive.set(rightPower);
    }

    public void driveForward() {
        mTelemetry.addData("L power", 0.5);
        mTelemetry.addData("R power", 0.5);
        leftDrive.set(0.5);
        rightDrive.set(0.5);
    }

    public void driveReverse() {
        mTelemetry.addData("L power", -0.5);
        mTelemetry.addData("R power", -0.5);
        leftDrive.set(-0.5);
        rightDrive.set(-0.5);
    }

    public void rotateLeft() {
        mTelemetry.addData("L power", -0.5);
        mTelemetry.addData("R power", 0.5);
        leftDrive.set(-0.5);
        rightDrive.set(0.5);
    }

    public void rotateRight() {
        mTelemetry.addData("L power", 0.5);
        mTelemetry.addData("R power", -0.5);
        leftDrive.set(0.5);
        rightDrive.set(-0.5);
    }

    public void stopDrive() {
        leftDrive.set(0.0);
        rightDrive.set(0.0);
    }
}
