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

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMax; 
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

/**
 *
 */
public class Lift extends SubsystemBase {
    // private final double POWER_LIFT = .1;
    private final int FAST_MAX_ENCODER_COUNT = 250;
    private final int FAST_MIN_ENCODER_COUNT = 50;
    private final double FAST_PERCENT_OUTPUT = 0.5;
    private final double SLOW_PERCENT_OUTPUT = 0.1;

    private RelativeEncoder m_encoder;
    private CANSparkMax canSparkMAXLift;
    private boolean LIFT_IS_LOCKED;

    /**
    *
    */
    public Lift() {
        canSparkMAXLift = new CANSparkMax(8, MotorType.kBrushless);
        canSparkMAXLift.setIdleMode(IdleMode.kBrake);
        // Forward -- TOP
        canSparkMAXLift.setSoftLimit(SoftLimitDirection.kForward, 310);
        canSparkMAXLift.enableSoftLimit(SoftLimitDirection.kForward, true);
        // Reverse -- BOTTOM
        canSparkMAXLift.setSoftLimit(SoftLimitDirection.kReverse, -17);
        canSparkMAXLift.enableSoftLimit(SoftLimitDirection.kReverse, true);

        m_encoder = canSparkMAXLift.getEncoder();

        LIFT_IS_LOCKED = false;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("liftPosition", m_encoder.getPosition());
        SmartDashboard.putBoolean("LiftForwardLimit", canSparkMAXLift.getForwardLimitSwitch(Type.kNormallyOpen).isPressed());
        SmartDashboard.putBoolean("LiftReverseLimit", canSparkMAXLift.getReverseLimitSwitch(Type.kNormallyOpen).isPressed());
        setLockedState();
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Put methods for controlling this subsystem here. Call these from Commands.

    public void liftStartUp(){
        if (!LIFT_IS_LOCKED){
            if (!isLiftLocked()) {
                if (m_encoder.getPosition() > FAST_MIN_ENCODER_COUNT && m_encoder.getPosition() < FAST_MAX_ENCODER_COUNT)
                    canSparkMAXLift.set(FAST_PERCENT_OUTPUT);
                else
                    canSparkMAXLift.set(SLOW_PERCENT_OUTPUT);
            }
        }
    }

    public void liftStartDown(){
        if (!LIFT_IS_LOCKED){
            if (!isLiftLocked()) {
                if (m_encoder.getPosition() > FAST_MIN_ENCODER_COUNT && m_encoder.getPosition() < FAST_MAX_ENCODER_COUNT)
                    canSparkMAXLift.set(-FAST_PERCENT_OUTPUT);
                else
                    canSparkMAXLift.set(-SLOW_PERCENT_OUTPUT);
            }
        }
    }

    public void liftStop(){
        canSparkMAXLift.stopMotor();
    }

    public boolean isLiftLocked() {
        setLockedState();
        return canSparkMAXLift.getReverseLimitSwitch(Type.kNormallyOpen).isPressed();
    }

    public boolean isLiftAtTop(){
        setLockedState();
        return canSparkMAXLift.getForwardLimitSwitch(Type.kNormallyOpen).isPressed();
    }

    private void setLockedState(){
        if (canSparkMAXLift.getReverseLimitSwitch(Type.kNormallyOpen).isPressed() ||
            m_encoder.getPosition() <= -10) LIFT_IS_LOCKED = true;
    }
}

