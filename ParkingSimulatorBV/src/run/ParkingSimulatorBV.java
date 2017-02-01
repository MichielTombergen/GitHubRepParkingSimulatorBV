package run;

import javax.swing.*;
import controller.*;
import model.*;
import view.*;

public class ParkingSimulatorBV {

	private Model model;
	private Controller controller;
	private AbstractView simulatorview;
	private AbstractView configurationview;
	private JFrame screen;
	
	public ParkingSimulatorBV() {
		
		model = new Model();
		controller = new Controller(model);
		simulatorview = new AbstractView(model);
		configurationview = new AbstractView(model);
		screen = new JFrame("ParkingSimulator");
		screen.setSize(800, 800);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(simulatorview);
		screen.getContentPane().add(configurationview);
		screen.getContentPane().add(controller);
		simulatorview.setBounds(10, 10, 200, 200);
		configurationview.setBounds(230, 10, 200, 200);
		controller.setBounds(0, 210, 450, 50);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ParkingSimulatorBV();
	}

}
