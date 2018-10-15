package org.firstinspires.ftc.teamcode;

import java.lang.Math;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Thread.sleep;

public class AutonomousStart extends LinearOpMode {

    //TODO: Refine headingDrive(), find actual values instead of placeholders, eventually

    private ElapsedTime runtime = new ElapsedTime();

    double liftPower = 0;

    MyHardwarePushbot robot = new MyHardwarePushbot();

    public void runOpMode() {

        robot.init(hardwareMap);

        // Create a placeholder variable to be used in place of actual distances. THIS IS TEMPORARY!
        int placeholder = 1;

        telemetry.addData(">", "Robot Ready.");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Lower the lift that holds the robot to the lander.

        moveLift(1, 10);

        // Drive away *slightly* from the lander.

        drive(1, 360);

        // Lower the lift back to the robot. NVM


        // Drive out of the lander zone, but not so far as to disturb the minerals on the field.

        drive(1, 360);

        // Turn 90 degrees.

        headingDrive(1, 360, -360);

        // Drive forward to bypass the minerals on the field.

        drive(1, 1440);

        // Turn 225 degrees.

        headingDrive(1, 900, -900);

        // Drive forward into the base.

        drive(1, 1215);

        telemetry.addData("Path", "Complete");
        telemetry.update();

    }

    public void drive(double speed, double distance) {

        if (opModeIsActive()) {

            // Define the target for the robot to reach.
            int moveCounts = (int) (distance * 1);
            int newLeftTarget = robot.leftDrive.getCurrentPosition() + moveCounts;
            int newRightTarget = robot.rightDrive.getCurrentPosition() + moveCounts;

            // Set the target positions for the robot, and set the robots to target mode.
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(speed);


            while (opModeIsActive() &&
                    robot.leftDrive.isBusy() && robot.rightDrive.isBusy()) {}

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }

    public void moveLift(double speed, double duration) {

        // Runs the lift for a set time and at a set speed.

        speed = Range.clip((speed), -1.0, 1.0);
        robot.leftArm.setPower(speed);

        runtime.reset();

        while (runtime.seconds() < duration) {}

        robot.leftArm.setPower(0);

    }

    public void headingDrive(double speed, double leftDistance, double rightDistance) {

        if (opModeIsActive()) {

            // Define the target for the robot to reach.
            int leftMoveCounts = (int) (leftDistance * 1);
            int rightMoveCounts = (int) (rightDistance * 1);
            int newLeftTarget = robot.leftDrive.getCurrentPosition() + leftMoveCounts;
            int newRightTarget = robot.rightDrive.getCurrentPosition() + rightMoveCounts;

            // Set the target positions for the robot, and set the robots to target mode.
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(speed);


            while (opModeIsActive() &&
                    robot.leftDrive.isBusy() && robot.rightDrive.isBusy()) {
            }

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }

}