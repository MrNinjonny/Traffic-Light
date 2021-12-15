package pro;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
   	private static ObjectOutputStream toServer; // IO Object Output Stream
   	private static ObjectInputStream fromServer; // IO streams Object Input Stream
 
   	private Socket socket;
 
   	public Client() {
 
          	try {
          		socket = new Socket("localhost", 5454); // Create a socket to connect to the server
          		
          		toServer = new ObjectOutputStream(socket.getOutputStream()); // Create an output stream to send data to server
          		fromServer = new ObjectInputStream(socket.getInputStream()); // Create an input stream to receive data from server
 
          	} catch (IOException e) {
                 	e.printStackTrace();
          	}
   	}
 
   	public void writeToServer(TrafficLight i) { // writes to the server
          	try {
                 	toServer.writeObject(i);
                 	toServer.reset();
          	} catch (IOException e) {
                 	e.printStackTrace();
          	}
   	}
   	
   	public TrafficLight readFromServer() { // reading from server
          	try {
                 	return (TrafficLight) fromServer.readObject();
          	} catch (ClassNotFoundException e) {
                 	e.printStackTrace();
          	} catch (IOException e) {
                 	e.printStackTrace();
          	}
			return null;
   	}

}
