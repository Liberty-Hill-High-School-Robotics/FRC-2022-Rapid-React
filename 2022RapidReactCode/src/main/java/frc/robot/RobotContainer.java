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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
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

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final Intake m_intake = new Intake();
    public final Flywheel m_flywheel = new Flywheel();
    public final Drive m_drive = new Drive();
    public final Belt m_belt = new Belt();
    public final Other m_other = new Other();

// Joysticks
private final Joystick driverJoystick = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("BeltUpCommand", new BeltUpCommand( m_belt ));
    SmartDashboard.putData("BeltDownCommand", new BeltDownCommand( m_belt ));
    SmartDashboard.putData("FlywheelOutCommand", new FlywheelOutCommand( m_flywheel ));
    SmartDashboard.putData("FlywheelInCommand", new FlywheelInCommand( m_flywheel ));
    SmartDashboard.putData("IntakeInCommand", new IntakeInCommand( m_intake ));
    SmartDashboard.putData("IntakeOutCommand", new IntakeOutCommand( m_intake ));
    SmartDashboard.putData("DriveArcade", new DriveArcade( m_drive ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_drive.setDefaultCommand(new DriveArcade( m_drive ) );


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
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
intakeInButton.whileHeld(new IntakeInCommand( m_intake ) ,true);
    SmartDashboard.putData("intakeInButton",new IntakeInCommand( m_intake ) );

final JoystickButton flywheelInButton = new JoystickButton(driverJoystick, 5);        
flywheelInButton.whileHeld(new FlywheelInCommand( m_flywheel ) ,true);
    SmartDashboard.putData("flywheelInButton",new FlywheelInCommand( m_flywheel ) );

final JoystickButton flywheelOutButton = new JoystickButton(driverJoystick, 3);        
flywheelOutButton.whileHeld(new FlywheelOutCommand( m_flywheel ) ,true);
    SmartDashboard.putData("flywheelOutButton",new FlywheelOutCommand( m_flywheel ) );

final JoystickButton beltDownButton = new JoystickButton(driverJoystick, 2);        
beltDownButton.whileHeld(new BeltDownCommand( m_belt ) ,true);
    SmartDashboard.putData("beltDownButton",new BeltDownCommand( m_belt ) );

final JoystickButton beltUpButton = new JoystickButton(driverJoystick, 1);        
beltUpButton.whileHeld(new BeltUpCommand( m_belt ) ,true);
    SmartDashboard.putData("beltUpButton",new BeltUpCommand( m_belt ) );



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getdriverJoystick() {
        return driverJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}

