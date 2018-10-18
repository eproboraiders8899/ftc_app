package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Double.toString;

@TeleOp(name="iterativeController", group="Iterative Opmode")

public class iterativeController extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    MyHardwarePushbot robot = new MyHardwarePushbot();

    double leftPower = 0;
    double rightPower = 0;
    double liftPower = .5;
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean aPressed = false;
    boolean bPressed = false;
    boolean tank = true;
    boolean halfSpeed = false;
    String trueMode;
    String speed;
    double leftDistance;
    double rightDistance;
    double leftHalf;
    double rightHalf;

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

        // - This requires no math, but it is hard to drive forward slowly and keep straight.

        // Throttle the power of the wheels if the difference between the request and current power is too big.

        // Toggle "tank" driving mode with the A button.

        if(gamepad1.a == true && aPressed == false){
            tank = !tank;
            aPressed = true;
        }

        if(gamepad1.a == false) {
            aPressed = false;
        }

        // Toggle "half speed" mode with the B button.

        if(gamepad1.b == true && bPressed == false){
            halfSpeed = !halfSpeed;
            bPressed = true;
        }

        if(gamepad1.b == false) {
            bPressed = false;
        }

        if(tank == true){

            // "Tank" driving mode

            // Throttle the power of the wheels if the difference between the request and current power is too big.

            if (java.lang.Math.abs(gamepad1.left_stick_y - leftPower) > .1) {
                leftPower = (leftPower + gamepad1.left_stick_y) / 2;
            }
            else {
                leftPower  = gamepad1.left_stick_y;
            }

            if (java.lang.Math.abs(gamepad1.right_stick_y - rightPower) > .1) {
                rightPower = (rightPower + gamepad1.right_stick_y) / 2;

            }
            else {
                rightPower  = gamepad1.right_stick_y;
            }

        }
        else {

            // "POV" driving mode

            leftPower = -(Range.clip(-gamepad1.left_stick_y + gamepad1.right_stick_x, -1.0, 1.0));
            rightPower = -(Range.clip(-gamepad1.left_stick_y - gamepad1.right_stick_x, -1.0, 1.0));
        }

        // If half speed mode is on, divide the speed the motor is set to by 2.
        if(halfSpeed == true){
            leftHalf = leftPower / 2;
            rightHalf = rightPower / 2;
            robot.leftDrive.setPower(leftHalf);
            robot.rightDrive.setPower(rightHalf);
        }
        else{
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);
        }



        // When a button on the dpad is pressed ONCE, increment the speed of the lift a small amount.

        if(gamepad2.dpad_left == true && leftPressed == false) {
            leftPressed = true;
            liftPower -= .05;
        }

        if(true != gamepad2.dpad_left){
            leftPressed = false;
        }

        if(gamepad2.dpad_right == true && rightPressed == false) {
            rightPressed = true;
            liftPower += .05;
        }

        if(true != gamepad2.dpad_right){
            rightPressed = false;
        }

        // Check if the lift speed is <= 1 and >= 0.

        liftPower = Range.clip(liftPower, 0, 1.0);

        // Run the lift based on the designated speed.


        if(gamepad2.dpad_up == true) {
            robot.leftArm.setPower(liftPower);
        }
        else if (gamepad2.dpad_down == true) {
            robot.leftArm.setPower(-liftPower);
        }

        if (gamepad2.dpad_up != true && gamepad2.dpad_down != true){
            robot.leftArm.setPower(0);
        }

        if(tank == true){
            trueMode = "Tank";
        }
        else{
            trueMode = "POV";
        }
        if(halfSpeed == true){
            speed = "Half";
        }
        else{
            speed = "Full";
        }

        // Show the elapsed game time, wheel power, wheel distance, and motor mode.
        leftDistance = robot.leftDrive.getCurrentPosition();
        rightDistance = robot.rightDrive.getCurrentPosition();

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motor Speeds:", "Left: (%.2f), Right: (%.2f), Lift: (%.2f)", leftPower, rightPower, liftPower);
        telemetry.addData("Motor Distances:", "Left: (%.2f) Right: (%.2f)", leftDistance, rightDistance);
        telemetry.addData("Toggles:", "Mode: " + trueMode);
        telemetry.addData("Toggles:", "Speed: " + speed);

        telemetry.update();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}
