package tenisz;

//import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Prmenu {

	public JFrame frame;
	protected JTextField target;
//	private static Control control = new Control();
	private GUI gui = new GUI();

	/**
	 * Launch the application.
	 */
	/*
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prmenu window = new Prmenu(control);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 */
	/**
	 * Create the application.
	 */
	 public Prmenu(Control control) {
		initialize(control);
	}

	 
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	/*private void initialize() { */
	public void initialize(Control control) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.startServer();
			}
		});


		target = new JTextField();
		target.setText(control.target);
		target.setColumns(10);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.target=target.getText();
				control.startClient();
			}
		});
		
		JButton btnOptions = new JButton("Options");
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gui.showOptions(control);
				frame.setVisible(false);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(165)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(target, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOptions, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnJoinGame, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnStartGame, Alignment.LEADING))
					.addContainerGap(170, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addComponent(btnOptions)
					.addGap(35)
					.addComponent(btnStartGame)
					.addGap(30)
					.addComponent(btnJoinGame)
					.addGap(30)
					.addComponent(target)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
}
