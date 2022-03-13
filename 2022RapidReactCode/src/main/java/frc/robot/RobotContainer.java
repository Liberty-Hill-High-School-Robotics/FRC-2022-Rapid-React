// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.Constants.ShootingConstants.ShootingPosition;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.security.acl.Group;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final RearShooter m_rearShooter = new RearShooter();
    public final Transversal m_transversal = new Transversal();
    public final Lift m_lift = new Lift();
    public final Turret m_turret = new Turret();
    public final Intake m_intake = new Intake();
    public final Flywheel m_flywheel = new Flywheel();
    public final Drive m_drive = new Drive();
    public final Belt m_belt = new Belt();
    public final Other m_other = new Other();

// Joysticks
private final XboxController operatorJoystick = new XboxController(1);
private final Joystick driverJoystick = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  private final double TARGET_HEIGHT = 104;
  private final double CAMERA_HEIGHT = 37;
  private final double CAMERA_ANGLE = 35.008;
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {



    //below, the drivetime smartdashboard should be:     SmartDashboard.putData("DriveTime", new DriveTime(2.5, -.25));

        
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    
   // SmartDashboard.putData("DriveTime", new DriveTime( m_drive ));
   // SmartDashboard.putData("SimpleAuton", new SimpleAuton());

    
    SmartDashboard.putData("RotateToHeadingCommand", new RotateToHeadingCommand( 45, m_drive ));
    SmartDashboard.putData("DriveArcade", new DriveArcade( m_drive ));

    // INTAKE
    SmartDashboard.putData("IntakeInCommand", new IntakeInCommand( m_intake ));
    SmartDashboard.putData("IntakeOutCommand", new IntakeOutCommand( m_intake ));
    SmartDashboard.putData("IntakeToFull", new IntakeToFull( m_intake ));

    // CONVEYOR
    SmartDashboard.putData("BeltUpCommand", new BeltUpCommand(m_belt, Belt.Level.MANUAL));
    SmartDashboard.putData("BeltDownCommand", new BeltDownCommand( m_belt ));
    SmartDashboard.putData("BeltToFull", new BeltToFull( m_belt ));
    SmartDashboard.putData("BeltIncrementVelocity", new beltTestIncrement(m_belt));
    SmartDashboard.putData("BeltDecrementVelocity", new beltTestDecrement(m_belt));
    SmartDashboard.putData("BeltTESTPrecentOutput", new beltTestPrecentOutput(m_belt));

    // TURRET
    SmartDashboard.putData("TurretRight", new TurretRight( m_turret ));
    SmartDashboard.putData("TurretLeft", new TurretLeft( m_turret ));
    SmartDashboard.putData("TurretAutoAim", new TurretAutoAim( m_turret ));
    SmartDashboard.putData("TurretAutoCenter", new TurretAutoCenter(m_turret));

    // SHOOTER FRONT
    SmartDashboard.putData("FlywheelOutCommand", new FlywheelOutCommand( m_flywheel ));
    SmartDashboard.putData("FlywheelInCommand", new FlywheelInCommand( m_flywheel ));
    SmartDashboard.putData("FrontShooterSlider", new ShooterSlider( m_flywheel ));
    SmartDashboard.putData("FrontIncrementVelocity", new FlywheelTestIncrement(m_flywheel));
    SmartDashboard.putData("FrontDecrementVelocity", new FlywheelTESTDecrement(m_flywheel));
    SmartDashboard.putData("FrontVelocity", new FlywheelTESTVelocity(m_flywheel));
    

    // SHOOTER REAR
    SmartDashboard.putData("RearShooterOut", new RearShooterOut( m_rearShooter ));
    SmartDashboard.putData("RearShooterIn", new RearShooterIn( m_rearShooter ));
    SmartDashboard.putData("RearShooterSlider", new RearShooterSlider( m_rearShooter ));
    SmartDashboard.putData("RearIncrementVelocity", new RearShooterTESTIncrement(m_rearShooter));
    SmartDashboard.putData("RearDecrementVelocity", new RearShooterTESTDecrement(m_rearShooter));
    SmartDashboard.putData("RearVelocity", new RearShooterTESTVelocity(m_rearShooter));
    SmartDashboard.putData("RearStop", new RearShooterStop(m_rearShooter));
    
    // LIFT
    SmartDashboard.putData("LiftStartUp", new LiftStartUp( m_lift ));
    SmartDashboard.putData("LiftStartDown", new LiftStartDown( m_lift ));
    

    // TRAVERSE
    SmartDashboard.putData("TransversalForward", new TransversalForward( m_transversal ));
    SmartDashboard.putData("TransversalBack", new TransversalBack( m_transversal ));

    // VISION
    SmartDashboard.putData("LIMELIGHT Off", new LimeLightLEDOff());
    SmartDashboard.putData("LIMELIGHT On", new LimeLightLEDOn());

    // GROUP
    SmartDashboard.putData("GROUP_PrepareToShoot", new PrepareToShoot(m_turret, m_flywheel, m_rearShooter));
    SmartDashboard.putData("GROUP_Shoot", new Shoot(m_turret, m_flywheel, m_rearShooter, m_belt));
    SmartDashboard.putData("GROUP_SimpleAuton", new SimpleAuton(m_turret, m_flywheel, m_rearShooter, m_belt, m_drive));
    SmartDashboard.putData("GROUP_StopAllShooterMotors", new GroupStopAllShooterMotors(m_flywheel, m_rearShooter, m_belt));

    // GROUP COMPONENTS
    SmartDashboard.putData("GROUP_FindTarget", new TurretAutoAim( m_turret ));
    SmartDashboard.putData("GROUP_CenterTarget", new TurretAutoCenter(m_turret));
    SmartDashboard.putData("GROUP_FlyWheelUpSpeed", new FlywheelUpSpeed(m_flywheel, ShootingPosition.TARMAC));
    SmartDashboard.putData("GROUP_RearFlywheelUpSpeed", new RearFlywheelUpSpeed(m_rearShooter, ShootingPosition.TARMAC));
    SmartDashboard.putData("GROUP_BeltUpSpeed", new BeltUpSpeed(m_belt,ShootingPosition.TARMAC));
    SmartDashboard.putData("GROUP_WaitForShooter", new WaitForShooter(3));
    SmartDashboard.putData("GROUP_RearStop", new RearShooterStop(m_rearShooter));
    SmartDashboard.putData("GROUP_FlywheelStop", new FlywheelStop(m_flywheel));
    SmartDashboard.putData("GROUP_BeltStop", new BeltStop(m_belt));
    SmartDashboard.putData("GROUP_DriveTime", new DriveTime(3.5, -.5, m_drive));

    // TEST COMMANDS
    SmartDashboard.putData("TEST1a", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST1a));
    SmartDashboard.putData("TEST1b", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST1b));
    SmartDashboard.putData("TEST1c", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST1c));
    SmartDashboard.putData("TEST1d", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST1d));
    SmartDashboard.putData("TEST1e", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST1e));
    SmartDashboard.putData("TEST1f", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST1f));
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    SmartDashboard.putData("ShootTEST1a", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST1a));
    SmartDashboard.putData("ShootTEST1b", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST1b));
    SmartDashboard.putData("ShootTEST1c", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST1c));
    SmartDashboard.putData("ShootTEST1d", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST1d));
    SmartDashboard.putData("ShootTEST1e", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST1e));
    SmartDashboard.putData("ShootTEST1f", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST1f));

    

    SmartDashboard.putData("TEST2a", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST2a));
    SmartDashboard.putData("TEST2b", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST2b));
    SmartDashboard.putData("TEST2c", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST2c));
    SmartDashboard.putData("TEST2d", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST2d));
    SmartDashboard.putData("TEST2e", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST2e));
    SmartDashboard.putData("TEST2f", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST2f));
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    SmartDashboard.putData("ShootTEST2a", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST2a));
    SmartDashboard.putData("ShootTEST2b", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST2b));
    SmartDashboard.putData("ShootTEST2c", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST2c));
    SmartDashboard.putData("ShootTEST2d", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST2d));
    SmartDashboard.putData("ShootTEST2e", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST2e));
    SmartDashboard.putData("ShootTEST2f", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST2f));



    SmartDashboard.putData("TEST3a", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST3a));
    SmartDashboard.putData("TEST3b", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST3b));
    SmartDashboard.putData("TEST3c", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST3c));
    SmartDashboard.putData("TEST3d", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST3d));
    SmartDashboard.putData("TEST3e", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST3e));
    SmartDashboard.putData("TEST3f", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.TEST3f));
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    SmartDashboard.putData("ShootTEST3a", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST3a));
    SmartDashboard.putData("ShootTEST3b", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST3b));
    SmartDashboard.putData("ShootTEST3c", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST3c));
    SmartDashboard.putData("ShootTEST3d", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST3d));
    SmartDashboard.putData("ShootTEST3e", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST3e));
    SmartDashboard.putData("ShootTEST3f", new ShootByLevel(m_turret, m_flywheel, m_rearShooter, m_belt, ShootingPosition.TEST3f));

    // TEST Distance Based Shooter
    SmartDashboard.putData("DISTANCE Belt", new BeltShootOne(m_belt, m_flywheel, m_rearShooter, ShootingPosition.DISTANCE));
    SmartDashboard.putData("DISTANCE PREPARE", new PrepareToShootByPosition(m_turret, m_flywheel, m_rearShooter, ShootingPosition.DISTANCE));

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_drive.setDefaultCommand(new DriveArcade( m_drive ) );


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        

    m_chooser.addOption("SimpleAuton", new SimpleAuton(m_turret, m_flywheel, m_rearShooter, m_belt, m_drive));
    m_chooser.setDefaultOption("SimpleAuton", new SimpleAuton(m_turret, m_flywheel, m_rearShooter, m_belt, m_drive));

    

        SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }
  
  /*
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton liftDownButton = new JoystickButton(operatorJoystick, XboxController.Button.kA.value);        
liftDownButton.whileHeld(new LiftStartDown( m_lift ) ,true);
    SmartDashboard.putData("LiftDownButton",new LiftStartDown( m_lift ) );

final JoystickButton liftUpButton = new JoystickButton(operatorJoystick, XboxController.Button.kY.value);        
liftUpButton.whileHeld(new LiftStartUp( m_lift ) ,true);
    SmartDashboard.putData("LiftUpButton",new LiftStartUp( m_lift ) );

final JoystickButton intakeToFullButton = new JoystickButton(operatorJoystick, XboxController.Button.kB.value);        
intakeToFullButton.whileHeld(new IntakeToFull( m_intake ) ,true);
    SmartDashboard.putData("IntakeToFullButton",new IntakeToFull( m_intake ) );

// TODO: Update with distance based commands
final JoystickButton prepareToShootButton = new JoystickButton(operatorJoystick, XboxController.Button.kLeftBumper.value);        
prepareToShootButton.whileHeld(new PrepareToShoot(m_turret, m_flywheel, m_rearShooter  ) ,true);
    SmartDashboard.putData("PrepareToShootButton",new PrepareToShoot(m_turret, m_flywheel, m_rearShooter  ) );   

final JoystickButton shootButton = new JoystickButton(operatorJoystick, XboxController.Button.kRightBumper.value);        
shootButton.whileHeld(new Shoot(m_turret, m_flywheel, m_rearShooter, m_belt) ,true);
    SmartDashboard.putData("ShootButton",new Shoot(m_turret, m_flywheel, m_rearShooter, m_belt  ) );
    
final JoystickButton stopShooterButton = new JoystickButton(operatorJoystick, XboxController.Button.kRightBumper.value);        
stopShooterButton.whenReleased(new GroupStopAllShooterMotors(m_flywheel, m_rearShooter, m_belt) ,true);
    SmartDashboard.putData("StopShooterButton",new GroupStopAllShooterMotors(m_flywheel, m_rearShooter, m_belt  ) );  

final JoystickButton precissionButton = new JoystickButton(driverJoystick, 2);        
precissionButton.whenReleased(new drivePrecission( m_drive ) ,true);
    SmartDashboard.putData("precissionButton",new drivePrecission( m_drive ) );



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getdriverJoystick() {
        return driverJoystick;
    }

public XboxController getoperatorJoystick() {
      return operatorJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  // 20220302 : THIS WHOLE METHOD WAS COMMENTED OUT : WHY?
public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
  return m_chooser.getSelected();
}

  public double getTv(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    //NetworkTableEntry tv = table.getEntry("tv");
    //return tv.getBoolean(false);
  }

  public double getTx(){
    NetworkTableEntry tx = table.getEntry("tx");
    return tx.getDouble(0);
  }

  public double getTy(){
    NetworkTableEntry ty = table.getEntry("ty");
    return ty.getDouble(0);
  }

  public double getTa(){
    NetworkTableEntry ta = table.getEntry("ta");
    return ta.getDouble(0);
  }

  public void forceLEDOff(){
    NetworkTableEntry ledMode = table.getEntry("ledMode");
    ledMode.setNumber(1);   // Off
  }

  public void forceLEDOn(){
    NetworkTableEntry ledMode = table.getEntry("ledMode");
    ledMode.setNumber(3);   // On
  }


  public double getDistance(){
    double distance = 999;    // TODO: Default to 999 (out of range) or 0 (at the fender)?
    if (getTv() == 1) {
      double radians = Math.toRadians(CAMERA_ANGLE + getTy());  // 20220228 Corrected Calculation : Incorrectly sending angle in degrees instead of radians to tan method
      distance = (TARGET_HEIGHT - CAMERA_HEIGHT) / Math.tan(radians);
    }
    return distance;
  }
  

}

