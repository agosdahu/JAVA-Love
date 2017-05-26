package tenisz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client extends Network {
	
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	public Client(Control control) {
		super(control);
	}
	
	public MySocket mySocket = new MySocket(ctrl);

	public class ReceiverThread implements Runnable {
		
		public void run() {
			System.out.println("Waiting for DATA");
			try {
				while (true) {
						mySocket = (MySocket) in.readObject(); //itt van vmi wait, tehát nem pörgessük szarrá a procit ;)
						System.out.println("Szervertõl elvettem az adatot");
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
	void connect (String host) {
		disconnect();
		try {
			socket = new Socket(host, 6003);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.flush();
						
			Thread rec = new Thread(new ReceiverThread());
			rec.start();										// itt indul a szál!
			System.out.println("Elindult a szál");
			mySocket.playerC = ctrl.getPlayer1();
			mySocket.playerS = ctrl.getPlayer2();			
			mySocket.playerC.setType("Client");
			mySocket.playerS.setType("Server");
			ctrl.setPlayer1(mySocket.playerC);
			ctrl.joinSuccesfull(ctrl.getPlayer2());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection. ");
			JOptionPane.showMessageDialog(null, "Cannot connect to server!");
		}
		
	}
	
	@Override
	public int getRacketPos() {
		int serverRack = mySocket.getRposS();
		return serverRack;
	}
	
	@Override
	public int getScore1() {
		return mySocket.getP1score();
	}
	
	@Override
	public int getScore2() {
		// TODO Auto-generated method stub
		return mySocket.getP2score();
	}
	
	@Override
	public void sendData() {
		mySocket.setRposC(ctrl.getPlayer1().getY());
		
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
	public void sendRacketPos(int pos) {
		mySocket.setRposC(pos);
		
	}
	
	@Override
	public int getX() {
		return mySocket.getBallX();
	}
	
	@Override
	public int getY() {
		return mySocket.getBallY();
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
	public void sendData(int ball_x, int ball_y, int player1Score, int player2Score) {
		// TODO Auto-generated method stub
		
	}

	

}