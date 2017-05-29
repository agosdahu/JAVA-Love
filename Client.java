package tenisz;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client extends Network {

	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	private Thread receiverThread = new Thread();
	private DataFromServer receivedData;

	Client(Control c) {
		super(c);
	}

	
	private class AcceptServer implements Runnable {

		@Override
		public void run() {
			try {
					
					out = new ObjectOutputStream(socket.getOutputStream());
					in = new ObjectInputStream(socket.getInputStream());
					out.flush();
					receiverThread = new Thread(new ReceiverThread());
					receiverThread.start();
					sendToClient(new DataFromServer());
					System.out.println("Client connected.");
					if(socket.isConnected()){
						ctrl.joinSuccesfull();
					}
					
					

			} catch (IOException e) {
				System.err.println("Connection failed.");
				disconnect();
				return;
			}
						
		}

	}
	
	
	private class ReceiverThread implements Runnable {

		public void run() {
			System.out.println("Waiting object stream...");
			try {
				DataFromServer received = (DataFromServer) in.readObject();
				receivedData = received;


				while (true) {
					received = (DataFromServer) in.readObject();
					receivedData = received;
					System.out.println(receivedData.ballPosX);
					System.out.println(receivedData.ballPosY);
					System.out.println(receivedData.posY);
					System.out.println(receivedData.player1Score);
					System.out.println(receivedData.player2Score);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Server disconnected!");
			} finally {
				disconnect();
			}
		}
	}

	@Override
	void connect(String ip) {
		disconnect();
		try {
			socket = new Socket(ip, 10007);

			//out = new ObjectOutputStream(socket.getOutputStream());
		//	in = new ObjectInputStream(socket.getInputStream());
			//out.flush();

			Thread rec = new Thread(new AcceptServer());
			rec.start();
			if(socket.isConnected()){
				ctrl.joinSuccesfull();
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection. ");
			JOptionPane.showMessageDialog(null, "Cannot connect to server!");
		}
	}
	

	@Override
	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (socket != null)
				socket.close();
		} catch (IOException ex) {
			System.err.println("Error while closing conn.");
		}
	}

	

	@Override
	void sendToServer(DataFromClient data) {
		// TODO Auto-generated method stub
		if (out == null)
			return;
		System.out.println("Sending data to Server");
		try {
			
			out.writeObject(data);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
	}

	@Override
	DataFromServer getReceivedDataFromServer() {
		// TODO Auto-generated method stub
		return receivedData;
	}
	
	@Override
	DataFromClient getReceivedDataFromClient() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	void sendToClient(DataFromServer temp) {
		// TODO Auto-generated method stub
	}
}
