package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name="mysecondlinear", group="Linear Opmode")

public class mysecondlinear extends LinearOpMode{

    // Create a new HardwarePushbot called "robot"
    MyHardwarePushbot robot = new MyHardwarePushbot();

    public void runOpMode() {

        robot.init(hardwareMap);


        telemetry.addData(">", "Robot Ready.");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        driveForward(.5, 10);
        TurnLeft(.5, 10);

        telemetry.addData("Path", "Complete");
        telemetry.update();


    }

    public void driveForward(double speed, double distance) {
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
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

            }
            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
        void TurnLeft(double speed, double turn){
        if(opModeIsActive()){
            // Define the target for the robot to reach.

            int moveCounts = (int)(turn * 2);
            int newLeftTarget = robot.leftDrive.getCurrentPosition() + moveCounts;
            int newRightTarget = robot.rightDrive.getCurrentPosition() + moveCounts;

            // Set the target positions for the robot, and set the robots to target mode.
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(speed);

            while (opModeIsActive() &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

            }
            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }


}

