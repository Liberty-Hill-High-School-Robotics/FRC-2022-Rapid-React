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
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.ErrorCode;
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
    /* -----------------------------------------------------------------------------------------------
     * Variables for DashBoard PID -- TESTING ONLY
     * -----------------------------------------------------------------------------------------------*/
    // While Velocity is reported in units per 100ms, the input to the VELOCITY PID is a percentage from -100 to 100
    //private final double MAX_SHOOTER_VELOCITY = (2048 * 6380) / (600) ;//ouput is in units per 100ms. // 21777 units per 100ms
    private final double MAX_SHOOTER_VELOCITY = 200;
    private double CURRENT_SHOOTER_VELOCITY = 0.0;

    /* -----------------------------------------------------------------------------------------------
     * Variables for PERCENT OUTPUT --  TESTING ONLY
     * -----------------------------------------------------------------------------------------------*/
    private final double SHOOTER_POWER_IN = .75;
    private final double SHOOTER_POWER_OUT = .5;

    /* -----------------------------------------------------------------------------------------------
     * Variables for Hardware
     * -----------------------------------------------------------------------------------------------*/
    private WPI_TalonFX talonFXShooter1;            // LEADER
    private WPI_TalonFX talonFXShooter2;            // FOLLOWER
    private DigitalInput shooterLmiit;

    /* -----------------------------------------------------------------------------------------------
     * Variables for Automatic PID Closed Loop Control
     * -----------------------------------------------------------------------------------------------*/
    private final double WHEEL_DIAMETER = 4.0;
    private final double WHEEL_GEAR_REDUCTION = 15/24;
    private final double MAX_RPM = 6380;

    private double targetVelocity = 0;              // UNITS?


    
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
        talonFXShooter2.configFactoryDefault();
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

        targetVelocity = 0;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("limelightDistance", RobotContainer.getInstance().getDistance());

        if (targetVelocity != 0 || CURRENT_SHOOTER_VELOCITY != 0){
            SmartDashboard.putNumber("FrontTarget", talonFXShooter1.getClosedLoopTarget());
            SmartDashboard.putNumber("FrontActual", talonFXShooter1.getSelectedSensorVelocity());
            SmartDashboard.putNumber("FrontError", talonFXShooter1.getClosedLoopError());
            SmartDashboard.putBoolean("FrontAtSetpoint", isFlywheelAtVelocity());
        }
        else{
            SmartDashboard.putNumber("FrontTarget", 0);
            SmartDashboard.putNumber("FrontActual", 0);
            SmartDashboard.putNumber("FrontError", 0);
            SmartDashboard.putBoolean("FrontAtSetpoint", false);
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Put methods for controlling this subsystem here. Call these from Commands.
    /* -----------------------------------------------------------------------------------------------
     * Methods used in all modes (percent output, automatic PID, Dashboard PID)
     * -----------------------------------------------------------------------------------------------*/
    public void flywheelStop() {
        talonFXShooter1.stopMotor();;  
        targetVelocity = 0;
    }

    /* -----------------------------------------------------------------------------------------------
     * Methods for Percent Output TESTING ONLY
     * -----------------------------------------------------------------------------------------------*/

    public void flywheelStartIn() {
        talonFXShooter1.set(SHOOTER_POWER_IN);
    }

    public void flywheelStartOutSlider(double power) {
        talonFXShooter1.set(power);
    }

    public void flywheelStartOut() {
        talonFXShooter1.set(SHOOTER_POWER_OUT);
    }

    public void flywheelOutPower (double power) {
        talonFXShooter1.set(power);
    }


    /* -----------------------------------------------------------------------------------------------
     * Methods for Automatic PID Closed Loop Control
     * -----------------------------------------------------------------------------------------------*/
    public void flywheelUpSpeed(Constants.ShootingConstants.ShootingPosition position) {
        Constants.ShootingConstants temp = new Constants.ShootingConstants();
        targetVelocity = 0;
        if (position == Constants.ShootingConstants.ShootingPosition.DISTANCE) {
            targetVelocity = determinePowerFromDistance();
        }
        else {
            targetVelocity = temp.getShootingSpeed(position, Constants.ShootingConstants.SubSystem.FLYWHEEL);
        }
        talonFXShooter1.set(ControlMode.Velocity, targetVelocity);
    }
    
    // TODO: Implement Algorithm to find power based on distance
    public double determinePowerFromDistance(){
        double calculatedPower = 0;
        double distance = RobotContainer.getInstance().getDistance();
        return calculatedPower;
    }

    public boolean isFlywheelAtVelocity(){
       if (Math.abs(talonFXShooter1.getClosedLoopError()) < 10) return true;
       else return false;
    }

    private double convertFPStoRPM (double fps){
        return (60 * 12 * fps) / (WHEEL_GEAR_REDUCTION * WHEEL_DIAMETER * Math.PI);
    }

    private double convertRPMtoFPS (double rpm){
        return (rpm * WHEEL_GEAR_REDUCTION * WHEEL_DIAMETER * Math.PI) / (60 *12);
    }

    private double convertFPStoSetPoint (double fps) {
        return 0;
    }

    private double convertSetPointToFPS (double setPoint) {
        return 0;
    }

    /* -----------------------------------------------------------------------------------------------
     * Methods for DASHBOARD Control of PID Closed Loop Control
     * -----------------------------------------------------------------------------------------------*/
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

    public void flywheelOutWithVelocity(double distance) {
        talonFXShooter1.set(ControlMode.Velocity, MAX_SHOOTER_VELOCITY * .9);
    }
}

