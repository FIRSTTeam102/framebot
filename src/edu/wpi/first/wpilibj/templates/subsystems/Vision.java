/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 *
 * @author Administrator
 */
public class Vision extends Subsystem {
    AxisCamera camera;          // the axis camera object (connected to the switch)

    public Vision()
    {
        camera = AxisCamera.getInstance();  // get an instance of the camera
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}