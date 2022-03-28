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
import frc.robot.Constants.ShootingConstants.ShootingPosition;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Belt extends SubsystemBase {

    // ***********************************************************************************************
    // * CONSTANTS
    // ***********************************************************************************************
    
    // ***********************************************************************************************
    // * ENUM FOR BELT/CONVEYOR SPEED BASED ON USE
    // ***********************************************************************************************
    public enum Level {
        INDEX       (0.3),      // Auto-Move Ball to Sensor Position
        MANUAL      (0.6)       // If Operator is trying to correct a problem
        ;

        private final double levelCode;
    
        Level(double levelCode) {
            this.levelCode = levelCode;
        }
        
        public double getLevelCode() {
            return this.levelCode;
        }
        
    }

    // ***********************************************************************************************
    // * VARIABLES
    // ***********************************************************************************************
    
    private double CURRENT_BELT_VELOCITY = 0.0;
    private WPI_VictorSPX victorSPXBelt;
    private DigitalInput throughSensor;

    
    /**
    * CONSTRUCTOR
    */
    public Belt() {
        victorSPXBelt = new WPI_VictorSPX(2);
        victorSPXBelt.configFactoryDefault();
        victorSPXBelt.setInverted(true);

        throughSensor = new DigitalInput(1);
        addChild("throughSensor", throughSensor);
    }

    // ***********************************************************************************************
    // * periodic
    // * This method will be called once per scheduler run
    // ***********************************************************************************************
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("conveyorSensorTriggered", isBallInConveyor());
        SmartDashboard.putNumber("BeltCurrentOutput", victorSPXBelt.get());
    }

    // ***********************************************************************************************
    // * simulationPeriodic
    // * This method will be called once per scheduler run when in simulation
    // ***********************************************************************************************
    @Override
    public void simulationPeriodic() {

    }

    // ***********************************************************************************************
    // Put methods for controlling this subsystem here. Call these from Commands.
    
    // ***********************************************************************************************
    // * beltStartUp
    // * Original movement command -- obsolete
    // ***********************************************************************************************
    public void beltStartUp(Level speed) {
        victorSPXBelt.set(speed.getLevelCode());
    }

    // ***********************************************************************************************
    // * beltStartDown
    // * Downward belt movement is an abnormal function
    // ***********************************************************************************************
    public void beltStartDown() {
        victorSPXBelt.set(-Level.MANUAL.getLevelCode());
    }

    // ***********************************************************************************************
    // * beltStop
    // ***********************************************************************************************
    public void beltStop() {
        victorSPXBelt.stopMotor();
    }

    // ***********************************************************************************************
    // * isBallInConveyor
    // * The conveyor is designed to hold one ball at the location of the throughSensor
    // * Determine if the Normally Closed switch has been triggered
    // ***********************************************************************************************
    public boolean  isBallInConveyor() {
        return !throughSensor.get();
    }

    // ***********************************************************************************************
    // * beltUpSpeed
    // * Operate the belt at the requested speed
    // * Speed is based on use
    // ***********************************************************************************************
    public void beltUpSpeed(Constants.ShootingConstants.ShootingPosition position) {
        Constants.ShootingConstants temp = new Constants.ShootingConstants();
        double percentOutput = 0;
        if (position == Constants.ShootingConstants.ShootingPosition.DISTANCE) {
            percentOutput = determinePowerFromDistance();
        }
        else {
            percentOutput = temp.getShootingSpeed(position, Constants.ShootingConstants.SubSystem.BELT);
        }
        victorSPXBelt.set(percentOutput);
    }
    
    // ***********************************************************************************************
    // * determinePowerFromDistance
    // ***********************************************************************************************
    public double determinePowerFromDistance(){
        Constants.ShootingConstants temp = new Constants.ShootingConstants();
        double calculatedPower = 0;
        double distance = RobotContainer.getInstance().getDistance();
        ShootingPosition calculatedPosition;
        calculatedPosition = temp.getPositionFromDistance(distance);
        calculatedPower = temp.getShootingSpeed(calculatedPosition, Constants.ShootingConstants.SubSystem.BELT);
        calculatedPower = 0.6;
        return calculatedPower;
    }

    // ***********************************************************************************************
    // All Code below is for testing only
    // ***********************************************************************************************
    // Belt is controlled by a Victor SPX and therefore does not have velocity control
    // It is controlled by percent output -- a value between -1 and 1
    // Increment/Decrement at 10% each time
    // ***********************************************************************************************

    public void incrementBeltVelocity() {
        CURRENT_BELT_VELOCITY = CURRENT_BELT_VELOCITY + 0.10;
        if (CURRENT_BELT_VELOCITY > 1) CURRENT_BELT_VELOCITY = 1.0;
        SmartDashboard.putNumber("BeltRequestedSpeed", CURRENT_BELT_VELOCITY);   
    }

    public void decrementBeltVelocity() {
        CURRENT_BELT_VELOCITY = CURRENT_BELT_VELOCITY - 0.10;
        if (CURRENT_BELT_VELOCITY < 0) CURRENT_BELT_VELOCITY = 0;
        SmartDashboard.putNumber("BeltRequestedSpeed", CURRENT_BELT_VELOCITY);   
    }

    public void beltTestPrecentOutput() {
        CURRENT_BELT_VELOCITY = SmartDashboard.getNumber("BeltRequestedSpeed", CURRENT_BELT_VELOCITY);
        victorSPXBelt.set(CURRENT_BELT_VELOCITY);
    }
}
