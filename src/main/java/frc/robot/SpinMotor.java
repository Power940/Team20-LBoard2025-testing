// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.time.Duration;
import java.time.Instant;

import edu.wpi.first.wpilibj2.command.Command;

public class SpinMotor extends Command {

	private Instant m_startTime;
	private double speedToTime = 0;

	/** Creates a new SpinMotor. */
	public SpinMotor() {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(MototSubsystem.get());
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		m_startTime = Instant.now();
		MototSubsystem.get().setMotorSpeed(0);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		//speeds the moter up for 5 seconds then slowes it down
		if (Duration.between(m_startTime, Instant.now()).toMillis() < 5000) {
			speedToTime = (Duration.between(m_startTime, Instant.now()).toMillis())/10000.0;
		} else {
			speedToTime = -(Duration.between(m_startTime, Instant.now()).toMillis()/10000.0)+1;
		}
		if(speedToTime>1){
			speedToTime=1;
		}else if(speedToTime<-1){
			speedToTime=-1;
		}
		MototSubsystem.get().setMotorSpeed(speedToTime);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		MototSubsystem.get().setMotorSpeed(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (Duration.between(m_startTime, Instant.now()).toMillis() > 10000) {
			return true;
		}
		return false;
	}
}
