package view;

import java.awt.FlowLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.Controller;

/**
 * The MainWindow class sets-up the GUI elements for the application.
 * Two panes display the current state of the model, and some control-
 * elements to manipulate the model are displayed.
 * @author MichielTombergen
 *
 */
public class MainWindow {
	private JFrame mainwindow;
	private JPanel buttonpane;
	
	public MainWindow(AbstractView leftpane, AbstractView rightpane, Controller controller){
		
		mainwindow=new JFrame("ParkingSimulator 2k17");
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//URL iconURL = getClass().getResource("../../img/icon.png"); 
		//ImageIcon icon = new ImageIcon(iconURL);
		//mainwindow.setIconImage(icon.getImage());
		
		
		mainwindow.setSize(1000, 400);
		mainwindow.setResizable(false);
		mainwindow.setLayout(null);
		mainwindow.getContentPane().add(leftpane);
		mainwindow.getContentPane().add(rightpane);
		
		leftpane.setBounds(10, 10, 200, 200);
		rightpane.setBounds(230, 10, 200, 200);
				
		buttonpane = new JPanel();
		buttonpane.setBounds(0, 210, 450, 50);
		buttonpane.setSize(450, 50);
		mainwindow.getContentPane().add(buttonpane);
		
	    JButton start;
		JButton stop;
		
		start=new JButton("Start");
		start.addActionListener(controller);
		stop=new JButton("Stop");
		stop.addActionListener(controller);		
		
		buttonpane.setLayout(new FlowLayout());

		buttonpane.add(start);
		buttonpane.add(stop);
		
		mainwindow.setVisible(true);
	
	}
	
}