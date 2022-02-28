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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drive extends SubsystemBase {
    private final double WHEEL_DIAMETER = 6.0;
    private final double TICKS_PER_ROTATION = 4096;

    private WPI_TalonSRX talonSRXR;
    private WPI_TalonSRX talonSRXL;
    private DifferentialDrive driveMain;
    private WPI_VictorSPX victorSPXL;
    private WPI_VictorSPX victorSPXR;
    private AnalogGyro gyro;
    
    private double speedFactor = 1;
    
    /**
    *
    */
    public Drive() {
        talonSRXR = new WPI_TalonSRX(4);
        talonSRXR.configFactoryDefault();
        talonSRXR.setInverted(true);

        victorSPXR = new WPI_VictorSPX(5);
        victorSPXR.configFactoryDefault();
        victorSPXR.follow(talonSRXR);
        victorSPXR.setInverted(InvertType.FollowMaster);
    
        talonSRXL = new WPI_TalonSRX(6);
        talonSRXL.configFactoryDefault();
        talonSRXL.setInverted(false);

        victorSPXL = new WPI_VictorSPX(7);
        victorSPXL.configFactoryDefault();
        victorSPXL.follow(talonSRXL);
        victorSPXL.setInverted(InvertType.FollowMaster);
 
        driveMain = new DifferentialDrive(talonSRXL, talonSRXR);
        addChild("driveMain",driveMain);
        driveMain.setSafetyEnabled(true);
        driveMain.setExpiration(0.1);
        driveMain.setMaxOutput(1.0);

        gyro = new AnalogGyro(0);
        addChild("gyro",gyro);
        gyro.setSensitivity(0.007);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("gyroValue", gyro.getAngle());
        SmartDashboard.putNumber("encoderR", talonSRXR.getSelectedSensorPosition());
        SmartDashboard.putNumber("encoderL", talonSRXL.getSelectedSensorPosition());
        SmartDashboard.putNumber("velocityR", talonSRXR.getSelectedSensorVelocity());
        SmartDashboard.putNumber("velocityL", talonSRXL.getSelectedSensorVelocity());
        SmartDashboard.putNumber("closedLoopErrorL", talonSRXL.getClosedLoopError());
        SmartDashboard.putNumber("closedLoopErrorR", talonSRXR.getClosedLoopError());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Put methods for controlling this subsystem here. Call these from Commands.

    public void DriveArcade(double speed, double rotation) {
        driveMain.arcadeDrive(speed*speedFactor, rotation*speedFactor);
    }

    public double getPIDCommand_Input(){
        return gyro.getAngle()%360;
    }

    public void setThisPID(double output){
        driveMain.arcadeDrive(0, output);
    }

    public void driveDistance(double distance){
        double target = WHEEL_DIAMETER * Math.PI * TICKS_PER_ROTATION;
        target = target / distance;
        talonSRXL.set(ControlMode.Position, target);
        talonSRXR.set(ControlMode.Position, target);
    }

    public void driveStop(){
        talonSRXL.stopMotor();
        talonSRXR.stopMotor();
        victorSPXL.stopMotor();
        victorSPXR.stopMotor();
    }

    public void setPrecissionMode(){
        speedFactor = .3;
    }

    public void setNormalMode(){
        speedFactor = 1;
    }
}

