// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.PneumaticsDemoSubsystem;

public class RobotContainer {
  private final PneumaticsDemoSubsystem mPneumaticsDemoSubsystem = PneumaticsDemoSubsystem.get();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    SmartDashboard.putData("Toggle (Single)", mPneumaticsDemoSubsystem.toggleSolenoid());
    SmartDashboard.putData("Forward (Double)", mPneumaticsDemoSubsystem.doubleForward());
    SmartDashboard.putData("Reverse (Double)", mPneumaticsDemoSubsystem.doubleReverse());
    SmartDashboard.putData("Off (Double)", mPneumaticsDemoSubsystem.doubleOff());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
