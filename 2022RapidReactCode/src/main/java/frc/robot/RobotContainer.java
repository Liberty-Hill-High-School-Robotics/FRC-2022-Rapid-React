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

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private final double CAMERA_HEIGHT = 32;
  private final double CAMERA_ANGLE = 50;
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("BeltToFull", new BeltToFull( m_belt ));
    SmartDashboard.putData("IntakeToFull", new IntakeToFull( m_intake ));
    SmartDashboard.putData("BeltDownCommand", new BeltDownCommand( m_belt ));
    SmartDashboard.putData("ShooterOutCommand", new ShooterOutCommand( m_flywheel ));
    SmartDashboard.putData("ShooterInCommand", new ShooterInCommand( m_flywheel ));
    SmartDashboard.putData("IntakeOutCommand", new IntakeOutCommand( m_intake ));
    SmartDashboard.putData("DriveArcade", new DriveArcade( m_drive ));
    SmartDashboard.putData("ShooterSlider", new ShooterSlider( m_flywheel ));
    //SmartDashboard.putData("RotateToHeadingCommand", new RotateToHeadingCommand( m_drive ));
    SmartDashboard.putData("TurretRight", new TurretRight( m_turret ));
    SmartDashboard.putData("TurretLeft", new TurretLeft( m_turret ));
    SmartDashboard.putData("LiftStartUp", new LiftStartUp( m_lift ));
    SmartDashboard.putData("LiftStartDown", new LiftStartDown( m_lift ));
    SmartDashboard.putData("BlueTarmacAlone1Ball", new BlueTarmacAlone1Ball());
    SmartDashboard.putData("BlueTarmacAlone2Ball", new BlueTarmacAlone2Ball());
    SmartDashboard.putData("RedTarmacAlone2Ball", new RedTarmacAlone2Ball());
    SmartDashboard.putData("RedTarmacAlone1Ball", new RedTarmacAlone1Ball());
    SmartDashboard.putData("TurretAutoAim", new TurretAutoAim( m_turret ));
    SmartDashboard.putData("ShooterToVelocity", new ShooterToVelocity( m_flywheel ));
    SmartDashboard.putData("BlueTarmacPartner1Ball", new BlueTarmacPartner1Ball());
    SmartDashboard.putData("RedTarmacPartner1Ball", new RedTarmacPartner1Ball());
    SmartDashboard.putData("TransversalForward", new TransversalForward( m_transversal ));
    SmartDashboard.putData("TransversalBack", new TransversalBack( m_transversal ));
    SmartDashboard.putData("RearShooterOut", new RearShooterOut( m_rearShooter ));
    SmartDashboard.putData("RearShooterIn", new RearShooterIn( m_rearShooter ));
    SmartDashboard.putData("RearShooterSlider", new RearShooterSlider( m_rearShooter ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    SmartDashboard.putData("RotateToHeadingCommand", new RotateToHeadingCommand( 45, m_drive ));
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_drive.setDefaultCommand(new DriveArcade( m_drive ) );


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    //m_chooser.addOption("BlueTarmacAlone1Ball", new BlueTarmacAlone1Ball());
    //m_chooser.addOption("BlueTarmacAlone2Ball", new BlueTarmacAlone2Ball());
    //m_chooser.addOption("RedTarmacAlone2Ball", new RedTarmacAlone2Ball());
    //m_chooser.addOption("RedTarmacAlone1Ball", new RedTarmacAlone1Ball());
    //m_chooser.addOption("BlueTarmacPartner1Ball", new BlueTarmacPartner1Ball());
    //m_chooser.addOption("RedTarmacPartner1Ball", new RedTarmacPartner1Ball());
    //m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ', '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ', '')} ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    //if robot builder puts a weird line of code above this end, delete it, it should look like bluetarmacalone1ball but with a bunch of $set and stuff
    //looks like "m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ', '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ', '')} ));"

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
final JoystickButton intakeOutButton = new JoystickButton(driverJoystick, 6);        
intakeOutButton.whileHeld(new IntakeOutCommand( m_intake ) ,true);
    SmartDashboard.putData("intakeOutButton",new IntakeOutCommand( m_intake ) );

final JoystickButton intakeInButton = new JoystickButton(driverJoystick, 4);        
intakeInButton.whileHeld(new IntakeToFull( m_intake ) ,true);
    SmartDashboard.putData("intakeInButton",new IntakeToFull( m_intake ) );

final JoystickButton shooterInButton = new JoystickButton(driverJoystick, 5);        
shooterInButton.whileHeld(new ShooterInCommand( m_flywheel ) ,true);
    SmartDashboard.putData("shooterInButton",new ShooterInCommand( m_flywheel ) );

final JoystickButton shooterOutButton = new JoystickButton(driverJoystick, 3);        
shooterOutButton.whileHeld(new ShooterOutCommand( m_flywheel ) ,true);
    SmartDashboard.putData("shooterOutButton",new ShooterOutCommand( m_flywheel ) );

final JoystickButton beltDownButton = new JoystickButton(driverJoystick, 2);        
beltDownButton.whileHeld(new BeltDownCommand( m_belt ) ,true);
    SmartDashboard.putData("beltDownButton",new BeltDownCommand( m_belt ) );

final JoystickButton beltUpButton = new JoystickButton(driverJoystick, 1);        
beltUpButton.whileHeld(new BeltToFull( m_belt ) ,true);
    SmartDashboard.putData("beltUpButton",new BeltToFull( m_belt ) );



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
 // public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
   // return m_chooser.getSelected();
 // }

  public boolean getTv(){
    NetworkTableEntry tv = table.getEntry("tv");
    return tv.getBoolean(false);
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


  public double getDistance(){
    double distance = (TARGET_HEIGHT - CAMERA_HEIGHT) / (CAMERA_ANGLE + getTy());
    return distance;
  }
  

}

