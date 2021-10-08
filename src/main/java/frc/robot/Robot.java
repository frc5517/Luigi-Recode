// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;






public class Robot extends TimedRobot {
  private SpeedController m_motor;
  private static final int kMotorPort = 3;
  private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(0);
  private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(1);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final XboxController m_driverController = new XboxController(0);
  private final Solenoid m_solenoid = new Solenoid(0);
  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(1, 2);
  private static final int kSolenoidButton = 1;
  private static final int kDoubleSolenoidForward = 2;
  private static final int kDoubleSolenoidReverse = 3;

  @Override

  public void robotInit() {
    m_motor = new PWMVictorSPX(kMotorPort);
    CameraServer.getInstance().startAutomaticCapture();}
    
  public void teleopPeriodic() {
    
    m_motor.set(m_driverController.getRawAxis(kMotorPort));

    m_solenoid.set(m_driverController.getRawButton(kSolenoidButton));

    if (m_driverController.getRawButton(kDoubleSolenoidForward)) {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    } else if (m_driverController.getRawButton(kDoubleSolenoidReverse)) {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    m_robotDrive.arcadeDrive(
        m_driverController.getY(Hand.kLeft), m_driverController.getX(Hand.kRight));
  }

}