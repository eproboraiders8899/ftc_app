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
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.CameraDevice;

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

// The class AutonomousStart creates all of the variables and functions
// used in BaseStart and CraterStart.
// Both of these classes extend AutonomousStart.

public class AutonomousStart extends LinearOpMode {

    // runOpMode is empty, as it would be overwritten by BaseStart and CraterStart.

    public void runOpMode() {}

    // ElapsedTime is used for "timeouts" when searching for minerals and driving in general.
    // The goal is to never hit said timeouts, though they are in place to make sure
    // that we don't go over 30 seconds during Autonomous.

    private ElapsedTime runtime = new ElapsedTime();

    // These variables are used for calculating the amount of "counts" one of our drive motors
    // should move to be able to move one inch.
    // Unfortunately, 1 "inch" in our program does not correspond to one inch on the playing field.

    static final double COUNTS_PER_MOTOR_REV     = 1120;
    static final double WHEEL_RADIUS_INCHES      = 2.0;
    static final double COUNTS_PER_INCH          = (COUNTS_PER_MOTOR_REV) /
            (WHEEL_RADIUS_INCHES * Math.PI * 2);
    static final double DRIVE_SPEED              = 0.6;

    // These variables are used in the initialization of object detection.

    public boolean vuforiaLoaded = false;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AdTtd8D/////AAABmbAte/IPjExpuQ7N9ZZbhFUoIphG3O/GR" +
            "S7D1JumDL5+8Z1ZqThpEqeUaCeFe5EsNKhwqIe4/AcxbyQ7Ya3JlG4qa7nSDD5G3Tp02egg6kzczLcCN14P" +
            "wFKWNi/r1/TPjy8kvfjA5Ve9fSzOt7ZFb5eHDZ2bS47jJpDb0uHJl1UiXWYMGMtbSF3BUXTKCxGOu7D83FZ" +
            "QABJ0f8udnmc94zU+pQxWYfLQiRb7BDoax/hyzEXrPt3/uri4AL0F2O5TyE2SlIZBZPs6K2OcXL2GtsOwiT" +
            "/jQDr7msaJoH7YqGi+uT+VZSTAvURf4SJ0TGc6Sk5513jsr6bLZCk1KPE1aYB2Hi9H0OXHnSju0r6V7M/1";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    // MyHardwarePushbot is a variation on the HardwarePushbot example, modified for use
    // in our code specifically.

    MyHardwarePushbot robot = new MyHardwarePushbot();

    public void moveLift(double speed, double duration) {

        // Runs the lift for a set time and at a set speed.

        speed = Range.clip((speed), -1.0, 1.0);
        robot.landerLift.setPower(speed);

        runtime.reset();

        while (robot.limitButton.getState() == true) {}

        robot.landerLift.setPower(0);
    }

    // TODO: Remove this function and replace the single instance of it with our new turn function.

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

    // encoderDrive is our main function used to drive our robot during Autonomous.

    // It moves the "left" motor (leftInches) units and the "right" motor (rightInches) units
    // at a speed of (speed), being sure to shut off the function after (timeoutS) seconds.

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opMode is still active.

