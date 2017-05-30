package tenisz;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PrGamefield /*extends JPanel*/{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_3;
	//public Player plyr1 = new Player("mosomaci", 5, 30, 5); 
	//public Player plyr2 = new Player("vidra", 590, 60, 5); 
//	private Control control = new Control();
	//public Ball ball = new Ball(90, 150, 5, 5, 5);
	
	//private int goal = 15;
	//private int[] cur_score = {5, 7};
	//public Score score = new Score(goal, cur_score);
	//private Graphics g;

	

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	/*public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrGamefield window = new PrGamefield();
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
	public PrGamefield(Control control) {
		initialize(control);
	//	this.score = score; 
	
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	
	
	
	/**
	 * @wbp.parser.entryPoint
	 *
	 */
	public void initialize(Control control) {
		frame = new JFrame();
		frame.setVisible(true);
		Teglalap t = new Teglalap(control);
		t.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				frame.repaint();
			}
			});
		
		/*
		t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				// TODO Auto-generated method stub
				if(evt.getKeyCode() == 38){ //if player 1 is moving up
					control.setplayer1Up(true);
					control.setplayer1Down(false);
				}
				else if(evt.getKeyCode() == 40){ //if player 1 is moving down
					control.setplayer1Up(false);
					control.setplayer1Down(true);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent evt) {
				// TODO Auto-generated method stub
				if(evt.getKeyCode() == 38){ //if player 1 stops moving up
					control.setplayer1Up(false);
					control.setplayer1Down(false);
				}
				else if(evt.getKeyCode() == 40){
					control.setplayer1Up(false);
					control.setplayer1Down(false);
				}
				
			}
		});
		
		*/
		
		 Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			 @Override
		        public void eventDispatched(AWTEvent event) {
				 
				 	boolean isUp = false;
				 	boolean isDown = false;
		            if(event.getID() == KeyEvent.KEY_PRESSED) {
		                KeyEvent kEvent = (KeyEvent) event;
		                isUp = (kEvent.getKeyCode() == KeyEvent.VK_UP);
		                isDown = (kEvent.getKeyCode() == KeyEvent.VK_DOWN);
		                if(isUp) {
				               control.setplayer1Up(true);
				               control.setplayer1Down(false);
				             }
				             else if(isDown){
				              	control.setplayer1Up(false);
								control.setplayer1Down(true);
				             }
		            }
		            
		            if(event.getID() == KeyEvent.KEY_RELEASED) {
		                KeyEvent kEvent = (KeyEvent) event;
		                isUp = !(kEvent.getKeyCode() == KeyEvent.VK_UP);
		                isDown = !(kEvent.getKeyCode() == KeyEvent.VK_DOWN);
		                if(!isUp) {
				               control.setplayer1Up(false);
				             }
				             else if(!isDown){
								control.setplayer1Down(false);
				             }
				             
		            }
		            
		             
		        }
		        

		    }, AWTEvent.KEY_EVENT_MASK);
		 
		 
	    //frame.getContentPane().add(t, BorderLayout.NORTH);
		frame.getContentPane().add(t);
	    //Uto u1 = new Uto(plyr);
	    //frame.getContentPane().add(u1);
	    //frame.add(u1);
	    //frame.getContentPane().add(u1);
	    //frame.getContentPane().add(u1);
	    
	    
	    JButton btnNewButton = new JButton("Load");
	    btnNewButton.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		// mentett játék betöltése
	    		control.loadGame();
	    		frame.setVisible(false);
	    	}
	    });
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    
	    JButton btnSave = new JButton("Save");
	    btnSave.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		// mentés
	    		control.saveGame(control.getScore().getCurrentScorePlayer1(), control.getScore().getCurrentScorePlayer2());
	    	}
	    });
	    
	    JLabel lblNewLabel = new JLabel(control.getPlayer1().getName());
	    
	    JLabel lblNewLabel_1 = new JLabel(control.getPlayer2().getName());
	    
	    /*JLabel*/ lblNewLabel_2 = new JLabel("" + control.getScore().getCurrentScorePlayer1());
	  
	    
	    lblNewLabel_2.setText("" + control.getScore().getCurrentScorePlayer1());     
   
	    
	    lblNewLabel_2.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				lblNewLabel_2.repaint();
			}
			});

	    
	    /*JLabel*/ lblNewLabel_3 = new JLabel("" + control.getScore().getCurrentScorePlayer2());
	    lblNewLabel_3.setText("" + control.getScore().getCurrentScorePlayer2());
	   
	    
	    lblNewLabel_3.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				lblNewLabel_3.repaint();
			}
			});
	    
	    GroupLayout gl_t = new GroupLayout(t);
	    gl_t.setHorizontalGroup(
	    	gl_t.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_t.createSequentialGroup()
	    			.addGap(104)
	    			.addComponent(lblNewLabel)
	    			.addGap(54)
	    			.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	    			.addGap(192)
	    			.addComponent(lblNewLabel_1)
	    			.addGap(56)
	    			.addComponent(lblNewLabel_3))
	    		.addGroup(gl_t.createSequentialGroup()
	    			.addGap(447)
	    			.addComponent(btnSave)
	    			.addGap(18)
	    			.addComponent(btnNewButton))
	    );
	    gl_t.setVerticalGroup(
	    	gl_t.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_t.createSequentialGroup()
	    			.addGap(34)
	    			.addGroup(gl_t.createParallelGroup(Alignment.LEADING)
	    				.addComponent(lblNewLabel)
	    				.addComponent(lblNewLabel_2)
	    				.addComponent(lblNewLabel_1)
	    				.addComponent(lblNewLabel_3))
	    			.addGap(465)
	    			.addGroup(gl_t.createParallelGroup(Alignment.LEADING)
	    				.addComponent(btnSave)
	    				.addComponent(btnNewButton)))
	    );
	    t.setLayout(gl_t);
		frame.setBounds(100, 100, 630, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
		
		
	}
	
	public void labels(Control control){
		lblNewLabel_2.setText("" + control.getScore().getCurrentScorePlayer1());
		lblNewLabel_3.setText("" + control.getScore().getCurrentScorePlayer2());
		
	}
	
	
}