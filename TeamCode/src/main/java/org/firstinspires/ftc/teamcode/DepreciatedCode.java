package org.firstinspires.ftc.teamcode;

// THIS CLASS DOES NOT DO ANYTHING; IT SIMPLY CONTAINS ALL OF THE CODE WE DON'T USE FOR
// ARCHIVING PURPOSES.

public class DepreciatedCode {

    // telemetry.addData("Status", "Run Time: " + runtime.toString());

    // static final double COUNTS_PER_MOTOR_REV  = 1440;
    // static final double DRIVE_GEAR_REDUCTION  = 2.0;
    // static final double WHEEL_DIAMETER_INCHES = 4.0;
    // static final double COUNTS_PER_INCH       = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES);

    // double liftPower = 0;


    /*

    public int getGoldPosition() {
        int goldLocation = GOLD_CENTER;

        if (tfod != null) {

            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

            if (updatedRecognitions != null) {

                telemetry.addData("# Object Detected", updatedRecognitions.size());
                telemetry.update();

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
                        goldLocation = GOLD_LEFT;
                    }
                    else if (silverMineralX > goldMineralX) {
                        goldLocation = GOLD_CENTER;
                    }
                    else {
                        goldLocation = GOLD_RIGHT;
                    }
                }


            }
        }
        else {

            telemetry.addData("Tensor Flow Failed", "Defaulting to trying center");
        }
        return goldLocation;
    }
    */

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

    // Lower the lift that holds the robot to the lander.

       /* if (robot.limitButton.getState() == true) {
            telemetry.addData("Digital Touch", "Is Not Pressed");
            robot.landerLift.setPower(DRIVE_SPEED);
        } else {
            telemetry.addData("Digital Touch", "Is Pressed");
            robot.landerLift.setPower(0);
            robot.limitServo.setPosition(0);

        }
  */

    //turn(-.5, 1.2);

    // Drive out of the lander zone, but not so far as to disturb the minerals on the field.

    // drive(-1, 360);
    /*

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

            // static final int GOLD_CENTER = 0;
    // static final int GOLD_RIGHT = 1;
    // static final int GOLD_LEFT = 2;
        }
    }
    */
    /*

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
    */

    // String goldPosition = null;

    /*

    public void initializeNoDetection() {

        robot.init(hardwareMap);

        robot.markerHolder.setPosition(1);

        telemetry.addData(">", "Robot Ready.");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
    }
    */

    // static final double TURN_SPEED               = 0.5;

    //robot.liftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    //robot.liftDrive.setPower(0);
    // robot.liftDrive.getCurrentPosition());
    //robot.liftDrive.setPower(Math.abs(speed));
    // newLiftTarget = robot.liftDrive.getCurrentPosition() + (int) (liftInches * COUNTS_PER_INCH);

    //  New code to run the mineral collector

    // if (java.lang.Math.abs(gamepad2.right_stick_y - raisePower) > .1) {
    //       raisePower = (raisePower + gamepad2.right_stick_y) / 2;
    //   }
    //   else {
    //      leftPower = gamepad1.left_stick_y;
    //  }

    //int newLiftTarget;
    //robot.liftDrive.setTargetPosition(newLiftTarget);
    // robot.liftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);






    // robot.mineralCollector.setPosition((gamepad2.right_stick_y / 2) + .5);

    // robot.mineralClaw.setPosition((gamepad2.left_stick_x / 2) + .5);

        /*
        robot.mineralCollector.setPosition((gamepad2.left_stick_y / 2) + .5);

        linearSpeed = (gamepad2.right_stick_y / 2) + .5;

        robot.linearLift.setPosition(linearSpeed);
        robot.rightLinear.setPosition(1 - linearSpeed);

        if(gamepad2.left_bumper == true) {
            robot.mineralClaw.setPosition(1);
        }
        else {
            robot.mineralClaw.setPosition(0);
        }

        if(gamepad2.a == true && collectorButton == false){
            collectorToggle = !collectorToggle;
            collectorButton = true;
        }

        if(gamepad2.a == false) {
            collectorButton = false;
        }
        */

    // Toggle which part of the mineral collector is being controlled based on the right trigger button of the second controller.