        if (opModeIsActive()) {

            // Determine a "target" position for the motors to go to based on the motor's
            // current position. Strictly speaking, the number of units that the robot's motors
            // have already moved plus the number of inches the function requests to move
            // (of course, multiplied by the number of counts per inch).

            // INCHES IS A TERM USED TO DEFINE A "UNIT" THE ROBOT MOVES, AND DOES NOT EQUAL
            // AN ACTUAL INCH.

            newLeftTarget  = robot.leftDrive.getCurrentPosition()  +
                    (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() +
                    (int) (rightInches * COUNTS_PER_INCH);

            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);


            // Set the motors to the "Run To Position" mode, where the motors will run to a set
            // "target" which is pre-defined above.

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Reset the timer used for the "timeout," and sets the motors to
            // their designated speed.

            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));


            // Have the motors run while NEITHER of the motors have reached their "target"
            // position. In the case that one of the motors "fails" in some way,
            // the function will still stop.

            // As a "failsafe," the function will automatically stop after a set amount of seconds,
            // defined when the function is called.

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d",
                        newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Shut off the motors.
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off "Run To Position" mode.
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void initialize() {

        // Initialize the robot, which was created above.

        robot.init(hardwareMap);

        // Initialize our robot's object detection, as defined below.

        initObjectDetection();

        // "Lock" the markerHolder motor in place so our team marker doesn't fall out.

        robot.markerHolder.setPosition(1);

        // Activate TensorFlow.

        if (tfod != null) {
            tfod.activate();
        }

        // Display in Telemetry that the robot is, in fact, initialized.

        telemetry.addData(">", "Robot Ready.");
        telemetry.update();

        // Wait for the team Coach to press start.
        waitForStart();
    }

    // Although flashSet is redundant (the command it replaces only takes up one line,
    // the function exists for the sake of simplicity. It simply sets the flash on the phone
    // connected to our robot to either on or off.

    public void flashSet(boolean mode) {
        CameraDevice.getInstance().setFlashTorchMode(mode);
    }

    // Moves the "mounting" lift until the limit switch on our robot is pressed,
    // signaling that the robot has touched the ground. Afterwards, moves as to move
    // "out of the way of" the lander so our robot doesn't get caught on it.

    public void dismount() {

        telemetry.update();

        moveLift(1, 0.1);

        robot.limitServo.setPosition(1);

        // Drive away *slightly* from the lander.

        // Editing code past this point affects Base and CraterStart drastically.
        // Technically, the robot is "dismounted" before this.

        turn(-.30, 1);

        encoderDrive(.35,2,2,2);
    }

    // Deposits the team marker into the team base.

    public void depositMarker() {

        runtime.reset();

        robot.markerHolder.setPosition(.5);

        while(runtime.seconds() <= 1) {}

        robot.markerHolder.setPosition(1);
    }

    // Initializes Vuforia using the Vuforia key.

    public void initVuforia() {

        // Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        vuforiaLoaded = true;
    }

    // Initializes TensorFlow and loads the mineral models.

    public void initTfod() {

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParameters =
                new TFObjectDetector.Parameters(tfodMonitorViewId);

        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    // initObjectDetection initializes Vuforia, and then initializes TensorFlow if it can.
    // TensorFlow is SUPPOSED to initialize; there is a major problem if it doesn't.

    public void initObjectDetection() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {

            initTfod();
        }
        else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }

    // Checks if the robot's phone's camera is seeing mineral(s), and then checks if the mineral(s)
    // that it is seeing is gold.

    public boolean seeingGold() {

        // isGold is returned at the end of the function; this function can be used on a whim
        // to check if the robot is seeing a gold mineral.

        boolean isGold = false;

        if (tfod != null) {

            runtime.reset();

            // Check, for a maximum of 5 seconds, if the robot sees any minerals, and then
            // continue.

            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            int recognitionSize = 0;

            while( (runtime.seconds() < 5) && (recognitionSize == 0) ) {
                updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    recognitionSize = updatedRecognitions.size();
                }
            }

            if (updatedRecognitions != null) {

                // Display whether the phone detects a mineral on the screen.

                telemetry.addData("# Object Detected", updatedRecognitions.size());
                telemetry.update();
                sleep(1000);

                if (updatedRecognitions.size() >= 1) {

                    // "Hi Adam" is for debug purposes.

                    telemetry.addData("Size = 1 ", "Hi Adam");
                    telemetry.update();
                    sleep(1000);

                    // Check if the mineral(s) the robot sees are gold, and updates the variable
                    // isGold accordingly.

                    for (Recognition recognition : updatedRecognitions) {

                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {

                            telemetry.addData("Confidence", recognition.getConfidence());
                            telemetry.addData("Height", recognition.getHeight());
                            telemetry.update();
                            sleep(1000);


                            isGold = true;
                            telemetry.addData("The Mineral is Gold", isGold);
                            telemetry.update();
                            sleep(1000);
                        }
                        else{

                            isGold = false;
                            telemetry.addData("The Mineral is Silver", isGold);
                            telemetry.update();
                            sleep(1000);
                        }
                    }
                }
            }
        }

        // If TensorFlow fails for whatever reason, default to the fact that the camera is not
        // seeing gold, which is a safer option.

        else {

            telemetry.addData("Tensor Flow Failed",
                    "Defaulting to saying gold is not being seen");
        }

        // Again, returns isGold.

        return isGold;
    }
}