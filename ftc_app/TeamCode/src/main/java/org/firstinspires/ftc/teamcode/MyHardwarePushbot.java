/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import java.util.Locale;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

// MyHardwarePushbot is a variation of HardwarePushbot, with a different combination of motors
// and servos tailored to our specific use.

public class MyHardwarePushbot
{
    // The function of each motor will be labeled:

    // "leftDrive" is the motor controlling the wheels on the left side of our robot.

    public DcMotor        leftDrive     = null;

    // "rightDrive" is the motor controlling the wheels on the right side of our robot.

    public DcMotor        rightDrive    = null;

    // landerLift is the motor controlling the lift on our robot used
    // to hook onto (and off of) the lander in the center of the playing field.

    public DcMotor        landerLift    = null;

    // markerHolder is the servo that holds our team's marker; it moves downwards when our robot
    // is in the base and ready to deposit said marker.

    public Servo          markerHolder  = null;

    // limitButton is the digital sensor that detects when the robot has touched the ground
    // after dismounting fom the lander; the button is pressed when this happens.

    public DcMotor        linearRotator = null;

    public Servo          boxRotator    = null;

    public CRServo        linearLift    = null;

    public CRServo        boxLeft      = null;

    public CRServo        boxRight       = null;

    public DigitalChannel limitButton   = null;

    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hwMap           = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public MyHardwarePushbot() {}

    /* Initialize standard Hardware interfaces */

    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map

        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive     = hwMap.get(DcMotor.class, "left_drive");
        rightDrive    = hwMap.get(DcMotor.class, "right_drive");
        landerLift    = hwMap.get(DcMotor.class, "lander_lift");
        linearRotator = hwMap.get(DcMotor.class, "linear_rotator");

        limitButton = hwMap.get(DigitalChannel.class, "limit_button");
        limitButton.setMode(DigitalChannel.Mode.INPUT);

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power

        leftDrive.setPower(0);
        rightDrive.setPower(0);
        landerLift.setPower(0);
        linearRotator.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.

        markerHolder  = hwMap.get(Servo.class, "marker_holder");
        boxRotator = hwMap.get(Servo.class, "box_rotator");
        linearLift = hwMap.get(CRServo.class, "linear_lift");
        boxLeft = hwMap.get(CRServo.class, "box_left");
        boxRight = hwMap.get(CRServo.class, "box_right");

    }
 }