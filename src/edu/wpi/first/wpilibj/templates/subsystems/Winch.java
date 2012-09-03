/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.SetSetPointWithTrigger;

/**
 *
 * @author Administrator
 */
public class Winch extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    static final double ENCODER_MAX_VALUE = 1000.0;
    SpeedController winchMotor;
    double xBoxJoyY = 0.0;
    public double setPoint = 0.0;

    public Winch()
    {
        winchMotor = new Jaguar(RobotMap.winchMotor);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new SetSetPointWithTrigger());
    }
    public void winchWithJoystick(Joystick xBoxStick) {
        xBoxJoyY = xBoxStick.getY();

        xBoxJoyY = RobotMap.stickDeadBand.Deaden(xBoxJoyY);

        winchMotor.set(xBoxJoyY);
    }
    public void winchAtSpeed(double speed) {
        winchMotor.set(speed);
    }
    public void updateStatus() {
        SmartDashboard.putDouble("winchMotor: ", winchMotor.get());
        SmartDashboard.putDouble("xBoxJoyY: ", xBoxJoyY);
        SmartDashboard.putDouble("winchSetPoint: ", setPoint);
    }
        public void zeroSetPoint() {
        setPoint = 0;
    }
    public void setSetPointWithTrigger(Joystick xBox) {
        double triggerValue = -xBox.getAxis(Joystick.AxisType.kZ) * 5.0;

        if(setPoint + triggerValue < 0.0)
            return;
        if(setPoint + triggerValue < ENCODER_MAX_VALUE)
            setPoint += triggerValue;
    }
}