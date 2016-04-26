package com.dhj.robotics;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Object that represents a single Motor. This class can be used to 
 * control the movement of a single motor with a 2 relay input. 
 * (4 relay input will be added)
 * 
 * @author Smitley, Garrett
 */
public class Motor {
	
	private GpioController gpioController;
	private GpioPinDigitalOutput relay1;
	private GpioPinDigitalOutput relay2;
	
	/**
	 * 2 relay motor, assuming one relay per motor terminal
	 * (See Constructor Detail for parameter details)
	 * @param gpioController 
	 * 	The main Gpio Controller (usually defined within the main class)
	 * @param relay1
	 * 	Output pin associated with relay 1 (Relay1(Low)+Relay2(High) ==> Forward)
	 * @param relay2
	 * 	Output pin associated with relay 2 (Relay1(Low)+Relay2(High) ==> Forward)
	 **/	
	public Motor(GpioController gpioController, GpioPinDigitalOutput relay1, 
			GpioPinDigitalOutput relay2) {
		this.gpioController=gpioController;
		this.relay1 = relay1;
		this.relay2 = relay2;
		
	}

	/**
	 * Spins the motor forwards for given length of time
	 * @param timeInMilliseconds Time in milliseconds (1000 milliseconds = 1 second)
	 */
	public void forward(long timeInMilliseconds) { 
		try {
			relay1.low();
			relay2.high();
			Thread.sleep(timeInMilliseconds);
			relay2.toggle();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Spins the motor in reverse for a given length of time
	 * @param timeInMilliseconds Time in milliseconds (1000 milliseconds = 1 second)
	 */
	public void reverse(long timeInMilliseconds) { 
		try {
			relay1.high();
			relay2.low();
			Thread.sleep(timeInMilliseconds);
			relay1.toggle();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Spins the motor forward until turned off
	 */
	public void forward() {
		relay1.low();
		relay2.high();
	}
	
	/**
	 * Spins the motor in reverse until turned off
	 */
	public void reverse() {
		relay1.high();
		relay2.low();
	}
	
	/**
	 * 	Turns off motor 
	 * 	by setting both relays to a low state
	 *  thus forcing the same polarity to the
	 *  motor poles
	 */
	public void motorOff() {
		relay1.low();
		relay2.low();
	}
 	
	public GpioController getGpioController() {
		return gpioController;
	}

	public void setGpioController(GpioController gpioController) {
		this.gpioController = gpioController;
	}

	public GpioPinDigitalOutput getRelay1() {
		return relay1;
	}

	public void setRelay1(GpioPinDigitalOutput relay1) {
		this.relay1 = relay1;
	}

	public GpioPinDigitalOutput getRelay2() {
		return relay2;
	}

	public void setRelay2(GpioPinDigitalOutput relay2) {
		this.relay2 = relay2;
	}
	
}
