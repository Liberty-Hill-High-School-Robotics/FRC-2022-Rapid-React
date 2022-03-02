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

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

/**
 *
 */
public class RearShooter extends SubsystemBase {
    private final double MAX_SHOOTER_VELOCITY = 5700;
    private final double REAR_POWER = .3;

    private double CURRENT_SHOOTER_VELOCITY = 0.0;
    private CANSparkMax canSparkMAXRearShooter;
    private SparkMaxPIDController m_pidController;
    private RelativeEncoder m_encoder;

    /**
    *
    */
    public RearShooter() {
        canSparkMAXRearShooter = new CANSparkMax(14, MotorType.kBrushless);
        canSparkMAXRearShooter.setIdleMode(IdleMode.kCoast);
        canSparkMAXRearShooter.setInverted(true);
        
        m_encoder = canSparkMAXRearShooter.getEncoder();

        m_pidController = canSparkMAXRearShooter.getPIDController();
        m_pidController.setP(6e-5);
        m_pidController.setI(0);
        m_pidController.setD(0);
        m_pidController.setIZone(0);
        m_pidController.setFF(0.000015);
        m_pidController.setOutputRange(-1, 1);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("RearVelocity", m_encoder.getVelocity());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem here. Call these from Commands.

    public void RearShooterOut(){
        canSparkMAXRearShooter.set(REAR_POWER);
        }
    
    public void RearShooterIn(){
        canSparkMAXRearShooter.set(-REAR_POWER);
    }
    
    public void RearShooterStop(){
        canSparkMAXRearShooter.stopMotor();
    }

    public void RearShooterOutPower (double power) {
      canSparkMAXRearShooter.set(power);
      SmartDashboard.putNumber("powerRear", power);
    }
    // ***********************************************************************************************
    // * rearFlywheelUpSpeed
    // * Operate the flywheel at the requested velocity
    // * Speed is based on use
    // ***********************************************************************************************
    public void rearflywheelUpSpeed(Constants.ShootingConstants.ShootingPosition position) {
        Constants.ShootingConstants temp = new Constants.ShootingConstants();
        double velocity = 0;
        if (position == Constants.ShootingConstants.ShootingPosition.DISTANCE) {
            velocity = determinePowerFromDistance();
        }
        else {
            velocity = temp.getShootingSpeed(position, Constants.ShootingConstants.SubSystem.FLYWHEEL);
        }
        m_pidController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
        CURRENT_SHOOTER_VELOCITY = velocity;
    }    
    
    // ***********************************************************************************************
    // * determinePowerFromDistance
    // TODO: Implement Algorithm to find power based on distance
    // ***********************************************************************************************
    public double determinePowerFromDistance(){
        double calculatedPower = 0;
        double distance = RobotContainer.getInstance().getDistance();
        return calculatedPower;
    }





    public boolean isFlywheelAtVelocity(){
       if (Math.abs(m_encoder.getVelocity() - CURRENT_SHOOTER_VELOCITY) < 10 ) return true;
       else return false;

    }



    // All methods below this comment are for TESTING ONLY
    public void incrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY + 475;
        if (CURRENT_SHOOTER_VELOCITY > MAX_SHOOTER_VELOCITY) CURRENT_SHOOTER_VELOCITY = MAX_SHOOTER_VELOCITY;
        SmartDashboard.putNumber("RearRequestVelocity", CURRENT_SHOOTER_VELOCITY);
    }

    public void decrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY - 475;
        if (CURRENT_SHOOTER_VELOCITY < 0) CURRENT_SHOOTER_VELOCITY = 0;
        SmartDashboard.putNumber("RearRequestVelocity", CURRENT_SHOOTER_VELOCITY);
    }

    public void testVelocity() {
        m_pidController.setReference(SmartDashboard.getNumber("RearRequestVelocity", CURRENT_SHOOTER_VELOCITY), CANSparkMax.ControlType.kVelocity);
    }
}

