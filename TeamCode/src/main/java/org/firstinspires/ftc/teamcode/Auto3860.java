package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Auto3860", group="Linear OpMode")
//@Disabled
public class Auto3860 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor Left;
    DcMotor Right;
    DcMotor Arm;
    DcMotor Servo;

    double power = 1.0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Servo = hardwareMap.dcMotor.get("Servo");
        Arm   = hardwareMap.dcMotor.get("Arm");
        Left  = hardwareMap.dcMotor.get("Left");
        Right = hardwareMap.dcMotor.get("Right");

        Arm.setDirection(DcMotor.Direction.FORWARD);
        Left.setDirection(DcMotor.Direction.FORWARD);
        Right.setDirection(DcMotor.Direction.REVERSE);
        Servo.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("mode", "waiting");
        telemetry.update();

        waitForStart();
        runtime.reset();

        sleep(500);

        Arm.setPower(-1.0);
        sleep(750);

        Arm.setPower(-0.4);
        Servo.setPower(-0.5);
        sleep(200);

        Servo.setPower(0.0);
        Arm.setPower(0.0);
        sleep(5000);
        Left.setPower(power);
        Right.setPower(power);
        sleep(1250);

        Left.setPower(0.0);
        Right.setPower(0.0);
        sleep(1000);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Status", "Running");
        telemetry.update();

        idle();
    }
}