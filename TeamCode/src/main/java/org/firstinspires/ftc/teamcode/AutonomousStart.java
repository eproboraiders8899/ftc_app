package org.firstinspires.ftc.teamcode;

import java.lang.Math;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Thread.sleep;

// @Disabled
public class AutonomousStart extends LinearOpMode {

    public void runOpMode() {}

    private ElapsedTime runtime = new ElapsedTime();

    double liftPower = 0;

    // static final double COUNTS_PER_MOTOR_REV  = 1440;
    // static final double DRIVE_GEAR_REDUCTION  = 2.0;
    // static final double WHEEL_DIAMETER_INCHES = 4.0;
    // static final double COUNTS_PER_INCH       = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES);
    static final double COUNTS_PER_MOTOR_REV     = 1120;
    static final double WHEEL_RADIUS_INCHES      = 2.0;
    static final double COUNTS_PER_INCH          = (COUNTS_PER_MOTOR_REV) / (WHEEL_RADIUS_INCHES * Math.PI * 2);
    static final double DRIVE_SPEED              = 0.6;
    static final double TURN_SPEED               = 0.5;

    MyHardwarePushbot robot = new MyHardwarePushbot();

    public void drive(double speed, double distance) {

        if (opModeIsActive()) {
            robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
            int leftMoveCounts  = (int) (leftDistance * 1);
            int rightMoveCounts = (int) (rightDistance * 1);
            int newLeftTarget   = robot.leftDrive.getCurrentPosition()  + leftMoveCounts;
            int newRightTarget  = robot.rightDrive.getCurrentPosition() + rightMoveCounts;

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

    public void turn(double speed, double duration) {

        if (opModeIsActive()) {

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(speed, -1.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(-speed);


            runtime.reset();

            while (runtime.seconds() < duration) {}

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);


        }

    }
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget  = robot.leftDrive.getCurrentPosition()  + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }
    public void initialize() {

        robot.init(hardwareMap);

        robot.leftClaw.setPosition(1);

        telemetry.addData(">", "Robot Ready.");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
    }

    public void dismount() {

        // Lower the lift that holds the robot to the lander.

        moveLift(1, 3.4);

        // Drive away *slightly* from the lander.

        turn(-.5, .5);

        drive(.5, -180);

        turn(-.5, 1.25);

        // Drive out of the lander zone, but not so far as to disturb the minerals on the field.

        drive(1, 360);

    }

    public void depositMarker() {

        runtime.reset();

        // Deposit the team marker into the team base.

        robot.leftClaw.setPosition(.5);

        while(runtime.seconds() <= 1) {}

        robot.leftClaw.setPosition(1);

    }
}