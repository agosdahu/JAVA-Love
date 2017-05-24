package tenisz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrIP {

	private JFrame frame;
	private JTextField textField;
	public String ip;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrIP window = new PrIP();
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
	/*public PrIP() {
		initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public String initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 165);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblIpAddress = new JLabel("IP address");
		lblIpAddress.setBounds(0, 0, 382, 16);
		lblIpAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblIpAddress);
		
		textField = new JTextField();
		textField.setBounds(57, 29, 269, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//ide jön a mentés
				ip = textField.getText();
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(143, 80, 97, 25);
		frame.getContentPane().add(btnOk);
		return ip;
	}

}
