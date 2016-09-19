import java.util.ArrayList;
import java.util.HashMap;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;


public class AutonomousSequence {

	private ArrayList<Controller> attachedControllers;
	private String[] buttonSequence = {"4", "5", "6", "7", "8", "9"};
	private HashMap<String, Boolean> buttonSequenceValue= new HashMap<String, Boolean>();
	
	public static void main(String[] args) {
		
		new AutonomousSequence();
	}
	
	public AutonomousSequence() {
		attachedControllers = new ArrayList<>();
		
		searchForControllers();
		
		for (String button : buttonSequence) {
			buttonSequenceValue.put(button, false);
		}
		
		if (!attachedControllers.isEmpty()) {
			listenToControllerData();
		}
	}
	
    /**
     * Search for controllers of type Controller.Type.STICK
     */
    private void searchForControllers() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for(int i = 0; i < controllers.length; i++){
            Controller controller = controllers[i];
         
            if (controller.getType() == Controller.Type.STICK) {
                // Add new controller to the list of all controllers.
                attachedControllers.add(controller);
                
                System.out.println("Found Controller: " + controller.getName());
            }
        }
    }//END searchForControllers
    
    public void listenToControllerData() {
    	boolean sequencePressed = false;
    	Controller controller = attachedControllers.get(0);
    	
    	while (true) {
    		
    		//If controller does not return anything, it has been disconnected.
    		if (!controller.poll()) {
    			System.out.println("Controller has been disconnected!");
    			break;
    		}
    		
	    	Component[] components = controller.getComponents();
	    	
	    	//Cycle through all components of the controller
	    	for (int i = 0 ; i < components.length ; i++) {
	    		Component component = components[i];
	    		Identifier componentIdentifier = component.getIdentifier();
	    		
	    		// Cycle through all components. 
	    		// If the component identifier name contains only numbers, then this is a button.
	    		 if(componentIdentifier.getName().matches("^[0-9]*$")){ 
	                    // Is button pressed?
	    			 	buttonSequenceValue.replace(componentIdentifier.getName(), false);
	                   
	                    if(component.getPollData() != 0.0f) {
	                    	buttonSequenceValue.replace(componentIdentifier.getName(), true);
	                    }	 
	                   
	                    
	    		 }//END if componentIdetifier
	    		 
	    	}//END for components.length
	    	
	    	//Check if each button has been pressed together (check the buttonSequenceValue onject)
	    	if(checkButtonSequence()) {
	    		try {
	    			System.out.println("Sequence Pressed! Begin autonomous mode.");
	                Thread.sleep(1000);
	            } catch (InterruptedException ex) {
	            	
	            }
	    	}
	    	
	    	 try {
	                Thread.sleep(25);
	            } catch (InterruptedException ex) {
	            	
	            }
	    	
    	}
    }//END listenToControllerData()
    
    //Check if all buttons in the specified buttonSequence object have been pressed simultaneously
    public boolean checkButtonSequence() {
    	if (buttonSequenceValue.get(buttonSequence[0]) == true
    			&& buttonSequenceValue.get(buttonSequence[1]) == true
    			&& buttonSequenceValue.get(buttonSequence[2]) == true
    			&& buttonSequenceValue.get(buttonSequence[3]) == true
    			&& buttonSequenceValue.get(buttonSequence[4]) == true
    			&& buttonSequenceValue.get(buttonSequence[5]) == true) {
    		return true;
    	}
    	else return false;
    }
	
}
