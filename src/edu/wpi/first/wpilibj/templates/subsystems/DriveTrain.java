/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoysticks;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.commands.DriveWithXBox;
/**
 *
 * @author Administrator
 */
public class DriveTrain extends PIDSubsystem {
    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;

    SpeedController leftFrontMotor;
    SpeedController leftRearMotor;
    SpeedController rightFrontMotor;
    SpeedController rightRearMotor;

    RobotDrive drive;

    double leftJoyY = 0.0;
    double rightJoyY = 0.0;
    double xboxLeft = 0.0;
    double xboxRight = 0.0;

    // Initialize your subsystem here
    public DriveTrain() {
        super("DriveTrain", Kp, Ki, Kd);

        leftFrontMotor = new Victor(RobotMap.frontLeftMotor);
        rightFrontMotor = new Victor(RobotMap.frontRightMotor);
        leftRearMotor = new Victor(RobotMap.rearLeftMotor);
        rightRearMotor = new Victor(RobotMap.rearRightMotor);
//        drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);
        drive = new RobotDrive(leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor);
        drive.setSafetyEnabled(false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithXBox());
    }

    public void updateStatus() {
        SmartDashboard.putDouble("leftFrontDriveMotor: ", leftFrontMotor.get());
        SmartDashboard.putDouble("rightFrontDriveMotor: ", rightFrontMotor.get());
        SmartDashboard.putDouble("leftRearDriveMotor: ", leftRearMotor.get());
        SmartDashboard.putDouble("rightRearDriveMotor: ", rightRearMotor.get());
        SmartDashboard.putDouble("rightJoyY: ", rightJoyY);
        SmartDashboard.putDouble("leftJoyY: ", -leftJoyY);

        if(this.getPIDController() != null)
        {
            SmartDashboard.putDouble("P: ", this.getPIDController().getP());
            SmartDashboard.putDouble("I: ", this.getPIDController().getI());
            SmartDashboard.putDouble("D: ", this.getPIDController().getD());
            SmartDashboard.putDouble("PID Error: ", this.getPIDController().getError());
        }
     }
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }

    public void driveWithJoysticks(Joystick leftStick, Joystick rightStick) {
        rightJoyY = rightStick.getY();
        leftJoyY = leftStick.getY();

        rightJoyY = RobotMap.stickDeadBand.Deaden(rightJoyY);
        leftJoyY = RobotMap.stickDeadBand.Deaden(leftJoyY);

        drive.tankDrive(-leftJoyY, rightJoyY);
    }
    
    public void driveWithXBox(Joystick xBox) {
    
     xboxLeft = xBox.getRawAxis(2);
     xboxRight = xBox.getRawAxis(5);
     

     drive.tankDrive(-xboxLeft, xboxRight);
    }
    
}