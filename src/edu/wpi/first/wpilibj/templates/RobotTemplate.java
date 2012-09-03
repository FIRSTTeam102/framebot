/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import Team102Lib.Deadband;
import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

        try
        {
            // instantiate the command used for the autonomous period
            autonomousCommand = null;

            // Initialize all subsystems
            CommandBase.init();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In RobotInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    private void setUpJoysticks()
    {
        DriverStation ds = DriverStation.getInstance();

        // ATTENTION: getAnalogIn does not work in robotInit()!!  (except in debug mode :()
        double stickDeadening = ds.getAnalogIn(1) + 1;
        double speedScale = ds.getAnalogIn(3) / 5.0;
        RobotMap.stickDeadBand = new Deadband(RobotMap.joystickRange, RobotMap.flatDeadband, stickDeadening, speedScale);
    }
    
    public void autonomousInit() {
        try
        {
            // We need to setup joysticks in autonmous because it will try to run the default command
            // for the drivetrain.
            setUpJoysticks();

            // schedule the autonomous command (example)
            if(autonomousCommand != null)
                autonomousCommand.start();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In autonomousInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        try
        {
            Scheduler.getInstance().run();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In autonomousPeriodic.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        try
        {
            if(autonomousCommand != null)
                autonomousCommand.cancel();

            setUpJoysticks();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In teleopInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        try
        {
            Scheduler.getInstance().run();
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In teleopPeriodic.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }
    public void updateStatus() {
        CommandBase.driveTrain.updateStatus();
        CommandBase.winch.updateStatus();
    }
}
