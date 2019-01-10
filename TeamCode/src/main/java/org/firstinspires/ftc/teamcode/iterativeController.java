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

@TeleOp(name="Robot (Iterative) Controller", group="Iterative Opmode")

public class iterativeController extends OpMode {

    // MyHardwarePushbot is a variation on the HardwarePushbot example, modified for use
    // in our code specifically.

    MyHardwarePushbot robot = new MyHardwarePushbot();

    // These variables are used to store the speed at which a given motor runs.

    double  leftPower      = 0;
    double  rightPower     = 0;

    double  liftPower      = .5;

    double  collectPower   = 0;

    double  linearPower    = 0;

    // These variables are used in "toggles" to determine whether a given button is pressed.

    boolean leftPressed    = false;
    boolean rightPressed   = false;

    boolean aPressed       = false;
    boolean bPressed       = false;

    // These variables determine whether half or reverse speed is on.

    boolean halfSpeed      = false;
    int     speedDirection = 1;

    // These variables are used when half speed mode is on as "half" of the original speed that
    // the motor was supposed to run.

    double  leftHalf;
    double  rightHalf;

    // These variables, as explained later, are used only in telemetry.

    String speed;

    double  leftDistance;
    double  rightDistance;

    // "runtime" is also used to display the time in telemetry.

    private ElapsedTime runtime = new ElapsedTime();

    // "init()" runs when the "init" button is pressed.

    @Override
    public void init() {

        // Initialize the robot, which was created above.

        robot.init(hardwareMap);

        // Set the "markerHolder" servo to an upright position so it does not fall down.

        robot.markerHolder.setPosition(1);

        // Display in Telemetry that the robot is, in fact, initialized.

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    // "init_loop()" runs in a loop when the "init" button is pressed; it is not used currently.

    @Override
    public void init_loop() {}

    // "start()" runs when the "start" button is pressed.
    // Reset the timer when the "start" button is pressed.

    @Override
    public void start() {
        runtime.reset();
    }

    // "loop()" runs in a loop when the "start" button is pressed.

    @Override
    public void loop() {

        // IterativeController is divided into 4 parts:
        // 1. TOGGLES
        // Contains the controls for toggling certain boolean variables with a button.
        // 2. POWER
        // Contains math (and some controls) regarding setting the speed of certain motors.
        // 3. MOVEMENT
        // Contains the controls to make the motors actually move.
        // 4. TELEMETRY
        // Contains the code used to display statistics on Telemetry.

        // 1. TOGGLES
        // Every "mode" that is toggled with a button only toggles the boolean the button is tied
        // to when it is initially pressed down; we do this by creating a variable that is true
        // when the button is pressed down, but updates at the same time that the original
        // variable does.

        // Toggle "reverse speed" mode with the A button on the first controller.

        if(gamepad1.a == true && aPressed == false){
            speedDirection = speedDirection * -1;
            aPressed = true;
        }

        if(gamepad1.a == false) {
            aPressed = false;
        }

        // Toggle "half speed" mode with the B button on the first controller.

        if(gamepad1.b == true && bPressed == false){
            halfSpeed = !halfSpeed;
            bPressed = true;
        }

        if(gamepad1.b == false) {
            bPressed = false;
        }

        // POWER

        // Throttle the power of the wheels if the difference
        // between the request and current power is too big.

        if (java.lang.Math.abs(gamepad1.left_stick_y - leftPower) > .1) {
            leftPower = (leftPower + gamepad1.left_stick_y) / 2;
        }
        else {
            leftPower = gamepad1.left_stick_y;
        }

        if (java.lang.Math.abs(gamepad1.right_stick_y - rightPower) > .1) {
            rightPower = (rightPower + gamepad1.right_stick_y) / 2;

        }
        else {
            rightPower = gamepad1.right_stick_y;
        }

        // Changing the speed of the lander lift on the robot works similarly to toggling
        // a variable, with a number incrementing instead of a boolean changing.

        // The speed of the lander lift decreases by 5% when the left "arrow" button on the first
        // controller is pressed, and increases by the same amount when the right "arrow" button
        // is pressed instead.

        // Speed decrement:

        if(gamepad2.dpad_left == true && leftPressed == false) {
            leftPressed = true;
            liftPower -= .05;
        }

        if(true != gamepad2.dpad_left){
            leftPressed = false;
        }

        // Speed increment:

        if(gamepad2.dpad_right == true && rightPressed == false) {
            rightPressed = true;
            liftPower += .05;
        }

        if(true != gamepad2.dpad_right){
            rightPressed = false;
        }

        // Check if the lift speed is between 0 and 1, and set the power within those bounds.

        liftPower = Range.clip(liftPower, -1.0, 1.0);

        // Set the speed of the motor controlling the angle of the (mineral) linear lift
        // to the "y" coordinate of the right stick on the 2nd controller.

        collectPower = gamepad2.left_stick_y * -1;

        // It takes much less power to move the linear lift upwards than downwards; we clip the
        // speed downwards at a quarter to prevent the lift from "slamming" downwards.

        collectPower = Range.clip(collectPower, -.25, 1.0);

        // Set linearPower to the y coordinate of the right stick on the 2nd controller.

        linearPower = gamepad2.right_stick_y * .5;

        // MOVEMENT
        // If half speed mode or reverse mode, set the power of the motors to half of the speed
        // or the reverse of the speed, respectively.

        // Half speed mode and reverse mode do not work together; half speed takes priority.

        if(halfSpeed == true){
            leftHalf  = leftPower  / 2;
            rightHalf = rightPower / 2;
            robot.leftDrive.setPower(leftHalf);
            robot.rightDrive.setPower(rightHalf);
        }
        else{
            robot.leftDrive.setPower(leftPower * speedDirection);
            robot.rightDrive.setPower(rightPower * speedDirection);
        }

        // If the up "arrow" button on the first controller, move the lander lift up at the
        // predefined speed; if the down "arrow" button is pressed, move the lift downwards.
        // Otherwise (including having both buttons pressed), do not move the lift.

        if(gamepad2.dpad_up == true) {
            robot.landerLift.setPower(liftPower);
        }
        else if (gamepad2.dpad_down == true) {
            robot.landerLift.setPower(-liftPower);
        }

        if (gamepad2.dpad_up != true && gamepad2.dpad_down != true){
            robot.landerLift.setPower(0);
        }

        robot.linearTurn.setPower(collectPower);

        // left and rightLinear run in opposite directions since they wind and unwind a string.

        robot.leftLinear.setPower(linearPower);
        robot.rightLinear.setPower(-linearPower);

        // TELEMETRY

        // The variable "speed" is strictly used for Telemetry.

        if(halfSpeed == true){
            speed = "Half";
        }
        else{
            speed = "Full";
        }

        // Show the elapsed game time, wheel power, wheel distance, and motor mode.
        leftDistance  = robot.leftDrive.getCurrentPosition();
        rightDistance = robot.rightDrive.getCurrentPosition();

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motor Speeds:",
                "Left: (%.2f), Right: (%.2f), Lift: (%.2f)",
                leftPower, rightPower, liftPower);

        telemetry.update();
    }

    // "stop()" runs when the "stop" button is pressed; it is not used currently.

    @Override
    public void stop() {
    }
}