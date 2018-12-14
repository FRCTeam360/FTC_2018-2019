package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="TeleOp3860", group="Linear OpMode")
//@Disabled
public class TeleOp3860 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    DcMotor Arm;
    DcMotor Servo;
    DcMotor Right;
    DcMotor Left;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Arm = hardwareMap.get(DcMotor.class, "Arm");
        Servo = hardwareMap.get(DcMotor.class, "Servo");
        Right = hardwareMap.get(DcMotor.class,"Right");
        Left = hardwareMap.get(DcMotor.class,"Left");

        Arm.setDirection(DcMotor.Direction.FORWARD);
        Servo.setDirection(DcMotor.Direction.FORWARD);
        Right.setDirection(DcMotor.Direction.REVERSE);
        Left.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("mode", "waiting");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            Left.setPower(-gamepad1.left_stick_y);
            Right.setPower(-gamepad1.right_stick_y);

            if(gamepad1.dpad_up)
            {
                Arm.setPower(1.0);
            }
            else if(gamepad1.dpad_down)
            {
                Arm.setPower(-1.0);
            }
            else
            {
                Arm.setPower(0.0);
            }

            if(gamepad1.y)
            {
                Servo.setPower(-1.0);
            }
            else if(gamepad1.b)
            {
                Servo.setPower(1.0);
            }
            else {
                Servo.setPower(0.0);
            }


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Status", "Running");
            telemetry.update();

            idle();
        }
        Arm.setPower( 0.0 );
        Servo.setPower( 0.0 );
    }
}