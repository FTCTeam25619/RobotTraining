package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.ExampleComplexDriveCommand;
import org.firstinspires.ftc.teamcode.commands.ExampleDriveMotorCommand;
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

    // Controllers
    private final GamepadEx controller1;
    private final GamepadEx controller2;

    // Gamepad Buttons
    private GamepadButton controller1A;
    private GamepadButton controller1DPadUp;
    private GamepadButton controller1DPadDown;

    public static Telemetry telemetry;

    public RobotTraining(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {
        // Setup Telemetry
        RobotTraining.telemetry = telemetry;

        // Subsystems
        exampleSubsystem = new ExampleSubsystem(hardwareMap, RobotTraining.telemetry);

        // Controllers
        controller1 = new GamepadEx(gamepad1);
        controller2 = new GamepadEx(gamepad2);

        setupControllerButtons();
        configureButtonMappings();
    }

    public void onStop() {}

    private void setupControllerButtons() {
        controller1A = new GamepadButton(controller1, GamepadKeys.Button.A);
        controller1DPadUp = new GamepadButton(controller1, GamepadKeys.Button.DPAD_UP);
        controller1DPadDown = new GamepadButton(controller1, GamepadKeys.Button.DPAD_DOWN);
    }

    private void configureButtonMappings() {
        controller1A.whenHeld(
                new ExampleComplexDriveCommand(exampleSubsystem, RobotTraining.telemetry)
        );
        controller1DPadUp.whenHeld(
                new ExampleDriveMotorCommand(exampleSubsystem, 0.5)
        );
        controller1DPadDown.whenHeld(
                new ExampleDriveMotorCommand(exampleSubsystem, -0.5)
        );
    }
}
