package br.edu.cefsa.ftt.ec;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;

// Refer�ncias:
//
// https://github.com/jinput/jinput
// http://www.bartneck.de/2017/08/14/tutorial-on-how-to-install-and-setup-jinput-on-mac-os-x-using-eclipse/
// http://www.gametutorial.net/article/JInput-Joystick-Test
// 

public class Joystick {

	public static void main(String[] args) {

		System.out.println("Out of controll...");
		
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		System.out.println(controllers[11]);
		
		for (int i = 0; i < controllers.length; i++) {
			System.out.println(i + " "+ controllers[i]);
		} //for
	
		 Controller controller = controllers[controllers.length-1]; //Pega o �ltimo controle
		 
		 System.out.println(controller.getName() + " - " + controller.getType().toString() + " type");
		 
		 Component[] components = controller.getComponents();
		 
		 for (int i = 0; i < components.length; i++)
			 System.out.println("Componente do Joystick: " + components[i]);
		 
		 Boolean loop = true;
		 while(loop) {
			 
			 if (!controller.poll()) {
				 System.out.println("Fui...");
				 loop = false;
			 } else {
				 
			/////////////////////////////////////////////////////////////////////////	 
		    //The magic is here...
				 
				 
		            for(int i=0; i < components.length; i++) {
		                Component component = components[i];
		                Identifier componentIdentifier = component.getIdentifier();
		              
		                // Buttons
		                //if(component.getName().contains("Button")){ // If the language is not english, this won't work.
		                if(componentIdentifier.getName().matches("^[0-9]*$")) { // If the component identifier name contains only numbers, then this is a button.
		                    
		                    if (component.getPollData() != 0.0f) {                    
		                    	String compName = component.getIdentifier().toString();
		                    	System.out.println(compName);
		                    } //if
		                    
		                    // We know that this component was button so we can skip to next component.
		                    continue;
		                } //if - buttons
		                
		                // Hat switch
		                if(componentIdentifier == Component.Identifier.Axis.POV){
		                    float hatSwitchPosition = component.getPollData();
		                    System.out.println(hatSwitchPosition);
		                    
		                    // We know that this component was hat switch so we can skip to next component.
		                    continue;
		                } // if hat
		             
		                // Axes
		                if(component.isAnalog()) {
		                    float axisValue = component.getPollData();
		                    //System.out.println(axisValue); //calcular %...
		                    
		                    // X axis
		                    if(componentIdentifier == Component.Identifier.Axis.X) {
		                        System.out.println("x: " + axisValue);
		                        continue; // Go to next component.
		                    }
		                    // Y axis
		                    if(componentIdentifier == Component.Identifier.Axis.Y) {
		                    	System.out.println("y: " + axisValue);
		                        continue; // Go to next component.
		                    }
		                    // Z axis
		                    if(componentIdentifier == Component.Identifier.Axis.Z) {
		                    	System.out.println("z: " + axisValue);
		                        continue; // Go to next component.
		                    }
		                    		                    		                    
		                } //if - axes
		                
		            }	//for			 
		 
				 
			/////////////////////////////////////////////////////////////////////////	 
			 } //if
				 
             try {
				Thread.sleep(30);
			 } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 } //try
             
		 } //while - infinity loop
			 
	}	//main

} //Joystick
