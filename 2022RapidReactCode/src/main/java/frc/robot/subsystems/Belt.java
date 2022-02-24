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


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
    import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Belt extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
private final double BELT_POWER = .3;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_VictorSPX victorSPXBelt;
private DigitalInput beltLimitOne;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public Belt() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
victorSPXBelt = new WPI_VictorSPX(2);
 
 

beltLimitOne = new DigitalInput(1);
 addChild("beltLimitOne", beltLimitOne);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    victorSPXBelt.setInverted(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        //voltage_scale_factor allows us to compensate for differences in supply voltage.
        SmartDashboard.putBoolean("conveyorSensorTriggered", isBallInConveyor());

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void beltStartUp() {
        victorSPXBelt.set(BELT_POWER);
    }

    public void beltStartDown() {
        victorSPXBelt.set(-BELT_POWER);
    }
    public void beltStop() {
        victorSPXBelt.stopMotor();
    }
    public boolean  isBallInConveyor() {
        return beltLimitOne.get();
    }
}
//We're gonna want the belt to only run forwards whenever the shooter is up to speed and aimed properererly, and only allow reverse movements when the intake is clear.
