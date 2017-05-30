package tenisz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client extends Network {
	
	private Socket socket = null;
	
	private volatile Thread rec;
		
	public Client(Control control) {
		super(control);
	}
	
	public DataFromClient myCData = new DataFromClient(ctrl);
	public DataFromServer mySData = new DataFromServer(ctrl);
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;

	public class ReceiverThread implements Runnable {
		
		private ObjectOutputStream out;
		private ObjectInputStream in;

		public void run() {
			try {
				Thread thisThread = Thread.currentThread();
				while (rec == thisThread) {
						if(socket.isConnected()) System.out.println("socket_OK");
						else System.out.println("socket_NOT_OK");
						myCData.updateData();
						myCData.test1 += 1;
						myCData.test2 += 2;
						System.out.println("Updating Client datapack...");
						out = new ObjectOutputStream(socket.getOutputStream());
						out.writeObject(myCData);
						System.out.println("Sending Client datapack");
						out = null;
						
						System.out.println("Sending Client datapack...");
						in = new ObjectInputStream(socket.getInputStream());
						mySData = (DataFromServer) in.readObject();
						System.out.println("Receiving Server datapack...");
						in = null;
						
						printCuccC();
						printCuccS();
					}
			} catch (Exception ex) {
				System.out.println("Exception get message: ");
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				System.err.println("Server disconnected! " + ex);
				disconnect();
			} finally {
				disconnect();
			}
		}
	}
	
	@Override
	void connect (String host) {
		disconnect();
		
		try {
			socket = new Socket(host, 10007);
			System.out.println("Connecting to Server...");
			rec = new Thread(new ReceiverThread());
			System.out.println("Thread start...");
			rec.start();
			System.out.println("Thread Running...");
			if(socket.isConnected()){
				System.out.println("Connected to Server...");
				System.out.println("The Game has started");
				ctrl.joinSuccesfull();
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
			e.printStackTrace();
			disconnect();
		} catch (IOException e) {
			disconnect();
			System.err.println(e.getStackTrace());
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
			if (Thread.currentThread() != null)
				stopThread();;
		} catch (IOException ex) {
			System.err.println("Error while closing conn.");
		}
		
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
	public void updateGame() {
		ctrl.getPlayer2().setY(mySData.posY);
		ctrl.getBall().setX(mySData.ballPosX);
		ctrl.getBall().setY(mySData.ballPosY);
		ctrl.getScore().setCurrentScorePlayer1(mySData.clientScore);
		ctrl.getScore().setCurrentScorePlayer2(mySData.serverScore);
		
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
	
	public void stopThread(){
		rec = null;
	}

	

}