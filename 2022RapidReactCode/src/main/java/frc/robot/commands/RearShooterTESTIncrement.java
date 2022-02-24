package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.RearShooter;

/**
 *
 */
public class RearShooterTESTIncrement extends CommandBase {

        private final RearShooter m_rearShooter;

        public RearShooterTESTIncrement(RearShooter subsystem) {


        m_rearShooter = subsystem;
        // addRequirements(m_rearShooter);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_rearShooter.incrementShooterVelocity();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RearShooterTESTVelocity m_test = new RearShooterTESTVelocity(m_rearShooter);
        m_test.schedule();
}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}

