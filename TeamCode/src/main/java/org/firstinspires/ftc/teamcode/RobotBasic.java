/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="RobotBasic", group="Linear OpMode")
//@Disabled
public class RobotBasic extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    com.qualcomm.robotcore.hardware.Servo Servo;

    DcMotor Arm_Drive;
    DcMotor Right_Drive;
    DcMotor Left_Drive;
    double ServoPosition = 0.0;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Arm_Drive = hardwareMap.get(DcMotor.class, "Arm");
        Right_Drive = hardwareMap.get(DcMotor.class, "Right_Drive");
        Left_Drive = hardwareMap.get(DcMotor.class, "Left_Drive");
        Servo = hardwareMap.servo.get("servo");

        Servo.setPosition(ServoPosition);

        double tgtPower;
        double DrivePower;
        double Turning;

        Arm_Drive.setDirection(DcMotor.Direction.FORWARD);
        Right_Drive.setDirection(DcMotor.Direction.FORWARD);
        Left_Drive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("mode", "waiting");
        telemetry.update();

        waitForStart();
        runtime.reset();

        ServoPosition = 0.5;
        Servo.setPosition(ServoPosition);
        sleep(2000);

        ServoPosition = 1.0;
        Servo.setPosition(ServoPosition);

        while (opModeIsActive()) {
            tgtPower = -this.gamepad1.right_stick_y;
            DrivePower = -this.gamepad1.left_stick_y;
            Turning = -this.gamepad1.left_stick_x;

            if((Turning > -0.5)||(Turning < +0.5)) {
                Arm_Drive.setPower(tgtPower);
                Right_Drive.setPower(DrivePower);
                Left_Drive.setPower(DrivePower);
            }
            else if(Turning > 0.5) {
                Right_Drive.setPower(-0.5);
                Left_Drive.setPower(+0.5);
            }
            else if(Turning < -0.5){
                Right_Drive.setPower(+0.5);
                Left_Drive.setPower(-0.5);
            }

            if(gamepad1.y) {
                Servo.setPosition(0.0);
            }
            else if (gamepad1.x) {
                Servo.setPosition(0.5);
            }
            else if (gamepad1.b) {
                Servo.setPosition(1.0);
            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Servo Position", Servo.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();
            idle();
        }

        Arm_Drive.setPower(0);
        Right_Drive.setPower(0);
        Left_Drive.setPower(0);
        Servo.setPosition(1);
    }
}