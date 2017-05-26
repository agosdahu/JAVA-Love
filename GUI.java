package tenisz;


import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame{
	
	/**
	 * 600*400 gamefield
	 */
	private static final long serialVersionUID = 1L;
	//private Control control;
	PrGamefield prgamefield;
	private Prmenu prmenu;
	private Pr pr;
	private PrIP prip;
	private PrPort prport;
	private String ip;
	private int port;

	public void showMenu(Control control) {
		// TODO Auto-generated method stub
		/* frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	//	control = new Control();
		
		JButton btnStartGame = new JButton("Start game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				control.startServer();
			}
		});
		
		JButton btnJoinGame = new JButton("Join game");
		btnJoinGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.startClient();
			}
		});
		frame.getContentPane().add(btnStartGame, BorderLayout.NORTH);
		frame.getContentPane().add(btnJoinGame, BorderLayout.SOUTH); 
		*/
		
	//	control = new Control();
		prmenu = new Prmenu(control);
	//	prmenu.initialize(control);
		
	}
	
	public void showOptions(Control control) {
		// TODO Auto-generated method stub
		pr = new Pr(control);
	//	pr.initialize(control);
		//prmenu.frame.setVisible(false); // ez nem azt csinálja, mint amit akartam
		
	}
	public void showGameField(Control control) {
		prgamefield = new PrGamefield(control);
		control.startGame();
		

	}

	public void showResult(Control control) {
		new PrResult(control);
	}

	public String getIPaddress() {
		// TODO Auto-generated method stub
		prip = new PrIP();
		ip = prip.initialize();
		return ip;
	}

	public int getPort() {
		// TODO Auto-generated method stub
		prport = new PrPort();
		port = prport.initialize();
		return port;
	}
	
	 public void refreshgui(Control control) {
		 prgamefield.frame.repaint();
		 prgamefield.labels(control);
		 //prgamefield.lblNewLabel_2.repaint();
		 //prgamefield.lblNewLabel_3.repaint();
		 }

	
}
