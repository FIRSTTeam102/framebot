
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.GetRange;
import edu.wpi.first.wpilibj.templates.commands.SwitchLight;
import edu.wpi.first.wpilibj.templates.commands.WinchAtConstantSpeed;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    // Joysticks
    private Joystick leftstick;
    private Joystick rightstick;
    private Joystick xBox;

    // Buttons
    private JoystickButton leftStick3;
    private JoystickButton rightStick3;

    private JoystickButton xBoxA;
    private JoystickButton xBoxB;
    private JoystickButton xBoxX;
    private JoystickButton xBoxY;
    private JoystickButton xBoxRightBumper;

    public OI() {
        leftstick = new Joystick(RobotMap.leftStickPort);
        rightstick = new Joystick(RobotMap.rightStickPort);
        xBox = new Joystick(RobotMap.xBoxPort);

        leftStick3 = new JoystickButton(leftstick, 3);
        rightStick3 = new JoystickButton(rightstick, 3);

        xBoxA = new JoystickButton(xBox, 1);
        xBoxB = new JoystickButton(xBox, 2);
        xBoxX = new JoystickButton(xBox, 3);
        xBoxY = new JoystickButton(xBox, 4);
        xBoxRightBumper = new JoystickButton(xBox, 6);

        xBoxA.whileHeld(new WinchAtConstantSpeed(0.2));
        xBoxRightBumper.whenPressed(new SwitchLight(true));
        xBoxRightBumper.whenReleased(new SwitchLight(false));
        xBoxB.whenReleased(new GetRange());
    }

    public Joystick getLeftJoystick() {
        return leftstick;
    }
    public Joystick getRightJoystick() {
        return rightstick;
    }
    public Joystick getXBox()
    {
        return xBox;
    }

}

