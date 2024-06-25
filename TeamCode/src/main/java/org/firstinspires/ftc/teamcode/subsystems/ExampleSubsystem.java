package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.SensorRevTOFDistance;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ExampleSubsystem extends SubsystemBase {
    private final Motor motor;
    private final SensorColor colorSensor;
    private final SensorRevTOFDistance distanceSensor;

    private final Telemetry telemetry;

    public ExampleSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        motor = new Motor(hardwareMap, "test_motor");
        motor.setRunMode(Motor.RunMode.RawPower);

        colorSensor = new SensorColor(hardwareMap, "sensor_color");
        distanceSensor = new SensorRevTOFDistance(hardwareMap, "sensor_color");
    }

    public void setMotorPower(double percentOutput) {
        percentOutput = Math.min(1.0, percentOutput);
        percentOutput = Math.max(-1.0, percentOutput);
        motor.set(percentOutput);
    }

    @Override
    public void periodic() {
        telemetry.addData("Distance Reading: ", distanceSensor.getDistance(DistanceUnit.CM));
//        int[] argb = colorSensor.getARGB();
//        telemetry.addData("Color ARGB Reading: ", "(" + argb[0] + "," + argb[1] + "," + argb[2] + "," + argb[3] + ")");
    }

    public double getPowerByDistance() {
        double distance = distanceSensor.getDistance(DistanceUnit.CM);

        if (distance < 1.0) return 0.0;

        double rawPower = distance / 10.0;
        rawPower = Math.max(0.1, rawPower);
        rawPower = Math.min(1.0, rawPower);
        return rawPower;
    }
}
