// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

// ***********************************************************************************************
// * 2/28/2022 Remove all AUTO-GENERATE COMMENTS
// ***********************************************************************************************

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.ShootingConstants.ShootingPosition;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;

/*
 * Turn on Limelight
 * Spin Flywheel
 * Belt (stop after shot and wait)
 * Belt (stop after shot and wait)
 * Belt (stop when empty)
 * Turn off limelight
*/
public class SingleCommandShoot extends CommandBase {

    private final Flywheel m_flywheel;
    private final Belt m_belt;
    private final Intake m_intake;
    private Constants.ShootingConstants.ShootingPosition m_position;
    private boolean waitForEmpty;
    private boolean waitForSecond;
    private int count;

    public SingleCommandShoot(Flywheel flywheel, Belt belt, Intake intake, ShootingPosition position ) {
        m_flywheel = flywheel;
        m_belt = belt;
        m_intake = intake;
        m_position = position;
        addRequirements(m_flywheel, m_belt);
        waitForEmpty = true;
        waitForSecond = false;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        RobotContainer.getInstance().forceLEDOn();
        m_flywheel.flywheelUpSpeed(m_position);
        if (m_belt.isBallInConveyor()) waitForEmpty = true;
        if (m_intake.isBallInIntake()) waitForSecond = true;
        count = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_belt.beltStop();
        RobotContainer.getInstance().forceLEDOff();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        boolean done = false;

        if (waitForEmpty) {                                 // Ball in Conveyor Waiting for Shot
            if (!m_belt.isBallInConveyor()) {
                waitForEmpty = false;                       // "SHOT"
            }
            else {
                if (m_flywheel.isFlywheelAtVelocity()) {    
                    m_belt.beltUpSpeed(m_position);         // SHOOT
                }     
            } 
        }
        else {                                               
            if (waitForSecond) {                            // DEAL WITH 2ND BALL
                if (m_belt.isBallInConveyor()) {            // HOLD BALL AT SWITCH & WAIT FOR FLYWHEEL
                    m_belt.beltStop();    
                    waitForEmpty = true;   
                    waitForSecond = false;                               
                }
                else {                                      // MOVE BALL TO SWITCH
                    // BELT IS MOVING -- DON'T NEED TO START IT AGAIN m_belt.beltUpSpeed(m_position);
                }
            }
            else {                                          // SHOULD BE DONE -- WAIT A FEW CYCLES TO MAKE SURE IT IS GONE
                count = count + 1;
            }
        }

        if (count > 20) done = true;

        return done;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
