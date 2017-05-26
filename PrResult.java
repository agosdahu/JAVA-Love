package tenisz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrResult {

	public JFrame frame;
//	private Control control = new Control();
	private String winner;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrResult window = new PrResult();
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
	public PrResult(Control control) {
		//initialize(control);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(Control control) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblTheWinnerIs = new JLabel("The winner is: ");
		lblTheWinnerIs.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		
		if (control.getScore().getCurrentScorePlayer1() == control.getScore().getScore()) {
			winner = control.getPlayer1().getName();
		}
		else if (control.getScore().getCurrentScorePlayer2() == control.getScore().getScore()){
			winner = control.getPlayer2().getName();
		}
			
		
		JLabel lblNewLabel = new JLabel(winner);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnBackToMenu = new JButton("Back to Menu");
		btnBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				control.showMenu();
				
			}
		});
		
		JButton btnNewButton = new JButton("New game");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				control.newGame();
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblTheWinnerIs)
					.addGap(56)
					.addComponent(lblNewLabel)
					.addContainerGap(98, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(btnBackToMenu)
					.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(64))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTheWinnerIs)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBackToMenu)
						.addComponent(btnNewButton))
					.addGap(25))
		);
		frame.getContentPane().setLayout(groupLayout);

	}

	

}
