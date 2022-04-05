// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.RearShooter;
import frc.robot.subsystems.Turret;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class ShootByLevel extends SequentialCommandGroup {

    public ShootByLevel(Turret turret,
                Flywheel flywheel,
                RearShooter rearShooter,
                Belt belt,
                Constants.ShootingConstants.ShootingPosition position){

    addCommands(
        // Add Commands here:
        // Also add parallel commands using the
        //
        // addCommands(
        //      new command1(argsN, subsystem),
        //      parallel(
        //          new command2(argsN, subsystem),
        //          new command3(argsN, subsystem)
        //      )    
        //  );
            //new TurretAutoCenter(turret),   
           // new TurretAutoAim(turret),
            //new TurretAutoCenter(turret),  
            new LimeLightLEDOn(),                            
            new WaitCommand(.2),                                    // GET THE SHOOTER UP TO SPEED
            parallel( 
            new FlywheelUpSpeed(flywheel, position),                // Flywheel
            new RearFlywheelUpSpeed(rearShooter, position)          // RearFlywheel
            ),
            //new BeltShootOne(belt, flywheel, rearShooter, position) // SHOOT BALLS
            new BeltUpSpeed(belt, Constants.ShootingConstants.ShootingPosition.DISTANCE)



        );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
