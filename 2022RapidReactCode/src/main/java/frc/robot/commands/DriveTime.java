// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: WaitCommand.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class DriveTime extends WaitCommand {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private final Drive m_drive;
    private double m_timeout;
    private double m_speed;
    private double m_turn;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    /*below command should be:

    public DriveTime(double timeout, double speed) {
        super(timeout);
        m_speed = speed;
        m_timeout = timeout;
        
        */


    public DriveTime(double timeout, double speed, double turn, Drive drive) {
        super(timeout);
        m_drive = drive;
        m_speed = speed;
        m_timeout = timeout;
        m_turn = turn;

        

        
        addRequirements(m_drive);

    
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        super.initialize();
        SmartDashboard.putNumber("DriveTimeValues", m_turn);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_drive.DriveArcade(m_speed, m_turn);
    }


    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }

}
