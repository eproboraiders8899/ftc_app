package org.firstinspires.ftc.teamcode;

import java.lang.Math;

import java.lang.Math;

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

@Autonomous(name = "CraterStart", group = "Autonomous")
// @Disabled

// CraterStart is our Autonomous program for when our robot starts on the side of the lander
// closest to one of the craters on the field.

// Since the field is rotationally symmetrical, we only need one of each Autonomous program.

public class CraterStart extends AutonomousStart {

    public void runOpMode() {

        // For more information regarding the methods used in BaseStart, consult the class
        // AutonomousStart.

        // Initialise the robot and object detection.

        initialize();

        // Dismount from the lander.

        dismount();

        // Turn counterclockwise to better align the camera with the rightmost mineral on
        // the field.

        // This is safe due to the fact that we drove forward slightly during dismount.

        encoderDrive(DRIVE_SPEED, 2, -2, 3.0);

        // To avoid detecting the silver playing field as a silver mineral and reduce the
        // chances of detecting minerals in the crater, DO NOT turn on the flash.

        flashSet(false);

        // Is the rightmost mineral gold?

        // If the rightmost mineral is gold:

        if (seeingGold() == true) {

            // Turn clockwise to align the robot back with the rightmost mineral to push it out
            // of position.

            // Turns like these are only necessary because the phone's camera is not in the center
            // of the robot.

            encoderDrive(DRIVE_SPEED, -2, 2, 3.0);

            // Drive forward and park on the edge of the crater.

            encoderDrive(-1, 35, 35, 3);

        }

        // If the rightmost mineral is not gold:

        else {

            // Turn counterclockwise to align the phone's camera with the leftmost mineral.

            // We scan the two outermost minerals to avoid detecting minerals in the crater.

            encoderDrive(DRIVE_SPEED, 8, -8, 3.0);

            // Is the leftmost mineral gold?

            // If the leftmost mineral is gold:

            if (seeingGold() == true) {

                // Turn clockwise to align the robot back with the leftmost mineral to push it out
                // of position.

                encoderDrive(DRIVE_SPEED, -2, 2, 3.0);

                // Drive forward and park on the edge of the crater.

                encoderDrive(-1, 38, 38, 3);
            }

            // If the leftmost mineral is not gold:

            else{

                // Turn clockwise to align the robot with the middlemost
                // mineral to push it out of position.

                encoderDrive(DRIVE_SPEED, -7.5, 7.5, 3.0);

                // Drive forward and park on the edge of the crater.

                encoderDrive(-1, 30, 30, 3);
            }
        }
    }
}
