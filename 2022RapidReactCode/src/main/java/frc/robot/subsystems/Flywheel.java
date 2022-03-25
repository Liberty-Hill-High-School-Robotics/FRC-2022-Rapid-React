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
import frc.robot.Constants.ShootingConstants.ShootingPosition;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import org.opencv.video.DISOpticalFlow;

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
    private final double UNITS_PER_ROTATION = 2048;
    private final double MAX_RPM = 6380;
    private final double LAUNCH_ANGLE = 60;      //change to actuall correct num
    private final double INITAL_HEIGHT = (37 / 12);      //change to correct num

    private double targetVelocity = 0;              // UNITS?
    private double targetVelocityUnits = 0;


    
    /**
    *
    */
    public Flywheel() {
        talonFXShooter1 = new WPI_TalonFX(11);
        talonFXShooter1.configFactoryDefault();
        talonFXShooter1.setInverted(true);
        talonFXShooter1.setNeutralMode(NeutralMode.Coast); 
        
        TalonFXConfiguration allConfigs = new TalonFXConfiguration();
        allConfigs.reverseSoftLimitEnable = false;           // Rotation to Right
        //allConfigs.reverseSoftLimitThreshold = -300;
        allConfigs.forwardSoftLimitEnable = false;           // Rotation to Left
        //allConfigs.forwardSoftLimitThreshold = 300;
        
        talonFXShooter1.config_kP(0, .0001);
        talonFXShooter1.config_kI(0, 0);
        talonFXShooter1.config_kF(0, 0.046);
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
            SmartDashboard.putNumber("FrontTargetUnits", targetVelocityUnits);
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
            targetVelocity = determinePowerFromDistance();  // RPM
        }
        else {
            targetVelocity = temp.getShootingSpeed(position, Constants.ShootingConstants.SubSystem.FLYWHEEL);  // RPM
        }
        targetVelocityUnits = convertRPMtoSetPoint(targetVelocity);
        talonFXShooter1.set(ControlMode.Velocity, targetVelocityUnits);
    }
    
    public double determinePowerFromDistance(){ // RETURN RPM
        double Distance = RobotContainer.getInstance().getDistance();
        double CalculatedVelocity = Math.sqrt((16.087 * Distance * Distance) / ((INITAL_HEIGHT + (Math.tan(LAUNCH_ANGLE) * Distance) - 9.5) * Math.cos(LAUNCH_ANGLE) * Math.cos(LAUNCH_ANGLE) ));
        double calculatedPower = convertFPStoRPM(CalculatedVelocity);

        SmartDashboard.putNumber("FLYWHEEL DISTANCE", Distance);
        SmartDashboard.putNumber("FLYWHEEL VELOCITY (FT/SEC)", CalculatedVelocity);
        SmartDashboard.putNumber("FLYWHEEL RPM", calculatedPower);
        SmartDashboard.putNumber("FLYWHEEL UNITS/100MSEC", convertFPStoSetPoint(CalculatedVelocity));

        /*
        Constants.ShootingConstants temp = new Constants.ShootingConstants();
        double calculatedPower = 0;
        double distance = RobotContainer.getInstance().getDistance();
        SmartDashboard.putNumber("CalculatedDistance", distance);
        ShootingPosition calculatedPosition;
        calculatedPosition = temp.getPositionFromDistance(distance);
        SmartDashboard.putString("CalculatedPos", calculatedPosition.toString());
        calculatedPower = temp.getShootingSpeed(calculatedPosition, Constants.ShootingConstants.SubSystem.FLYWHEEL);
        SmartDashboard.putNumber("CalculatedTarget", calculatedPower);
        return calculatedPower;
        */

        return calculatedPower;
    }

    public boolean isFlywheelAtVelocity(){
       double error = 0;
       //double actual = 0;
       
       //actual = talonFXShooter1.getSelectedSensorVelocity();
       //error = actual - targetVelocityUnits;
       
       error = talonFXShooter1.getClosedLoopError();
       if (Math.abs(error) < 500) return true;
       else return false;

       //if (Math.abs(talonFXShooter1.getClosedLoopError()) < 10) return true;
      //else return false;
    }

    private double convertFPStoRPM (double fps){
        // FT    60 SEC   12 IN   REV                                          REV
        // --- X ------ X ----- X --------------------------------------- ==>  --------
        // SEC   MIN      FT      GEAR_REDUCTION * WHEEL_DIAMETER * PI IN      MIN
        
        return (60 * 12 * fps) / (WHEEL_GEAR_REDUCTION * WHEEL_DIAMETER * Math.PI);
    }

    private double convertRPMtoFPS (double rpm){
        // REV   MIN      WHEEL DIAMETER * GEAR REDUCTION * PI (IN)   FT         FT
        // --- X ------ X ----------------------------------------- X ----- ==>  --------
        // MIN   60 SEC   REV                                         12 IN      SEC

        double fps = 0; 
        // fps = (rpm * UNITS_PER_ROTATION)/600;
        fps = (rpm * WHEEL_DIAMETER * WHEEL_GEAR_REDUCTION * Math.PI) / (60 * 12);
        return fps;
    }

    private double convertFPStoSetPoint (double fps) {
        double setPoint = 0;
        // FT    SEC            12 IN   REV                                       UNITS PER ROTATION      UNITS
        // --- X ------------ X ----- X --------------------------------------- X ------------------ ==>  --------
        // SEC   10 (100MSEC)   FT      GEAR_REDUCTION * WHEEL_DIAMETER * PI IN   REV                     100 MSEC

        setPoint = (fps * 12 * UNITS_PER_ROTATION) / (10 * WHEEL_DIAMETER * Math.PI);
        return setPoint;
    }

    private double convertRPMtoSetPoint (double rpm) {
        double setPoint = 0;
        // REV   MIN      SEC            UNITS PER ROTATION      UNITS
        // --- X ------ X ------------ X ------------------ ==>  --------
        // MIN   60 SEC   10 (100MSEC)   REV                     100 MSEC

        setPoint = rpm * UNITS_PER_ROTATION / 600;
        return setPoint;
    }

    private double convertSetPointToFPS (double setPoint) {
        // UNITS         10 (100MSEC)   REV                  GEAR REDUCTION * DIAMETER * PI   FT         FT
        // ---------- X  ------------ X ------------------ X ------------------------------ X ----- ==>  ---
        // (100 MSEC)    SEC            UNITS PER ROTATION   REV                              12 IN      SEC
        
        double fps;
        fps = (10 * WHEEL_GEAR_REDUCTION * WHEEL_DIAMETER * Math.PI) / (UNITS_PER_ROTATION * 12);
        
        return fps;
    }

    /* -----------------------------------------------------------------------------------------------
     * Methods for DASHBOARD Control of PID Closed Loop Control
     * -----------------------------------------------------------------------------------------------*/
    public void incrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY + 10;
        targetVelocityUnits = (2048 * ((CURRENT_SHOOTER_VELOCITY / 100) * 6380)) / (600);
        if (CURRENT_SHOOTER_VELOCITY > MAX_SHOOTER_VELOCITY) CURRENT_SHOOTER_VELOCITY = MAX_SHOOTER_VELOCITY;
        SmartDashboard.putNumber("RequestedShooterVelocity", CURRENT_SHOOTER_VELOCITY);
    }

    public void decrementShooterVelocity() {
        CURRENT_SHOOTER_VELOCITY = CURRENT_SHOOTER_VELOCITY - 10;
        targetVelocityUnits = (2048 * ((CURRENT_SHOOTER_VELOCITY / 100) * 6380)) / (600);
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

