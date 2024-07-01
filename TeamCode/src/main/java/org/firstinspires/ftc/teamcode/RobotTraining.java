package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveForward;
import org.firstinspires.ftc.teamcode.commands.DriveReverse;
import org.firstinspires.ftc.teamcode.commands.DriveSticks;
import org.firstinspires.ftc.teamcode.commands.ExampleComplexDriveCommand;
import org.firstinspires.ftc.teamcode.commands.ExampleDriveMotorCommand;
import org.firstinspires.ftc.teamcode.commands.RotateLeft;
import org.firstinspires.ftc.teamcode.commands.RotateRight;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.ExampleSubsystem;

/* To connect to the Control Hub device via Wi-Fi:
   - Connect Wi-Fi to the FTC-25619 network
   - In command line, run: adb connect 192.168.43.1:5555
   - To check that it is connected, run: adb devices -l
   - Check that the REV Robotics Control Hub shows up above
   - in Android Studio with a green dot (connected)
 */

public class RobotTraining extends Robot {
    // Subsystems
    private final ExampleSubsystem exampleSubsystem;
    private final Drivetrain drivetrain;

    // Controllers
    private final GamepadEx controller1;
    private final GamepadEx controller2;

    // Gamepad Buttons
    private GamepadButton controller1A;
    private GamepadButton controller1DPadUp;
    private GamepadButton controller1DPadDown;
    private GamepadButton controller1DPadLeft;
    private GamepadButton controller1DPadRight;
    private GamepadButton controller1B;
    private GamepadButton controller1Y;

    public static Telemetry telemetry;

    public RobotTraining(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {
        // Setup Telemetry
        RobotTraining.telemetry = telemetry;

        // Controllers
        controller1 = new GamepadEx(gamepad1);
        controller2 = new GamepadEx(gamepad2);

        // Subsystems
        exampleSubsystem = new ExampleSubsystem(hardwareMap, RobotTraining.telemetry);
        drivetrain = new Drivetrain(hardwareMap,controller1, RobotTraining.telemetry);
        drivetrain.setDefaultCommand(new DriveSticks(drivetrain, controller1, RobotTraining.telemetry));



        setupControllerButtons();
        configureButtonMappings();
    }

    public void onStop() {}

    private void setupControllerButtons() {
        controller1A = new GamepadButton(controller1, GamepadKeys.Button.A);
        controller1DPadUp = new GamepadButton(controller1, GamepadKeys.Button.DPAD_UP);
        controller1DPadDown = new GamepadButton(controller1, GamepadKeys.Button.DPAD_DOWN);
        controller1DPadLeft = new GamepadButton(controller1, GamepadKeys.Button.DPAD_LEFT);
        controller1DPadRight = new GamepadButton(controller1, GamepadKeys.Button.DPAD_RIGHT);
        controller1B = new GamepadButton(controller1, GamepadKeys.Button.B);
        controller1Y = new GamepadButton(controller1, GamepadKeys.Button.Y);
    }

    private void configureButtonMappings() {
        controller1A.whenHeld(
                new ExampleComplexDriveCommand(exampleSubsystem, RobotTraining.telemetry)
        );
        controller1B.whenHeld(
                new ExampleDriveMotorCommand(exampleSubsystem, 0.5)
        );
        controller1Y.whenHeld(
                new ExampleDriveMotorCommand(exampleSubsystem, -0.5)
        );
        controller1DPadUp.whenHeld(
                new DriveForward(drivetrain)
        );
        controller1DPadDown.whenHeld(
                new DriveReverse(drivetrain)
        );
        controller1DPadLeft.whenHeld(
                new RotateLeft(drivetrain)
        );
        controller1DPadRight.whenHeld(
                new RotateRight(drivetrain)
        );
    }
}
