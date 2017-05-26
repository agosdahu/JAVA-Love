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
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
		
	public Server(Control control) {
		super(control);
	}
	
	public MySocket mySocket = new MySocket(ctrl);
	
	public class ReceiverThread implements Runnable {

		public void run() {
			try {
				System.out.println("Waiting for Client");
				clientSocket = serverSocket.accept();
				System.out.println("Client connected.");
				mySocket.playerC = ctrl.getPlayer2();
				mySocket.playerS = ctrl.getPlayer1();			
				mySocket.playerC.setType("Client");
				mySocket.playerS.setType("Server");
				ctrl.setPlayer1(mySocket.playerS);
				ctrl.setPlayer2(mySocket.playerC);
				ctrl.joinSuccesfull(ctrl.getPlayer2());
			} catch (IOException e) {
				System.err.println("Accept failed.");
				disconnect();
				return;
			}

			try {
				out = new ObjectOutputStream(clientSocket.getOutputStream());
				in = new ObjectInputStream(clientSocket.getInputStream());
				out.flush();											// memória szemét kiflushására
			} catch (IOException e) {
				System.err.println("Error while getting streams.");
				disconnect();
				return;
			}

			try {
				while (true) {
					mySocket = (MySocket) in.readObject();
					System.out.println("Klienstõl elvettem az adatot");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Client disconnected!");
			} finally {
				disconnect();
			}
		}
	}
	
	@Override
	void connect(String host) {
		
		disconnect();
		try {
			serverSocket = new ServerSocket(6003);

			Thread rec = new Thread(new ReceiverThread());
			rec.start();
		} catch (IOException e) {
				System.err.println("Could not listen on " + host);
		}
		
	}
	
	@Override
	public int getRacketPos() {
		// TODO Auto-generated method stub
		return 0;
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
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		
	}

	@Override
	public void sendRacketPos(int pos) {
		mySocket.setRposS(pos);
		
		if (out == null)
			return;
		System.out.println("Sending DATA to Server");
		try {
			out.writeObject(mySocket);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
		
	}

	@Override
	public void sendData(int ball_x, int ball_y, int player1Score, int player2Score) {
		mySocket.setBallX(ctrl);
		mySocket.setBallY(ctrl);
		mySocket.setP1score(getScore1());
		mySocket.setP2score(getScore2());
		
		if (out == null)
			return;
		System.out.println("Sending DATA to Server");
		try {
			out.writeObject(mySocket);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScore1() {
		return ctrl.getScore().getCurrentScorePlayer2();
	}

	@Override
	public int getScore2() {
		return ctrl.getScore().getCurrentScorePlayer1();
	}

	
		
}