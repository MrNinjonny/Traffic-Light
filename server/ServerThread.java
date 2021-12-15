package pro;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;



public class ServerThread extends Thread{
	private Socket socket;
	private Vector<ServerThread> threadVector;
	private ObjectInputStream inputFromClient; // IO streams Object Input Stream
   	private ObjectOutputStream outputToClient; // IO Object Output Stream
   	private TrafficLight TrafficLight;

	
	public ServerThread(Socket socket, Vector<ServerThread> threadVector) { // constructing the ServerThread
		super();
		this.socket = socket;
		this.threadVector = threadVector;
	}
	public void run() {
		 try {
			 outputToClient = new ObjectOutputStream(socket.getOutputStream()); // Create an output stream to send data to server
			 inputFromClient = new ObjectInputStream(socket.getInputStream()); // Create an input stream to receive data from server
			 
			 while(true) {
				 TrafficLight = (TrafficLight)inputFromClient.readObject(); // gets TrafficLight from client
				 System.out.println("server got Traffic Light:" + TrafficLight);
				 
				 if(!someoneworking())
					 findmaxload();
				 outputToClient.writeObject(TrafficLight);
				 System.out.println("server sent Traffic Light:" + TrafficLight); // sends TrafficLight to server
				 outputToClient.reset();
			 }
		 } catch (Exception e) {System.out.println(e);}
	}
	
	public synchronized void findmaxload() { // find TrafficLight with max load
   		int max = 0;
   		int id = 0;
   		for(int i = 0;i < threadVector.size();i++) {
   			if(threadVector.get(i).getTrafficLight().getLoad() > max) {
   				max = threadVector.get(i).getTrafficLight().getLoad();
   				threadVector.get(id).getTrafficLight().setWork(false);
   				id = i;
   				threadVector.get(id).getTrafficLight().setWork(true);
   			}
   		}
   	}
	public synchronized boolean someoneworking() { // checking if other TrafficLights are working 
		for(int i = 0;i < threadVector.size();i++) {
			if(threadVector.get(i).getTrafficLight().isWork())
				return true;
		}
		return false;
	}
	public TrafficLight getTrafficLight() {
		return TrafficLight;
	}
}
