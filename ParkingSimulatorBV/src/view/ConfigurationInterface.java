package view;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JProgressBar;

public class ConfigurationInterface {

	protected static final String ParentComponent = null;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurationInterface window = new ConfigurationInterface();
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
	public ConfigurationInterface() {
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
