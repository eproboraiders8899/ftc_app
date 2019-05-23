package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static java.lang.Double.toString;
import static java.lang.Double.valueOf;

@TeleOp(name="Rotator Test", group="Iterative Opmode")

public class RotatorTest extends OpMode {

    MyHardwarePushbot robot = new MyHardwarePushbot();

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {

        robot.init(hardwareMap);

        robot.markerHolder.setPosition(1);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        robot.leftRotator.setPower(gamepad1.left_stick_y);
        robot.rightRotator.setPower(-gamepad1.left_stick_y);
    }

    @Override
    public void stop() {
    }
}
