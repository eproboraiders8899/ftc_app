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
public class CraterStart extends AutonomousStart {

    public void runOpMode() {
        initialize();

        dismount();

        encoderDrive(DRIVE_SPEED, 2, -2, 3.0);

        flashSet(false);

        if (seeingGold() == true) {

            flashSet(false);

            encoderDrive(DRIVE_SPEED, -2, 2, 3.0);

            encoderDrive(-1, 35, 35, 3);

        }
        else {

            encoderDrive(DRIVE_SPEED, 8, -8, 3.0);

            if (seeingGold() == true) {

                encoderDrive(DRIVE_SPEED, -2, 2, 3.0);

                encoderDrive(-1, 38, 38, 3);
            }
            else{

                encoderDrive(DRIVE_SPEED, -7.5, 7.5, 3.0);
                encoderDrive(-1, 30, 30, 3);
            }
        }
    }
}




/*
        telemetry.addData("Path","Complete");
        telemetry.update();

        }

}
        if(seeingGold() == true) {

        flashSet(false);

        encoderDrive(-1, 27, 27, 3);

        }
        else{

        flashSet(false);

        encoderDrive(DRIVE_SPEED,  3,  -3, 3.0);

        encoderDrive(-1, 35, 35, 3);
*/