package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.RearShooter;
import frc.robot.subsystems.Turret;

/**
 *
 */
public class GroupStopAllShooterMotors extends SequentialCommandGroup {


    public GroupStopAllShooterMotors(
                        Flywheel flywheel,
                        RearShooter rearShooter,
                        Belt belt){

    addCommands(
            parallel(                                                                                   // TURN EVERYTHING OFF
                new FlywheelStop(flywheel),
                new RearShooterStop(rearShooter),
                new BeltStop(belt),
                new LimeLightLEDOff()
            )                                             
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
