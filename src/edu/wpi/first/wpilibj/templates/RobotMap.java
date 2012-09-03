package edu.wpi.first.wpilibj.templates;

import Team102Lib.Deadband;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    public static final int frontLeftMotor = 1;
    public static final int frontRightMotor = 2;
    public static final int rearLeftMotor = 3;
    public static final int rearRightMotor = 4;
    
    public static final int winchMotor = 6;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;

    public static final double joystickRange = 1.0d; // the range of the joystick around 0.0
    public static final double flatDeadband = 0.02d;        // The amount of flat space in the deadband (around 0.0)
    public static Deadband stickDeadBand = null;    // Used to create a smooth deadband for the stick.

    // Joysticks
    public static final int leftStickPort = 1;
    public static final int rightStickPort = 2;
    public static final int xBoxPort = 3;

    // Relay Ports
    public static final int lightSwitchChannel = 3;

}
