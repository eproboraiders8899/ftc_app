package org.firstinspires.ftc.teamcode;

import java.lang.Math;
import java.util.List;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static java.lang.Thread.sleep;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;



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

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AdTtd8D/////AAABmbAte/IPjExpuQ7N9ZZbhFUoIphG3O/GRS7D1JumDL5+8Z1ZqThpEqeUaCeFe5EsNKhwqIe4/AcxbyQ7Ya3JlG4qa7nSDD5G3Tp02egg6kzczLcCN14PwFKWNi/r1/TPjy8kvfjA5Ve9fSzOt7ZFb5eHDZ2bS47jJpDb0uHJl1UiXWYMGMtbSF3BUXTKCxGOu7D83FZQABJ0f8udnmc94zU+pQxWYfLQiRb7BDoax/hyzEXrPt3/uri4AL0F2O5TyE2SlIZBZPs6K2OcXL2GtsOwiT/jQDr7msaJoH7YqGi+uT+VZSTAvURf4SJ0TGc6Sk5513jsr6bLZCk1KPE1aYB2Hi9H0OXHnSju0r6V7M/1";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    String goldPosition = null;

    MyHardwarePushbot robot = new MyHardwarePushbot();

    public void drive(double speed, double distance) {

        if (opModeIsActive()) {
            robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
           // robot.liftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           // robot.liftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            // Define the target for the robot to reach.
            int moveCounts = (int) (distance * 1);
            int newLeftTarget = robot.leftDrive.getCurrentPosition() + moveCounts;
            int newRightTarget = robot.rightDrive.getCurrentPosition() + moveCounts;
            //int newLiftTarget = robot.liftDrive.getCurrentPosition() + moveCounts;

            // Set the target positions for the robot, and set the robots to target mode.
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
           // robot.liftDrive.setTargetPosition(newLiftTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           // robot.liftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(speed);
            //robot.LiftDrive.setPower(speed);

            while (opModeIsActive() &&
                    robot.leftDrive.isBusy() && robot.rightDrive.isBusy()) {}

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
            //robot.liftDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //robot.liftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void moveLift(double speed, double duration) {

        // Runs the lift for a set time and at a set speed.

        speed = Range.clip((speed), -1.0, 1.0);
        robot.leftArm.setPower(speed);

        runtime.reset();

        while (robot.digitalTouch.getState() == true) {}

        runtime.reset();

        while (runtime.seconds() < duration) {}

        robot.leftArm.setPower(0);
    }

    public void headingDrive(double speed, double leftDistance, double rightDistance) {

        if (opModeIsActive()) {

            // Define the target for the robot to reach.
            int leftMoveCounts  = (int) (leftDistance * 1);
            int rightMoveCounts = (int) (rightDistance * 1);
            //int liftMoveCounts = (int) (liftDistance * 1);
            int newLeftTarget   = robot.leftDrive.getCurrentPosition()  + leftMoveCounts;
            int newRightTarget  = robot.rightDrive.getCurrentPosition() + rightMoveCounts;
            //int newLiftTarget  = robot.liftDrive.getCurrentPosition() + rightMoveCounts;

            // Set the target positions for the robot, and set the robots to target mode.
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            // robot.liftDrive.setTargetPosition(newLiftTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // robot.liftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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
            // robot.liftDrive.setPower(0);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            // robot.liftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turn(double speed, double duration) {

        if (opModeIsActive()) {

            // Set the speed of the robot to the "speed" variable in the function.
            speed = Range.clip(speed, -1.0, 1.0);
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(-speed);
           // robot.liftDrive.setPower(-speed);


            runtime.reset();

            while (runtime.seconds() < duration) {}

            // Set the robot's power to 0.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
            //robot.liftDrive.setPower(0);

        }
    }
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        //int newLiftTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget  = robot.leftDrive.getCurrentPosition()  + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
           // newLiftTarget = robot.liftDrive.getCurrentPosition() + (int) (liftInches * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            //robot.liftDrive.setTargetPosition(newLiftTarget);

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           // robot.liftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));
            //robot.liftDrive.setPower(Math.abs(speed));

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
                       // robot.liftDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
            //robot.liftDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //robot.liftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void initialize() {

        robot.init(hardwareMap);

        robot.leftClaw.setPosition(1);

        initObjectDetection();

        telemetry.addData(">", "Robot Ready.");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (tfod != null) {
            tfod.activate();
        }

    }

    public void dismount() {

        // Lower the lift that holds the robot to the lander.

        if (robot.digitalTouch.getState() == true) {
            telemetry.addData("Digital Touch", "Is Not Pressed");
            robot.leftArm.setPower(DRIVE_SPEED);
        } else {
            telemetry.addData("Digital Touch", "Is Pressed");
            robot.leftArm.setPower(0);
            robot.limitSwitch.setPosition(0);

        }

        telemetry.update();

        moveLift(1, 0.25);

        robot.limitSwitch.setPosition(1);

        // Drive away *slightly* from the lander.

        turn(-.5, .5);

        drive(.5, -180);

        turn(-.5, 1.2);

        // Drive out of the lander zone, but not so far as to disturb the minerals on the field.

        drive(-1, 360);

    }

    public void depositMarker() {

        runtime.reset();

        // Deposit the team marker into the team base.

        robot.leftClaw.setPosition(.5);

        while(runtime.seconds() <= 1) {}

        robot.leftClaw.setPosition(1);

    }

    public void initVuforia() {

        // Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    public void initTfod() {

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);

        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public void initObjectDetection() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {

            initTfod();
        }
        else {

            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

    }

    public void getGoldPosition() {

        if (tfod != null) {

            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

            if (updatedRecognitions != null) {

                telemetry.addData("# Object Detected", updatedRecognitions.size());

                if (updatedRecognitions.size() == 2) {

                    int goldMineralX = -1;
                    int silverMineralX = -1;

                    for (Recognition recognition : updatedRecognitions) {

                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {

                            goldMineralX = (int) recognition.getLeft();
                        }
                        else if (silverMineralX == -1) {

                            silverMineralX = (int) recognition.getLeft();
                        }
                    }

                    if (goldMineralX == -1) {
                        goldPosition = "right";
                    }
                    else if (silverMineralX > goldMineralX) {
                        goldPosition = "left";
                    }
                    else {
                        goldPosition = "center";
                    }
                }

                telemetry.update();
            }
        }
    }

    /*
    public float[] senseColor() {

        // Return the color sensor value in HSV form.

        float[] hsvValues = {0, 0, 0};

        Color.RGBToHSV((int) (robot.colorSensor.red() * 255),
                (int) (robot.colorSensor.green() * 255),
                (int) (robot.colorSensor.blue() * 255),
                hsvValues);

        return hsvValues;
    }
    */

    /*
    public boolean compareColors(colorA, colorB, tolerance) {

        // Compare 2 colors(preferably, one of which being senseColor()), and return whether they are the same within a "tolerance" value in percent.

        if(Math.abs(colorA[0] / 3.6 - colorB[0] / 3.6) <= tolerance && colorA[1] * 100 - colorB[1] * 100) <= tolerance && colorA[2] * 100 - colorB[2] * 100) <= tolerance) {
            return true;
        }
        else{
            return false;
        }
    }
    */

}