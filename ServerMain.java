package pro;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerMain {

    public static void main(String[] args) {
    	Vector<ServerThread> threadVector = new Vector<ServerThread>(); // vector to add all the clients threads
    	
        try
        {
        	ServerSocket serversocket = new ServerSocket(5454); // creating the socket
            while(true) // accepting clients and creating a thread for each of them
            {
                Socket socket = serversocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadVector);
                threadVector.add(serverThread); 
                serverThread.start(); // starting the thread

                

            }
        } catch (Exception e) {System.out.println(e);}
    }
}