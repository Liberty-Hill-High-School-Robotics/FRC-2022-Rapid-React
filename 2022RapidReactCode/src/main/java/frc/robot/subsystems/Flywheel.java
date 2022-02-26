// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Flywheel extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    private final double MAX_SHOOTER_VELOCITY = (2048 * 6380) / (600) ;//ouput is in units per 100ms. // 21777 units per 100ms
    private final double SHOOTER_POWER_IN = .75;
    private final double SHOOTER_POWER_OUT = .5;
    private double CURRENT_SHOOTER_VELOCITY = 0.0;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonFX talonFXShooter1;
private WPI_TalonFX talonFXShooter2;
private DigitalInput shooterLmiit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public Flywheel() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
talonFXShooter1 = new WPI_TalonFX(11);
 
 

talonFXShooter2 = new WPI_TalonFX(12);
 
 

shooterLmiit = new DigitalInput(2);
 addChild("shooterLmiit", shooterLmiit);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        //first attempt
        //talonFXShooter1.config_kP(0, .25);
        //talonFXShooter1.config_kI(0, .001);
        //talonFXShooter1.config_kF(0, 10);
        //talonFXShooter1.config_kD(0, 20);

        //second
        //talonFXShooter1.config_kP(0, .25);
        //talonFXShooter1.config_kI(0, 0);
        //talonFXShooter1.config_kF(0, 10);
        //talonFXShooter1.config_kD(0, 0);

        //third
        //talonFXShooter1.config_kP(0, .1);
        //talonFXShooter1.config_kI(0, 0);
        //talonFXShooter1.config_kF(0, 10);
        //talonFXShooter1.config_kD(0, 0);

        //fourth
        //talonFXShooter1.config_kP(0, .01);
        //talonFXShooter1.config_kI(0, 0);
        //talonFXShooter1.config_kF(0, 10);
       //talonFXShooter1.config_kD(0, 0);

        //fifth
        //talonFXShooter1.config_kP(0, .001);
        //talonFXShooter1.config_kI(0, 0);
        //talonFXShooter1.config_kF(0, 10);
        //talonFXShooter1.config_kD(0, 0);

        //sixth
        talonFXShooter1.config_kP(0, .0001);
        talonFXShooter1.config_kI(0, 0);
        talonFXShooter1.config_kF(0, 10);
        talonFXShooter1.config_kD(0, 0);



    talonFXShooter1.setInverted(true);

    talonFXShooter2.follow(talonFXShooter1);
    talonFXShooter2.setInverted(InvertType.OpposeMaster);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("limelightDistance", RobotContainer.getInstance().getDistance());
        SmartDashboard.putNumber("targetVelocity", talonFXShooter1.getClosedLoopTarget());
        SmartDashboard.putNumber("actualVelocity1", talonFXShooter1.getSelectedSensorVelocity());
        SmartDashboard.putNumber("actualVelocity2", talonFXShooter1.getSelectedSensorVelocity());
        SmartDashboard.putNumber("RequestedShooterVelocity", CURRENT_SHOOTER_VELOCITY);
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void flywheelStartIn() {
        talonFXShooter1.set(SHOOTER_POWER_IN);
    }

    public void flywheelStartOutSlider(double power) {
        talonFXShooter1.set(power);

    }

    public void flywheelStartOut() {
        talonFXShooter1.set(SHOOTER_POWER_OUT);

    }

    public void flywheelStop() {
        talonFXShooter1.stopMotor();;  
    }

    public void flywheelOutWithVelocity(double distance) {
        talonFXShooter1.set(ControlMode.Velocity, MAX_SHOOTER_VELOCITY * .9);
    }

    public void flywheelOutPower (double power) {
        //talonFXShooter1.set(ControlMode.Velocity, MAX_SHOOTER_VELOCITY * power);
       //talonFXShooter1.set(TalonFXControlMode.Velocity, MAX_SHOOTER_VELOCITY * power);
      talonFXShooter1.set(power);
      SmartDashboard.putNumber("power", power);
    }

    // All methods below this comment are for TESTING ONLY
    public void incrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY + 10;
        if (CURRENT_SHOOTER_VELOCITY > 100) CURRENT_SHOOTER_VELOCITY = 100;
    }

    public void decrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY - 10;
        if (CURRENT_SHOOTER_VELOCITY < 0) CURRENT_SHOOTER_VELOCITY = 0;
    }

    public void flyWheelTestVelocity() {
        talonFXShooter1.set(ControlMode.Velocity, CURRENT_SHOOTER_VELOCITY);
    }
}

