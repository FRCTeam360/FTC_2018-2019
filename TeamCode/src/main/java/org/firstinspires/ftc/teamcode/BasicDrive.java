package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;



@TeleOp(name = "3860 Main Drive Program", group = "Iterative Opmode")



    public class BasicDrive extends OpMode {


        private DcMotor rightDrive;
        private DcMotor leftDrive;

        @Override
        public void init() {
            telemetry.addData("Status", "Initialized");

            leftDrive = hardwareMap.dcMotor.get("left");
            rightDrive = hardwareMap.dcMotor.get("right");

            leftDrive.setDirection(DcMotor.Direction.FORWARD);
            rightDrive.setDirection(DcMotor.Direction.REVERSE);
        }

        @Override
        public void loop() {
            double leftPower;
            double rightPower;

            leftPower = -gamepad1.left_stick_y;
            rightPower = -gamepad1.right_stick_y;

            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

        }

        @Override
        public void stop() {
        }

    }