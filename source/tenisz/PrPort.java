package tenisz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PrPort {

	private JFrame frame;
	private JTextField textField;
	private int port;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrPort window = new PrPort();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public PrPort() {
		//initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public int initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 165);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblIpAddress = new JLabel("Port");
		lblIpAddress.setBounds(0, 0, 272, 16);
		lblIpAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblIpAddress);
		
		textField = new JTextField();
		textField.setBounds(76, 29, 116, 32);
		frame.getContentPane().add(textField);
		textField.setText("1024");
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//ide jön a mentés
				port = Integer.parseInt(textField.getText());
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(86, 74, 97, 25);
		frame.getContentPane().add(btnOk);
		return port;
		
	}

}
