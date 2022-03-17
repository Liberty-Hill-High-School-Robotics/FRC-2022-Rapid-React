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
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */

public class Turret extends SubsystemBase {
    private final double TURRET_POWER = .3;

    private WPI_TalonSRX talonSRXTurret;

    /**
    *
    */
    public Turret() {
        talonSRXTurret = new WPI_TalonSRX(3);
        talonSRXTurret.configFactoryDefault();
        TalonSRXConfiguration allConfigs = new TalonSRXConfiguration();
        talonSRXTurret.setSensorPhase(true);
        //allConfigs.reverseSoftLimitEnable = true;           // Rotation to Right
        //allConfigs.reverseSoftLimitThreshold = -300;
        //allConfigs.forwardSoftLimitEnable = true;           // Rotation to Left
        //allConfigs.forwardSoftLimitThreshold = 300;
        allConfigs.peakOutputForward = .3;
        allConfigs.peakOutputReverse = -.3;
        talonSRXTurret.configAllSettings(allConfigs);
        talonSRXTurret.setNeutralMode(NeutralMode.Brake);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("TurretPosition", talonSRXTurret.getSelectedSensorPosition());
        SmartDashboard.putBoolean("TurretLeftLimit", isLeftLimit());
        SmartDashboard.putBoolean("TurretRightLimit", isRightLimit());
        SmartDashboard.putNumber("getTv", RobotContainer.getInstance().getTv());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // ***********************************************************************************************
    // Put methods for controlling this subsystem here. Call these from Commands.

    public void turretStartLeft() {
        talonSRXTurret.set(TURRET_POWER);
    }

    public void turretStartRight() {
        talonSRXTurret.set(-TURRET_POWER);
    }
    public void turretStop() {
        talonSRXTurret.stopMotor();
    }

    public boolean isLeftLimit() {
        return talonSRXTurret.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean isRightLimit() {
        return talonSRXTurret.getSensorCollection().isRevLimitSwitchClosed();
    }

    public void setThisPID(double output){
        talonSRXTurret.set(output);
    }

    public double getPIDCommand_Input(){
       return RobotContainer.getInstance().getTx();
    }
}

