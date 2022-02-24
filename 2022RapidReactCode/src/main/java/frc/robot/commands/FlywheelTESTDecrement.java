package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Flywheel;
/**
 *
 */
public class FlywheelTESTDecrement extends CommandBase {

        private final Flywheel m_flywheel;

    public FlywheelTESTDecrement(Flywheel subsystem) {

        m_flywheel = subsystem;
        // addRequirements(m_flywheel);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_flywheel.decrementShooterVelocity();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        FlywheelTESTVelocity m_test = new FlywheelTESTVelocity(m_flywheel);
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
