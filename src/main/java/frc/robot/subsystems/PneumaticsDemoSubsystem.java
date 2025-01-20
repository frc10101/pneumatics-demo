// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticsDemoSubsystem extends SubsystemBase {
  private static PneumaticsDemoSubsystem mInstance;
  private final Solenoid mSolenoid;
  private final DoubleSolenoid mDoubleSolenoid;

  private static Solenoid createSolenoid() {
    try {
      return new Solenoid(PneumaticsModuleType.REVPH, 0);
    } catch (Exception e) {
      return null;
    }
  }

  private static DoubleSolenoid createDoubleSolenoid() {
    try {
      return new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 2);
    } catch (Exception e) {
      return null;
    }
  }

  private PneumaticsDemoSubsystem() {
    mSolenoid = createSolenoid();
    mDoubleSolenoid = createDoubleSolenoid();
  }

  public static PneumaticsDemoSubsystem get() {
    if (mInstance == null) {
      mInstance = new PneumaticsDemoSubsystem();
    }

    return mInstance;
  }

  public Command toggleSolenoid() {
    if (mSolenoid == null) {
      return Commands.print("Could not create single solenoid. Check PH port 0.");
    }

    return runOnce(() -> mSolenoid.toggle());
  }

  private Command setDoubleSolenoidState(DoubleSolenoid.Value direction) {
    if (mDoubleSolenoid == null) {
      return Commands.print("Could not create double solenoid. Check PH ports 1 and 2");
    }

    if (direction == null) {
      return runOnce(() -> mDoubleSolenoid.set(Value.kOff));
    }

    return runOnce(() -> mDoubleSolenoid.set(direction));
  }

  public Command doubleForward() {
    return setDoubleSolenoidState(Value.kForward);
  }

  public Command doubleReverse() {
    return setDoubleSolenoidState(Value.kReverse);
  }

  public Command doubleOff() {
    return setDoubleSolenoidState(Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Single State", mSolenoid == null ? false : mSolenoid.get());
    SmartDashboard.putString("Double State", mDoubleSolenoid == null ? null : mDoubleSolenoid.get().toString());
  }
}
