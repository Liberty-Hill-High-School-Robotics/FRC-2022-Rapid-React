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
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.RearShooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Intake;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class TwoBallAuton extends SequentialCommandGroup {


    public TwoBallAuton(Turret turret,
                        Flywheel flywheel,
                        RearShooter rearShooter,
                        Belt belt,
                        Drive drive,
                        Intake intake){

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
          //  new TurretAutoAim(turret),
          //  new TurretAutoCenter(turret), 
           parallel(
            new IntakeTimed(2.7, intake),
            new DriveTime(2.45, .6, 0, drive) //power was .5, changed to 1 to 2.45 time back to .245
            ),
           // new IntakeStop(intake),
            new LimeLightLEDOn(),  
            new WaitCommand(1),
           // new DriveRotateToTarget(drive),
            new DriveTime(.58, 0, 1, drive), //.5 to .55 to .575 to .6 back to .575 to .58
            new WaitCommand(.5),
            new TurretAutoCenter(turret),
            //ParallelRaceGroup(
            //    new TurretAutoCenter(turret),
            //    new WaitCommand(1.25)
           // ),
                                                                // AIM
            //new WaitCommand(5),                                                                           // GET THE SHOOTER UP TO SPEED
            new FlywheelUpSpeed(flywheel, Constants.ShootingConstants.ShootingPosition.DISTANCE),         // Flywheel (TARMAC)
           // new RearFlywheelUpSpeed(rearShooter, Constants.ShootingConstants.ShootingPosition.TARMAC)   // RearFlywheel (TARMAC)
            new WaitForShooter(1),
            //new BeltShootOne(belt, flywheel, rearShooter, Constants.ShootingConstants.ShootingPosition.TwoAuton),                // SHOOT BALLS
            new BeltUpSpeed(belt, Constants.ShootingConstants.ShootingPosition.DISTANCE),
            new WaitForShooter(3),                                                                      // WAIT FOR SHOOTING TO BE DONE
            parallel(                                                                                   // TURN EVERYTHING OFF
                new FlywheelStop(flywheel),
                //new RearShooterStop(rearShooter),
                new BeltStop(belt)
            ),   
            new DriveTime(2.4, -.5, 0, drive)                                       
                                                                          // BACK UP OUT OF TARMAC
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
