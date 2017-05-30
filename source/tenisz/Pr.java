package tenisz;

// import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Pr {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtGoal;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Control c = new Control();
				try {
					Pr window = new Pr(c);
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
	public Pr(Control control) {
		initialize(control);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param control 
	 * @wbp.parser.entryPoint
	 */
	public void initialize(Control control) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JLabel lblOptions = new JLabel("Options");
		
		JLabel lblName = new JLabel("Name:");
		
		txtName = new JTextField();
		txtName.setText(control.getPlayer1().getName());
		txtName.setColumns(10);
		
		JLabel lblGoal = new JLabel("Goal: ");
		
		txtGoal = new JTextField();
		txtGoal.setText(Integer.toString(control.getScore().getScore()));
		txtGoal.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.setName(txtName.getText());
				control.setScore(Integer.parseInt(txtGoal.getText()));
				control.showMenu();
				frame.setVisible(false);
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblGoal))
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtGoal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOptions))
					.addContainerGap(173, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(297, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(38))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOptions)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGoal)
						.addComponent(txtGoal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addComponent(btnSave)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}


}
