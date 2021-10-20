package frc.robot;

// Imports
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Robot extends TimedRobot {
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

      // Get and make controllers
    int controlMode;
    XboxController xboxController = new XboxController(1);
    Joystick rightJoystick = new Joystick(2);
    Joystick leftJoystick = new Joystick(1);
    Joystick psController = new Joystick(0);
    
    if (rightJoystick.isConnected() && leftJoystick.isConnected() && psController.isConnected()) {
       controlMode = 0;
    } 
    else if (xboxController.isConnected() && psController.isConnected()) {
      controlMode = 1;
    } 
    else if (xboxController.isConnected()) {
      controlMode = 2;
    }
    else {
      controlMode = 5;
        }

      // Pinch open
        boolean pinchOpen = true;
    if (controlMode == 0) {
          pinchOpen = psController.getRawButton(5);
        } 
    else if (controlMode == 1) {
          pinchOpen = psController.getRawButton(5);
    }
    else if (controlMode == 2) {
          pinchOpen = xboxController.getRawButton(5);
        }
    else if (controlMode == 5) {
          pinchOpen = false;
        }

      // Pinch close
        boolean pinchClose = true;
    if (controlMode == 0) {
      pinchClose = psController.getRawButton(3);
    }
    else if (controlMode == 1) {
      pinchClose = psController.getRawButton(3);
    }
    else if (controlMode == 2) {
      pinchClose = xboxController.getRawButton(3);
    }
    else if (controlMode == 5) {
      pinchClose = false;
    }

      // Intake in
        Boolean IntakeIn = true;
    if (controlMode == 0) {
      IntakeIn = psController.getRawButton(6);
    }
    else if (controlMode == 1) {
      IntakeIn = psController.getRawButton(6);
    }
    else if (controlMode == 2) {
      IntakeIn = xboxController.getRawButton(6);
    }
    else if (controlMode == 5) {
      IntakeIn = false;
    }

      // Intake out
    Boolean IntakeOut = true;
    if (controlMode == 0) {
      IntakeOut = psController.getRawButton(4);
    }
    else if (controlMode == 1) {
      IntakeOut = psController.getRawButton(4);
    }
    else if (controlMode == 2) {
      IntakeOut = xboxController.getRawButton(4);
    }
    else if (controlMode == 5) {
      IntakeOut = false;
    }

      // Elevator up
    Boolean ElevatorUp = true;
    if (controlMode == 0) {
      ElevatorUp = psController.getRawButton(8);
    }
    else if (controlMode == 1) {
      ElevatorUp = xboxController.getRawButton(8);
    }
    else if (controlMode == 2) {
      ElevatorUp = xboxController.getRawButton(8);
    }
    else if (controlMode == 5) {
      ElevatorUp = false;
    }
    
      // Elevator down
    Boolean ElevatorDown = true;
    if (controlMode == 0) {
      ElevatorDown = psController.getRawButton(7);
    }
    else if (controlMode == 1) {
      ElevatorDown = xboxController.getRawButton(7);
    }
    else if (controlMode == 2) {
      ElevatorDown = xboxController.getRawButton(7);
    }
    else if (controlMode == 5) {
      ElevatorDown = false;
    }

      // Solenoid up
    Boolean SolenoidUp = true;
    if (controlMode == 0) {
      SolenoidUp = psController.getRawButton(1);
    }
    else if (controlMode == 1) {
      SolenoidUp = psController.getRawButton(1);
    }
    else if (controlMode == 2) {
      SolenoidUp = xboxController.getRawButton(1);
    }
    else if (controlMode == 5) {
      SolenoidUp = false;
    }

      // Solenoid down
    Boolean SolenoidDown = true;
    if (controlMode == 0) {
      SolenoidUp = psController.getRawButton(2);
    }
    else if (controlMode == 1) {
      SolenoidDown = psController.getRawButton(2);
    }
    else if (controlMode == 2) {
      SolenoidDown = xboxController.getRawButton(2);
    }
    else if (controlMode == 5) {
      SolenoidDown = false;
    }

      // Drive Train Y axis
    double driveY = 1;
    if (controlMode == 0) {
      driveY = leftJoystick.getRawAxis(1);
    }
    else if (controlMode == 1) {
      driveY = xboxController.getRawAxis(1);
    }
    else if (controlMode == 2) {
      driveY = xboxController.getRawAxis(1);
    }
    else if (controlMode == 5) {
      driveY = 0;
    }

      // Drive Train X axis
    double driveX = 1;
    if (controlMode == 0) {
      driveX = rightJoystick.getRawAxis(4);
    }
    else if (controlMode == 1) {
      driveX = xboxController.getRawAxis(4);
    }
    else if (controlMode == 2) {
      driveX = xboxController.getRawAxis(4);
    }
    else if (controlMode == 5) {
      driveX = 0;
    }

      // Intake
      // Press both buttons and it does nothing
    if (IntakeIn && IntakeOut) {
      m_intakerightMotor.set(0);
      m_intakekleftMotor.set(0);
    }
      // Intake In
    else if (IntakeIn) {
      m_intakerightMotor.set(1);
      m_intakekleftMotor.set(-1);
    }
      // Intake Out
    else if (IntakeOut) {
      m_intakerightMotor.set(-1);
      m_intakekleftMotor.set(1);
    }
      // Intake Off
    else {
      m_intakerightMotor.set(0);
      m_intakekleftMotor.set(0);
    }

      // Elevator
      // Press both buttons do nothing
      if (ElevatorUp && ElevatorDown) {
        m_elevatorrightMotor.set(0);
        m_intakekleftMotor.set(0);
      }
      // Start Elevator Up
      else if (ElevatorUp) {
        m_elevatorrightMotor.set(1);
      }
      // Select Elevator Down
      else if (ElevatorDown) {
        m_elevatorleftMotor.set(1);
      }
      // Do Nothing
      else {
        m_elevatorrightMotor.set(0);
        m_elevatorleftMotor.set(0);
      }


      // Solenoids
      // If pinch open button pressed while lift is up set pinch to close
      if (pinchOpen && (m_liftSolenoid.get() == Value.kForward))
      m_liftSolenoid.set(Value.kForward);

      if (pinchOpen) {
        m_pinchSolenoid.set(Value.kForward);
    } 
      if (pinchClose) {
        m_pinchSolenoid.set(Value.kReverse);
      }
     

      // Raises Single Solenoid Lift
      if (SolenoidUp) {
      m_liftSolenoid.set(Value.kReverse);
    }
      // Lowers Single Solenoid Lift
      else if (SolenoidDown) {
      m_liftSolenoid.set(Value.kForward);
    }

      // DriveTrain
    m_robotDrive.arcadeDrive(driveY, driveX);
  }

}