package tenisz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import tenisz.Server;

public class Server extends Network {
	
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	
	private volatile Thread rec;
			
	public Server(Control control) {
		super(control);
	}
	
	public DataFromClient myCData = new DataFromClient(ctrl);
	public DataFromServer mySData = new DataFromServer(ctrl);
	
	public class ReceiverThread implements Runnable {

		private Socket clientSocket;
		private ObjectInputStream in;
		private ObjectOutputStream out;

		public void run() {
			try {
				System.out.println("Waiting for Client");
				clientSocket = serverSocket.accept();
				System.out.println("Client connected to Server");
				
			} catch (IOException e) {
				System.err.println("Accept failed.");
				e.printStackTrace();
				disconnect();
				return;
			}
			
			try {
				Thread thisThread = Thread.currentThread();
				while (rec == thisThread) {
					if(clientSocket.isConnected()) System.out.println("socket_OK");
					else System.out.println("socket_NOT_OK");
					mySData.updateData();
					mySData.test1 += 1;
					mySData.test2 += 2;
					System.out.println("Updating Server datapack...");
					in = new ObjectInputStream(clientSocket.getInputStream());
					myCData = (DataFromClient) in.readObject();
					System.out.println("Receiving Client datapack...");
					in = null;
					
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					out.writeObject(mySData);
					System.out.println("Sending Server datapack...");
					out = null;
					
					printCuccC();
					printCuccS();
				}
			} catch (Exception ex) {
				System.err.println("Client disconnected!");
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				System.err.println("Or Error while getting streams.");
				disconnect();
				return;
			}finally {
				disconnect();
			}
		}
	}
	
	@Override
	void connect(String host) {
		
		disconnect();
		try {
			serverSocket = new ServerSocket(10007);
			System.out.println("Creating Server socket");
			rec = new Thread(new ReceiverThread());
			System.out.println("Creating Thread");
			rec.start();
			System.out.println("Thread is Running");
			if(clientSocket.isConnected()){
				System.out.println("The Game has started");
				ctrl.joinSuccesfull();
				System.out.println("joinSuccess!!!");
			}
			
		} catch (IOException e) {
				System.err.println("Could not listen on " + host);
				e.printStackTrace();
				disconnect();
		}
		
	}
		
	@Override
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
			if (Thread.currentThread() != null)
				stopThread();;
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE,
					null, ex);
			ex.printStackTrace();
		}
		
	}
	
	public void stopThread(){
		rec = null;
	}

	@Override
	DataFromClient getReceivedDataFromClient() {
		// TODO Auto-generated method stub
		return myCData;
	}

	@Override
	DataFromServer getReceivedDataFromServer() {
		// TODO Auto-generated method stub
		return mySData;
	}

	@Override
	void updateGame() {
		ctrl.getPlayer2().setY(myCData.posY);
		ctrl.setplayer2Up(myCData.ClientUp);
		ctrl.setplayer2Down(myCData.ClientDown);		
	}
	
	void printCuccS(){
		System.out.println("SBallX: " + mySData.ballPosX + 
				" SBallY: " + mySData.ballPosY + 
				" SRackY: " + mySData.posY + 
				" ServScore: " + mySData.serverScore + 
				" ClientScore: " + mySData.clientScore + 
				" ServerUP: " + mySData.serverUp + 
				" ServerDown: " + mySData.serverDown + 
				"Test nums: " + mySData.test1 + " " + mySData.test2);
	}
	
	void printCuccC(){
		System.out.println("CRackY: " + myCData.posY +
				" ClientUP: " + myCData.ClientUp +
				" ClientDown: " + myCData.ClientDown + 
				"Test nums: " + myCData.test1 + " " + myCData.test2);
		
	}

	
		
}