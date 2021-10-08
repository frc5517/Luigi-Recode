package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;

public class Elevator {
    
public final double LIFT_SPEED = .55;
public final double LOWER_SPEED = .30;
public final double CLIMB_SPEED = 1;
public final double MAX_PID_ELEVATOR_SPEED =1;

public double elevatorP = 0.1,
              elevatorI = 0,
              elevatorD = 0.032066348925;

public Talon elevatorLeftMotor;
public Talon elevatorRightMotor;

              

}
