package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Flywheel;
/**
 *
 */
public class FlywheelTESTVelocity extends CommandBase {

        private final Flywheel m_flywheel;

    public FlywheelTESTVelocity(Flywheel subsystem) {

        m_flywheel = subsystem;
        addRequirements(m_flywheel);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_flywheel.flyWheelTestVelocity();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
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
