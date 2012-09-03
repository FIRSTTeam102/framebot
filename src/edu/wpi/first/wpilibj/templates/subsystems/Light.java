/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Administrator
 */
public class Light extends Subsystem {

    Relay lightSwitch;
    boolean relayOn = false;                //

    public Light(){
        lightSwitch = new Relay(RobotMap.lightSwitchChannel, Relay.Direction.kForward) ;

    }
    public void switchLight(boolean switchOn){
        if((switchOn && relayOn) || (!switchOn && !relayOn))
            return;
        if (switchOn && !relayOn)
        {
            lightSwitch.set(Relay.Value.kOn);
            relayOn = true;
            MessageLogger.LogMessage("Light On");
        }
        else
        {
            lightSwitch.set(Relay.Value.kOff);
            relayOn = false;
            MessageLogger.LogMessage("Light Off");
        }
    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}