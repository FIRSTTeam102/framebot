
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author bradmiller
 */
public class WinchAtConstantSpeed extends CommandBase {

    double speed = 0.1;

    public WinchAtConstantSpeed(double speed) {
        requires(winch);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        winch.winchAtSpeed(speed);
    }


    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        winch.winchAtSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

}
