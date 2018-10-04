package org.firstinspires.ftc.teamcode;

import android.util.Range;

public class AutonomousStart extends LinearOpMode {

    //TODO: Refine headingDrive(), find actual values instead of placeholders, eventually

    MyHardwarePushbot robot = new MyHardwarePushbot();

    public void runOpMode() {

        robot.init(hardwareMap);

        // Create a placeholder variable to be used in place of actual distances. THIS IS TEMPORARY!
        public static final int PLACEHOLDER = 1;

        telemetry.addData(">", "Robot Ready.");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Lower the lift that holds the robot to the lander.

        moveLift(PLACEHOLDER, PLACEHOLDER);

        // Drive away *slightly* from the lander.

        drive(PLACEHOLDER, PLACEHOLDER);

        // Lower the lift back to the robot.

        moveLift(PLACEHOLDER, PLACEHOLDER);

        // Drive out of the lander zone, but not so far as to disturb the minerals on the field.

        drive(PLACEHOLDER, PLACEHOLDER);

        // Turn 90 degrees.

        headingDrive(PLACEHOLDER, PLACEHOLDER, PLACEHOLDER);

        // Drive forward to bypass the minerals on the field.

        drive(PLACEHOLDER, PLACEHOLDER);

        //Turn 225 degrees.

        headingDrive(PLACEHOLDER, PLACEHOLDER, PLACEHOLDER);

        // Drive forward into the base.

        drive(PLACEHOLDER, PLACEHOLDER);

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
            robot.leftFrontDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.rightFrontDrive.setTargetPosition(newRightTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.leftFrontDrive.setPower(speed);
            robot.rightDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);


            while (opModeIsActive() &&
                    robot.leftDrive.isBusy() && robot.leftFrontDrive.isBusy() && robot.rightDrive.isBusy() && robot.rightFrontDrive.isBusy()) {
            }

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.leftFrontDrive.setPower(0);
            robot.rightDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }


    }

    public void moveLift(double speed, double distance) {

        // This is pretty much a carbon copy of the above drive() function, but used only for the lift on the robot.

        // Define the target for the lift to reach.
        int moveCounts = (int) (distance * 1);
        int LiftTarget = robot.leftDrive.getCurrentPosition() + moveCounts;

        robot.leftArm.setTargetPosition(LiftTarget);

        robot.leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        speed = Range.clip(Math.abs(speed), 0.0, 1.0);
        robot.leftArm.setPower(speed);

        while (opModeIsActive() &&
                robot.leftArm.isBusy() {
        }

        robot.leftArm.setPower(0);

        robot.leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void headingDrive(double speed, double leftDistance, double rightDistance) {

        if (opModeIsActive()) {

            // Define the target for the robot to reach.
            int leftMoveCounts = (int) (distance * 1);
            int rightMoveCounts = (int) (distance * 1);
            int newLeftTarget = robot.leftDrive.getCurrentPosition() + moveCounts;
            int newRightTarget = robot.rightDrive.getCurrentPosition() + moveCounts;

            // Set the target positions for the robot, and set the robots to target mode.
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.leftFrontDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.rightFrontDrive.setTargetPosition(newRightTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.leftFrontDrive.setPower(speed);
            robot.rightDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);


            while (opModeIsActive() &&
                    robot.leftDrive.isBusy() && robot.leftFrontDrive.isBusy() && robot.rightDrive.isBusy() && robot.rightFrontDrive.isBusy()) {
            }

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.leftFrontDrive.setPower(0);
            robot.rightDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }


    }

}

