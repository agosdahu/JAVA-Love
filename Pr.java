package tenisz;

// import java.awt.EventQueue;

import javax.swing.JFrame;
//import javax.swing.JPopupMenu;
//import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import javax.swing.JMenuItem;
//import javax.swing.JMenuBar;
import javax.swing.JTextField;
//import java.awt.BorderLayout;
import javax.swing.JLabel;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import java.awt.FlowLayout;
//import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
//import javax.swing.JEditorPane;
//import java.awt.TextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Pr {

	private JFrame frame;
	private JTextField txtName;
	private JButton btnSave;
	public String name;
	public int goal;
	public Player player;
	private Control control = new Control();

	/**
	 * Launch the application.
	 */
	/* public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pr window = new Pr();
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
	/*public Pr() {
		initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JLabel lblOptions = new JLabel("Options");
		
		JLabel lblName = new JLabel("Name:");
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setColumns(10);
		
		JLabel lblGoal = new JLabel("Goal: ");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(15, 5, 30, 1));
		
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// itt kell elmenteni a beírt dolgokat
				 name = txtName.getText();
				 control.setName(name);
				 //player.setName(name);
				 //player.name = player.getName();
				 
				 goal = (int)spinner.getModel().getValue();
				 control.setScore(goal);
				 
				 frame.setVisible(false);
				 
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblGoal))
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOptions)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(173, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(333, Short.MAX_VALUE)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGoal)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(63)
					.addComponent(btnSave)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
