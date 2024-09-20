// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.Supplier;

import frc.robot.Constants.ControllerConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;

public class DefaultDriveCommand extends Command {

  private final Supplier<Double> m_speedStraight;
  private final MototSubsystem m_leftSubsystem;
  /** Creates a new DefaultDriveCommand. */
  public DefaultDriveCommand(MototSubsystem leftSubsystem, Supplier<Double> speedStraight) {
    m_speedStraight=speedStraight;
    m_leftSubsystem=leftSubsystem;
    addRequirements(m_leftSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speedStraight = MathUtil.applyDeadband(m_speedStraight.get(), ControllerConstants.kDeadzone);
    MototSubsystem.get().leftDrive(speedStraight);
  }
}
