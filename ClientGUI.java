package pro;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class ClientGUI extends JFrame implements Serializable{
	private static final long serialVersionUID = -7478396453816473169L;
	private boolean red = true, yellow = false, green = false;
	private Client client = new Client();
	private TrafficLight TrafficLight = new TrafficLight();

	public ClientGUI() {
		setTitle("traffic lights");
	  	setSize(800, 500);
	  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	setVisible(true);
	}
	
	public void paint(Graphics g){
   	    
   	    g.setColor(Color.black);
   	    g.fillRect(260,50,80,160);
   	    g.setColor(new Color(255, 150, 150)); // light red
   	    g.fillOval(280,60,40,40);
   	    g.setColor(new Color(255, 255, 200)); // light yellow
   	    g.fillOval(280,110,40,40);
   	    g.setColor(new Color(150, 200, 150)); // light green
   	    g.fillOval(280,160,40,40);
	    
   	    while(true) {
   	    	if(!TrafficLight.isWork()) { // send current state of the TrafficLight
   	    		
   	    		g.setColor(new Color(150, 200, 150));  
   	    		g.fillOval(280,160,40,40);
   	    		g.setColor(Color.red);
   	    		g.fillOval(280,60,40,40);
   	    		
	   	    	client.writeToServer(TrafficLight); // sends the Traffic Light to server
	   	    	TrafficLight = (TrafficLight)(client.readFromServer()); // get TrafficLight from server 
	   	    	System.out.println(TrafficLight);
	   	    	if(!TrafficLight.isWork()) {
	   	    		TrafficLight.setLoad(TrafficLight.getLoad() + 1);
		   	    	 try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {e.printStackTrace();}
	   	    	}
   	    	}
   	    	else { // working the TrafficLight
		    	if(red){
		    		
		    		g.setColor(new Color(150, 200, 150));  
		    	    g.fillOval(280,160,40,40);
		    		
		   	        g.setColor(Color.red);
		   	        g.fillOval(280,60,40,40);
		   	        System.out.println("red");
		   	        try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {e.printStackTrace();} // red for 3 seconds
		   	    }
		    	if(yellow) {
		   	   	    g.setColor(new Color(255, 150, 150));     
		     	    g.fillOval(280,60,40,40);
		   	    	
		   	        g.setColor(Color.yellow);
		   	        g.fillOval(280,110,40,40);
		   	        System.out.println("yellow");
		   	        try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {e.printStackTrace();} // yellow for 2 seconds
		   	    }
		
		    	if(green) {
		   	   	    g.setColor(new Color(255, 255, 200));
		     	    g.fillOval(280,110,40,40);
		   	    	
		   	        g.setColor(Color.green);
		   	        g.fillOval(280,160,40,40);
		   	        System.out.println("green");
		   	        try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {e.printStackTrace();} // green for 1 seconds
		   	        TrafficLight.setWork(false);
		   	        TrafficLight.setLoad((int)(Math.random()*100 + 1));
		   	    }
		    	changeColor();
   	    	}
   	    }
	}
	
	void changeColor() { // changes the TrafficLight color
		if(red == true) {
			red = false;
			yellow = true;
		}
		else if(yellow == true) {
			yellow = false;
			green = true;
		}
		else if(green == true) {
			green = false;
			red = true;
		}
	}
	
	
	public static void main(String[] args) {
      	new ClientGUI();
	}
}