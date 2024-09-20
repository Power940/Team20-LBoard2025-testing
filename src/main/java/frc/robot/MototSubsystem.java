// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MototSubsystem extends SubsystemBase {
	/** Stores the instance of the MotorSubsystem */
	private static MototSubsystem s_subsystem;
	private final CANSparkMax m_bottomMotor = new CANSparkMax(MotorConstants.kBottomMotorID, MotorType.kBrushless);

	/** Creates a new MotorSubsystem. */
	public MototSubsystem() {
		if (s_subsystem != null) {
			try {
				throw new Exception("Motor Subsystem already initialized!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		m_bottomMotor.restoreFactoryDefaults();
		m_bottomMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
		m_bottomMotor.enableVoltageCompensation(12);
		m_bottomMotor.setSmartCurrentLimit(MotorConstants.kSmartCurrentLimit);
	}

	public void leftDrive(double motorSpeed) {
			m_bottomMotor.set(motorSpeed);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public static MototSubsystem get() {
		return s_subsystem;
	}

	/**
	 * Sets the percent output for the motor
	 * 
	 * @param speed The percent output to run the motor at
	 */

	public void setMotorSpeed(double speed) {
		m_bottomMotor.set(speed);
	}

}
