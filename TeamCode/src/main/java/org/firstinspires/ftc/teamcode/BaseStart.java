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
public class BaseStart extends AutonomousStart {

    public void runOpMode() {

        initialize();

        dismount();

        flashSet(true);

        encoderDrive(DRIVE_SPEED,  4,  -4, 3.0);

        encoderDrive(DRIVE_SPEED,  -3,  3, 3.0);

        encoderDrive(.75, 80, 80, 3);

        /*

        if(seeingGold() == true) {

            flashSet(false);

            encoderDrive(DRIVE_SPEED,  -2,  2, 3.0);

            encoderDrive(1, 35, 35, 3);

            encoderDrive(.75, 9, -9, 3);

            encoderDrive(.75, 35, 35, 3);
        }
        else {

            encoderDrive(DRIVE_SPEED,  4,  -4, 3.0);

            if(seeingGold() == true) {

                encoderDrive(DRIVE_SPEED,  -1,  1, 3.0);

                encoderDrive(.75, 80, 80, 3);

                flashSet(false);



            }
            else{

                encoderDrive(DRIVE_SPEED,  4,  -4, 3.0);

                flashSet(false);

                //encoderDrive(1, 35, 35, 3);
                //encoderDrive(.75, 7, -7, 3);
                //encoderDrive(.75, 35, 35, 3);
            }
        }
        */


       // encoderDrive(TURN_SPEED,   12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
       // encoderDrive(DRIVE_SPEED, -24, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout

        depositMarker();

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
