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

// ***********************************************************************************************
// * 2/28/2022 Remove all AUTO-GENERATE COMMENTS
// ***********************************************************************************************

package frc.robot.subsystems;


import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;


/**
 *
 */
public class Flywheel extends SubsystemBase {
    // While Velocity is reported in units per 100ms, the input to the VELOCITY PID is a percentage from -100 to 100
    //private final double MAX_SHOOTER_VELOCITY = (2048 * 6380) / (600) ;//ouput is in units per 100ms. // 21777 units per 100ms
    private final double MAX_SHOOTER_VELOCITY = 100;
    private final double SHOOTER_POWER_IN = .75;
    private final double SHOOTER_POWER_OUT = .5;
    private double CURRENT_SHOOTER_VELOCITY = 0.0;

    private WPI_TalonFX talonFXShooter1;
    private WPI_TalonFX talonFXShooter2;
    private DigitalInput shooterLmiit;

    
    /**
    *
    */
    public Flywheel() {
        talonFXShooter1 = new WPI_TalonFX(11);
        talonFXShooter1.configFactoryDefault();
        talonFXShooter1.setInverted(true);
        talonFXShooter1.setNeutralMode(NeutralMode.Coast);
        talonFXShooter1.config_kP(0, .0001);
        talonFXShooter1.config_kI(0, 0);
        talonFXShooter1.config_kF(0, 10);
        talonFXShooter1.config_kD(0, 0);

        talonFXShooter2 = new WPI_TalonFX(12);
        talonFXShooter1.configFactoryDefault();
        talonFXShooter2.follow(talonFXShooter1);
        talonFXShooter2.setInverted(InvertType.OpposeMaster);
        talonFXShooter2.setNeutralMode(NeutralMode.Coast);
            
        shooterLmiit = new DigitalInput(2);
        addChild("shooterLmiit", shooterLmiit);
 
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
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("limelightDistance", RobotContainer.getInstance().getDistance());
        SmartDashboard.putNumber("targetVelocity", talonFXShooter1.getClosedLoopTarget());
        SmartDashboard.putNumber("actualVelocity1", talonFXShooter1.getSelectedSensorVelocity());
        SmartDashboard.putNumber("actualVelocity2", talonFXShooter2.getSelectedSensorVelocity());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Put methods for controlling this subsystem here. Call these from Commands.

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
        talonFXShooter1.set(power);
    }

    // All methods below this comment are for TESTING ONLY
    public void incrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY + 10;
        if (CURRENT_SHOOTER_VELOCITY > MAX_SHOOTER_VELOCITY) CURRENT_SHOOTER_VELOCITY = MAX_SHOOTER_VELOCITY;
        SmartDashboard.putNumber("RequestedShooterVelocity", CURRENT_SHOOTER_VELOCITY);
    }

    public void decrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY - 10;
        if (CURRENT_SHOOTER_VELOCITY < 0) CURRENT_SHOOTER_VELOCITY = 0;
        SmartDashboard.putNumber("RequestedShooterVelocity", CURRENT_SHOOTER_VELOCITY);
    }

    public void flyWheelTestVelocity() {
        talonFXShooter1.set(ControlMode.Velocity, SmartDashboard.getNumber("RequestedShooterVelocity", CURRENT_SHOOTER_VELOCITY));
    }
}

