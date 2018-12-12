package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@TeleOp(name="Camera Flash Test", group="Iterative Opmode")
// @Disabled

public class CameraFlashTest extends OpMode {

    boolean aPressed  = false;
    boolean flashMode = false;

    private static final String VUFORIA_KEY = "AdTtd8D/////AAABmbAte/IPjExpuQ7N9ZZbhFUoIphG3O/GRS7D1JumDL5+8Z1ZqThpEqeUaCeFe5EsNKhwqIe4/AcxbyQ7Ya3JlG4qa7nSDD5G3Tp02egg6kzczLcCN14PwFKWNi/r1/TPjy8kvfjA5Ve9fSzOt7ZFb5eHDZ2bS47jJpDb0uHJl1UiXWYMGMtbSF3BUXTKCxGOu7D83FZQABJ0f8udnmc94zU+pQxWYfLQiRb7BDoax/hyzEXrPt3/uri4AL0F2O5TyE2SlIZBZPs6K2OcXL2GtsOwiT/jQDr7msaJoH7YqGi+uT+VZSTAvURf4SJ0TGc6Sk5513jsr6bLZCk1KPE1aYB2Hi9H0OXHnSju0r6V7M/1";
    private VuforiaLocalizer vuforia;

    @Override public void init()      {
        // Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    @Override public void init_loop() {}
    @Override public void start()     {}

    @Override
    public void loop() {

        if(gamepad1.a == true && aPressed == false) {

            flashMode = !flashMode;
            aPressed  = true;
        }

        if(gamepad1.a == false) {

            aPressed = false;
        }

        CameraDevice.getInstance().setFlashTorchMode(flashMode);
    }

    @Override public void stop() {}
}
