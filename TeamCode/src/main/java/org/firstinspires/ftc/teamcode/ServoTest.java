package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "ServoTest", group = "Autonomous")
@Disabled

public class ServoTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    MyHardwarePushbot robot = new MyHardwarePushbot();

    public void runOpMode() {

        robot.init(hardwareMap);

        robot.leftClaw.setPosition(1);

        telemetry.addData(">", "Robot Ready.");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        runtime.reset();

        robot.leftClaw.setPosition(.50);

        while (runtime.seconds() < 3.0) {}

        robot.leftClaw.setPosition(1);

        while (runtime.seconds() < 6.0) {}

        telemetry.addData(">", "Path Complete.");
        telemetry.update();
    }
}