        /*
        if(gamepad2.right_stick_button == true && mineralButton == false){
            mineralLift = !mineralLift;
            mineralButton = true;
        }

        if(gamepad2.right_stick_button == false) {
            mineralButton = false;
        }

        if(mineralLift == true) {
            robot.mineralCollector.setPosition((gamepad2.right_stick_y / 2) + .5);
        }
        else {
            robot.mineralClaw.setPosition((gamepad2.right_stick_y / 2) + .5);
        }

        // Toggle which part of the linear lift is being controlled based on the left trigger button of the second controller.

        if(gamepad2.left_stick_button == true && linearButton == false){
            linearTurn = !linearTurn;
            linearButton = true;
        }

        if(gamepad2.left_stick_button == false) {
            mineralButton = false;
        }

        if(linearTurn == true) {
            robot.linearTurn.setPower(gamepad2.left_stick_y);
            robot.linearWind.setPower(gamepad2.left_stick_y);
        }
        else {
            robot.linearTurn.setPower(0);
            robot.linearWind.setPower(0);
            robot.linearLift.setPosition((gamepad2.left_stick_y / 2) + .5);
            robot.linearLift.setPosition(-(gamepad2.left_stick_y / 2) + .5);
        }

        if(collectorToggle == true) {
            robot.mineralCollector.setPosition(45);
        }
        else {
            robot.mineralCollector.setPosition(0);
        }

        if (robot.limitButton.getState() == false) {
        telemetry.addData("Digital Touch", "Is Pressed");
        }
        else if (robot.limitButton.getState() == true) {
            telemetry.addData("Digital Touch", "Isn't Pressed");
        }
        */

    // if (java.lang.Math.abs(gamepad2.left_stick_y - collectPower) > .1) {
    //      linearTurn = (collectPower + gamepad2.left_stick_y) / 2;
    //  }
    // else {
    // }

    // - This requires no math, but it is hard to drive forward slowly and keep straight.
    // Throttle the power of the wheels if the difference between the request and current power is too big.
    // Toggle "tank" driving mode with the A button.

        /*
        if(gamepad1.a == true && aPressed == false){
            tank = !tank;
            aPressed = true;
        }

        if(gamepad1.a == false) {
            aPressed = false;
        }
        */

    // robot.linearLift.setPosition(.5);
    // robot.rightLinear.setPosition(.5);

    // robot.mineralClaw.setPosition(.5);
    // robot.mineralCollector.setPosition(.5);

    //robot.linearWind.setZeroPowerBehavior(BRAKE);
    // double  lowerPower         = 0;
    // double  raisePower          = 0;
    // double  linearTurn         = 0;

    // boolean collectorToggle = false;
    // boolean collectorButton = false;

    // boolean tank            = true;
/*
    boolean mineralLift        = false;
    boolean mineralButton      = false;       // runtime.reset();

       // while (runtime.seconds() < duration) {}//public DcMotor linearWind       = null;
       //   // public DcMotor linearPull       = null;// public Servo   mineralCollector = null;
       //    // public Servo   mineralClaw      = null;
       //    // public Servo   linearLift       = null;
       //    // public Servo   rightLinear      = null;// linearPull = hwMap.get(DcMotor.class, "linear_Pull");
       //       // linearWind = hwMap.get(DcMotor.class, "linear_wind"); //linearPull.setPower(0);
       //       // linearWind.setPower(0);// mineralCollector = hwMap.get(Servo.class, "mineral_collector");
       //        // mineralClaw = hwMap.get(Servo.class, "mineral_claw");
       //        // linearLift = hwMap.get(Servo.class, "linear_lift");
       //        // rightLinear = hwMap.get(Servo.class, "right_linear");//linearWind.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       //       // linearPull.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    // boolean linearTurn         = false;
    boolean linearButton       = false;

    String  trueMode;
    */

//  robot.linearWind.setPower(gamepad2.left_stick_y * -.7);

        /*
        if(tank == true){
            trueMode = "Tank";
        }
        else{
            trueMode = "POV";
        }
        */

        /*
        telemetry.addData("Toggles:", "Mode: " + trueMode);
        telemetry.addData("Toggles:", "Speed: " + speed);
        */

    // /*
    //    public ColorSensor colorSensor = null;
    //    public DistanceSensor distanceSensor = null;
    //    *//*
    //        //        colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
    //        //        distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
    //        //        */double  linearSpeed;
}
