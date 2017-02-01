package view;

import java.awt.*;
import javax.swing.*;
import model.*;

public class ConfigurationView extends AbstractView {

	protected static final String ParentComponent = null;
	private JFrame frame;

	public ConfigurationView(Model model){
		super(model);
		setSize(200, 200);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurationView window = new ConfigurationView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfigurationView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Simulator mysimulator = new Simulator();
				mysimulator.run();
			}
		});
		btnSubmit.setBackground(Color.RED);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBounds(124, 180, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JTextArea lblMessage2 = new JTextArea();
		lblMessage2.setText("Simulator opstarten?");
		lblMessage2.setBounds(140, 30, 172, 46);
		frame.getContentPane().add(lblMessage2);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnHello = new JMenu("hello");
		menuBar.add(mnHello);
		
		JMenuItem mntmGoFuckYourself = new JMenuItem("go fuck yourself");
		mnHello.add(mntmGoFuckYourself);
		
		JMenuItem mntmGoKillYourself = new JMenuItem("go kill yourself");
		mnHello.add(mntmGoKillYourself);
	}
}
