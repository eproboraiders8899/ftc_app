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

@Autonomous(name="Crater Start- No Detach", group="Autonomous")
@Disabled
public class CraterNoDetach extends AutonomousStart {

    public void runOpMode() {

        initialize();


        if(seeingGold() == true) {
            encoderDrive(-1, 35, 35, 3);

            encoderDrive(-1, 35, 35, 3);
        }
        else {
            encoderDrive(DRIVE_SPEED,  -4,  4, 5.0);  // S1: turn to scan right mineral with 5 Sec timeout

            if(seeingGold() == true) {

                encoderDrive(-1, 35, 35, 3);


            }

            else{

                encoderDrive(DRIVE_SPEED,  8,  -8, 5.0);  // S1: turn to scan left mineral with 5 Sec timeout
                encoderDrive(-1, 35, 35, 3); //run to push mineral and park on crater
            }
        }

        CameraDevice.getInstance().setFlashTorchMode(false);

        telemetry.addData("Path", "Complete");
        telemetry.update();

    }
}
