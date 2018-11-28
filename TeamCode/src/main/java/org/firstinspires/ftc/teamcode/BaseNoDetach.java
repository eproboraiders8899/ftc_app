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


@TeleOp(name="Base Start- No Detach", group="Autonomous")
// @Disabled
public class BaseNoDetach extends AutonomousStart {

    public void runOpMode() {

        initialize();

        /*
        // Runs when the gold mineral is in the center.
        encoderDrive(.5, 56, 56, 3);
        */

        /*
        // Runs when the gold mineral is on the right.
        encoderDrive(.5, 27, 27, 3);
        turn(-.5, 1);
        encoderDrive(.5, 36, 36, 3);
        */

        /*
        // Runs when the gold mineral is on the left.
        encoderDrive(.5, 27, 27, 3);
        turn(.5, 1);
        encoderDrive(.5, 36, 36, 3);
        */

        depositMarker();

        telemetry.addData("Path", "Complete");
        telemetry.update();

    }
}
