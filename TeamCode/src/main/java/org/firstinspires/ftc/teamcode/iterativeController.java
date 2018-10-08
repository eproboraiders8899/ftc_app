package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="iterativeController", group="Iterative Opmode")

public class iterativeController extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    MyHardwarePushbot robot = new MyHardwarePushbot();

    double leftPower = 0;
    double rightPower = 0;
    double liftPower = 0;

    @Override
    public void init() {



        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {



        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        // Setup a variable for each drive wheel to save power level for telemetry

        // double drive = -gamepad1.left_stick_y;
        // double turn  =  gamepad1.right_stick_x;
        // leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        // rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.


        // Throttle the power of the wheels if the difference between the request and current power is too big.


        // TODO: Improve throttling.

        if (java.lang.Math.abs(gamepad1.left_stick_y - leftPower) > .1) {
            leftPower = leftPower + (gamepad1.left_stick_y - leftPower) / 10;
        }
        else {
            leftPower  = gamepad1.left_stick_y;
        }

        if (java.lang.Math.abs(gamepad1.right_stick_y - rightPower) > .1) {
            rightPower = rightPower + (gamepad1.right_stick_y - rightPower) / 10;

        }
        else {
            rightPower  = gamepad1.right_stick_y;
        }


        // Send calculated power to wheels
        robot.leftDrive.setPower(leftPower);
        robot.rightDrive.setPower(rightPower);

        // Set the power of the lift based on if the trigger and/or bumper on the left side of the second controller are pressed

        if(gamepad2.left_bumper == true) {
            liftPower = .5;
        }
        else if (gamepad2.left_trigger == 1) {
            liftPower = -.5;
        }
        else {
            liftPower = 0;
        }
        robot.leftArm.setPower(liftPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "Y'ALL WANT TO KNOW WHAT THE MOTOR SPEEDS ARE? HERE YE GO: \n Left: (%.2f), Right: (%.2f), Lift: (%.2f)", leftPower, rightPower, liftPower);
        telemetry.update();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}
