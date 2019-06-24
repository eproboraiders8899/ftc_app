package org.firstinspires.ftc.teamcode;

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

@Autonomous(name = "BaseStart", group = "Autonomous")
// @Disabled

// BaseStart is our Autonomous program for when our robot starts on the side of the lander
// closest to one of the team depots on the field.

// Since the field is rotationally symmetrical, we only need one of each Autonomous program.

public class BaseStart extends AutonomousStart {

    public void runOpMode() {

        // For more information regarding the methods used in BaseStart, consult the class
        // AutonomousStart.

        // Initialise the robot and object detection.

        initialize();

        // Dismount from the lander.

        dismount();

        // Turn on the flash to have more consistent lighting during play.

        // It is more safe to do so by the base because of an absence of a crater and the minerals
        // within possibly interfering with object detection.

        flashSet(true);

        // Is the rightmost mineral gold?

        // If the leftmost mineral is gold:

        if(seeingGold() == true) {

            // Turn off the flash to avoid interfering with other robots.

            flashSet(false);

            // Turn clockwise to align the robot back with the rightmost mineral to push it out
            // of position.

            // Turns like these are only necessary because the phone's camera is not in the center
            // of the robot.

            encoderDrive(DRIVE_SPEED,  -2,  2, 3.0);

            // Drive forward.

            encoderDrive(1, 40, 40, 3);

            // Turn counterclockwise to align the robot (and mineral) with the base.

            encoderDrive(.75, 10, -10, 3);

            // Drive forward into the base.

            encoderDrive(.75, 35, 35, 3);
        }

        // If the leftmost mineral is not gold:

        else {

            // Turn counterclockwise to align the camera with the middlemost mineral.

            encoderDrive(DRIVE_SPEED,  5,  -5, 3.0);

            // Is the middlemost mineral gold?

            // If the middlemost mineral is gold:

            if(seeingGold() == true) {

                // Turn off the flash to avoid interfering with other robots.

                flashSet(false);

                // Turn clockwise to align the robot back with the middlemost
                // mineral to push it out of position.

                encoderDrive(DRIVE_SPEED,  -1,  1, 3.0);

                // Drive forward into the base.

                encoderDrive(.75, 80, 80, 3);

                encoderDrive(DRIVE_SPEED,  8,  -8, 3.0);
            }

            // If the middlemost mineral is not gold:

            else{

                // Turn off the flash to avoid interfering with other robots.

                flashSet(false);

                // Turn counterclockwise to align the robot with the leftmost
                // mineral to push it out of position.

                encoderDrive(DRIVE_SPEED,  3,  -3, 3.0);

                // Drive forward.

                encoderDrive(1, 35, 35, 3);

                // Turn clockwise to align the robot (and mineral) with the base.

                encoderDrive(.75, -10, 10, 3);

                // Drive forward into the base.

                encoderDrive(.75, 38, 38, 3);
            }
        }

        // Deposit the team marker into the base.

        depositMarker();

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
