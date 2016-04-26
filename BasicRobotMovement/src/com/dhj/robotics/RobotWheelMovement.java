package com.dhj.robotics;
/**
 * Assuming 1 motor per wheel/track, this object controls the motors 
 * together. (Forward calls both wheels/tracks to move forward)
 * 
 * @author gsmitley
 *
 */
public class RobotWheelMovement {

	private Motor leftMotor;
	private Motor rightMotor;
	
	/**
	 * @param leftMotor Associated with the left wheel/track of robot
	 * @param rightMotor Associated with the right wheel/track of robot
	 */
	public RobotWheelMovement(Motor leftMotor, Motor rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	/**
	 * Moves both wheels/tracks forward for given length of time
	 * @param timeInMilliseconds Time in milliseconds (1000 milliseconds = 1 second)
	 */
	public void forward(long timeInMilliseconds) throws InterruptedException {
		leftMotor.forward();
		rightMotor.forward();
		Thread.sleep(timeInMilliseconds);
	}
	
	/**
	 * Moves both wheels/tracks forward until stopped
	 */
	public void forward() {
		leftMotor.forward();
		rightMotor.forward();
	}
	
	/**
	 * Moves both wheels/tracks in reverse for given length of time
	 * @param timeInMilliseconds Time in milliseconds (1000 milliseconds = 1 second)
	 */
	public void reverse(long timeInMilliseconds) throws InterruptedException {
		leftMotor.reverse();
		rightMotor.reverse();
		Thread.sleep(timeInMilliseconds);
	}
	
	/**
	 * Moves both wheels/tracks in reverse until stopped
	 */
	public void reverse() {
		leftMotor.reverse();
		rightMotor.reverse();
	}
	
	/**
	 * Spins the left motor in reverse and the right motor forward to turn left
	 * for a given length of time
	 * @param timeInMilliseconds Time in milliseconds (1000 milliseconds = 1 second)
	 */
	public void leftTurn(long timeInMilliseconds) throws InterruptedException {
		leftMotor.reverse();
		rightMotor.forward();
		Thread.sleep(timeInMilliseconds);
	}
	
	/**
	 * Spins the left motor forward and the right motor in reverse to turn right
	 * for a given length of time
	 * @param timeInMilliseconds Time in milliseconds (1000 milliseconds = 1 second)
	 */
	public void rightTurn(long timeInMilliseconds) throws InterruptedException {
		leftMotor.forward();
		rightMotor.reverse();
		Thread.sleep(timeInMilliseconds);
	}
	
	/**
	 * Turns both motors off by setting each relay to the off 
	 * position, sending the same polarity to each motor terminal
	 */
	public void stop() {
		leftMotor.motorOff();
		rightMotor.motorOff();
	}
	
	public Motor getLeftMotor() {
		return leftMotor;
	}

	public void setLeftMotor(Motor leftMotor) {
		this.leftMotor = leftMotor;
	}

	public Motor getRightMotor() {
		return rightMotor;
	}

	public void setRightMotor(Motor rightMotor) {
		this.rightMotor = rightMotor;
	}
	
}
