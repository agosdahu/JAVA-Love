package tenisz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import tenisz.Client.ReceiverThread;

public class Client extends Network {
	
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;

	public Client(Control control) {
		super(control);
	}
	
	public class ReceiverThread implements Runnable {

		public void run() {
			System.out.println("Waiting for DATA");
			try {
				while (true) {
					
					//Point received = (Point) in.readObject(); //itt van vmi wait, tehát nem pörgessük szarrá a procit ;)
					//ctrl.clickReceived(received);
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
	void connect(String ip, int port) {
		disconnect();
		try {
			socket = new Socket(ip, 6003);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.flush();

			Thread rec = new Thread(new ReceiverThread());
			rec.start();										// itt indul a szál!
			System.out.println("Elindult a szál");
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

}