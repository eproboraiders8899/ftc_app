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

@TeleOp(name="Crater Start- No Detach", group="Autonomous")
// @Disabled
public class CraterNoDetach extends AutonomousStart {

    public void runOpMode() {

        initialize();


        // Runs when the gold mineral is in the center.
        encoderDrive(-1, 35, 35, 3);

        /*
        // Runs when the gold mineral is on the right.
        turn(.5, 1);
        encoderDrive(.5, 38, 38, 3);
        */

        /*
        // Runs when the gold mineral is on the left.
        turn(-.5, 1);
        encoderDrive(.5, 35, 35, 3);
        */

        robot.mineralCollector.setPosition(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();

    }
}
