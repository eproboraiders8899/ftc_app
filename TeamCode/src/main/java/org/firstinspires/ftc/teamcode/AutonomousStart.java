package org.firstinspires.ftc.teamcode;

import java.lang.Math;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

public class AutonomousStart extends LinearOpMode {

    //TODO: Refine headingDrive(), find actual values instead of placeholders, eventually
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

        moveLift(1, 19000);



        // Drive away *slightly* from the lander.

        drive(placeholder, placeholder);

        // Lower the lift back to the robot. NVM


        // Drive out of the lander zone, but not so far as to disturb the minerals on the field.

        drive(placeholder, placeholder);

        // Turn 90 degrees.

        headingDrive(placeholder, placeholder, placeholder);

        // Drive forward to bypass the minerals on the field.

        drive(placeholder, placeholder);

        //Turn 225 degrees.

        headingDrive(placeholder, placeholder, placeholder);

        // Drive forward into the base.

        drive(placeholder, placeholder);

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
                    robot.leftDrive.isBusy() && robot.rightDrive.isBusy()) {
            }

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }


    }

    public void moveLift(double speed, double duration) {

        // This is pretty much a carbon copy of the above drive() function, but used only for the lift on the robot.

        // Define the target for the lift to reach.
       // int moveCounts = (int) (distance * 1);
       // int LiftTarget = robot.leftDrive.getCurrentPosition() + moveCounts;

       // robot.leftArm.setTargetPosition(LiftTarget);

       // robot.leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        speed = Range.clip((speed), -1.0, 1.0);
        robot.leftArm.setPower(speed);

        sleep(duration)

        while (opModeIsActive() &&
                robot.leftArm.isBusy()) {
        }

        robot.leftArm.setPower(0);

        robot.leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

