package tenisz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Network {

	private ServerSocket serverSocket = null;

	private Socket clientSocket = new Socket();
	private Thread receiverThread = new Thread();

	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	private DataFromClient receivedData;
	

	
	Server(Control c) {
		super(c);
	}
	
	private class AcceptClient implements Runnable {

		@Override
		public void run() {
			try {
					System.out.println("Waiting for Client1");
					clientSocket = serverSocket.accept();
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					in = new ObjectInputStream(clientSocket.getInputStream());
					out.flush();
					receiverThread = new Thread(new ReceiverThread());
					receiverThread.start();
					sendToClient(new DataFromServer());
					System.out.println("Client connected.");
					if(clientSocket.isConnected()){
						ctrl.joinSuccesfull();
					}
					
					

			} catch (IOException e) {
				System.err.println("Connection failed.");
				disconnect();
				return;
			}
						
		}

	}

	public class ReceiverThread implements Runnable {

		ReceiverThread() {
		}

		public void run() {

			try {
				System.out.println("ReceiverThread started");
				System.out.println("Waiting object stream...");
				DataFromClient received = (DataFromClient) in.readObject();
				receivedData = received;


				while (true) {
					received = (DataFromClient) in.readObject();
					receivedData = received;

				}
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Client disconnected!");
			} finally {
				disconnect();
				}
		}
	}
	
	void connect(String ip) {
		disconnect();
		try {
			serverSocket = new ServerSocket(10007);
			Thread acceptclient = new Thread(new AcceptClient());
			acceptclient.start();

		} catch (IOException e) {
			System.err.println("Could not listen on port: 10007.");
		}
	}


	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (clientSocket != null)
				clientSocket.close();
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	
	void sendToClient(DataFromServer data) {
		// TODO Auto-generated method stub
		if (out == null)
			return;
		System.out.println("Sending string to Client");
		try {
			out.writeObject(data);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
		
	}

	DataFromClient getReceivedDataFromClient() {
		// TODO Auto-generated method stub
		return receivedData;
	}

	DataFromServer getReceivedDataFromServer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	void sendToServer(DataFromClient temp) {
		// TODO Auto-generated method stub
			
	}
	
}

