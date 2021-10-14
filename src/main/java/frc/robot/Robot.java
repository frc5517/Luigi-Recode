package frc.robot;

  // Imports
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Robot extends TimedRobot {
  // Input
  private final XboxController m_driverController = new XboxController(0);
  // Intake
  private final PWMVictorSPX m_intakerightMotor = new PWMVictorSPX(5);
  private final PWMVictorSPX m_intakekleftMotor = new PWMVictorSPX(4);
  // Elevator
  private final PWMVictorSPX m_elevatorrightMotor = new PWMVictorSPX(3);
  private final PWMVictorSPX m_elevatorleftMotor = new PWMVictorSPX(2);
  // Solenoid
  private final DoubleSolenoid m_liftSolenoid = new DoubleSolenoid(4, 7);
  private final DoubleSolenoid m_pinchSolenoid = new DoubleSolenoid(1, 6);
  // DriveTrain
  private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(1);
  private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(0);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  


  @Override

  public void robotInit() {
    // Camera
    // Get Camera
    CameraServer.getInstance().startAutomaticCapture();
  
    //Solenoids
    // Defaults Double Solenoid Pinch Closed
     m_pinchSolenoid.set(Value.kForward);
    // Defaults Single Solenoid Lift Up
     m_liftSolenoid.set(Value.kReverse);
     
  }

    

  public void teleopPeriodic() {

      // Intake
      // Press both buttons and it does nothing
    if (m_driverController.getRawButton(6) && m_driverController.getRawButton(4)) {
      m_intakerightMotor.set(0);
      m_intakekleftMotor.set(0);
    }
      // Intake In
    else if (m_driverController.getRawButton(6)) {
      m_intakerightMotor.set(0.5);
      m_intakekleftMotor.set(-0.5);
    }
      // Intake Out
    else if (m_driverController.getRawButton(4)) {
      m_intakerightMotor.set(0.5);
      m_intakekleftMotor.set(0.5);
    }
      // Intake Off
    else {
      m_intakerightMotor.set(0);
      m_intakekleftMotor.set(0);
    }

      // Elevator
      // Press both buttons do nothing
      if (m_driverController.getRawButton(8) && (m_driverController.getRawButton(9))) {
        m_elevatorrightMotor.set(0);
        m_intakekleftMotor.set(0);
      }
      // Start Elevator Up
      else if (m_driverController.getRawButton(8)) {
        m_elevatorrightMotor.set(1);
      }
      // Select Elevator Down
      else if (m_driverController.getRawButton(7)) {
        m_elevatorleftMotor.set(1);
      }
      // Do Nothing
      else {
        m_elevatorrightMotor.set(0);
        m_elevatorleftMotor.set(0);
      }


      // Solenoids
      // Opens Double Solenoid Pinch
      if (m_driverController.getRawButton(5)) {
      m_pinchSolenoid.set(Value.kForward);
    }
      // Closes Double Solenoid Pinch
      else if (m_driverController.getRawButton(3)) {
       m_pinchSolenoid.set(Value.kReverse);
     }
      // Raises Single Solenoid Lift
      else if (m_driverController.getRawButton(1)) {
      m_liftSolenoid.set(Value.kReverse);
    }
      // Lowers Single Solenoid Lift
      else if (m_driverController.getRawButton(2)) {
      m_liftSolenoid.set(Value.kForward);
    }
    // Stops pinch from opening while lift is up
      else if ((m_liftSolenoid.get() == Value.kForward) && (m_pinchSolenoid.get() == Value.kForward)); {
        m_pinchSolenoid.set(Value.kReverse);
    }    

      // DriveTrain
    m_robotDrive.arcadeDrive(
        m_driverController.getY(Hand.kLeft), m_driverController.getX(Hand.kRight));
  }

}