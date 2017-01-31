package run;

import javax.swing.*;
import controller.*;
import model.*;
import view.*;

public class ParkingSimulatorBV {

	private Model model;
	private Controller controller;
	private AbstractView simulatorview;
	private AbstractView configurationinterface;
	private JFrame screen;
	
	public ParkingSimulatorBV() {
		
		model = new Model();
		controller = new Controller(model);
		simulatorview = new AbstractView(model);
		configurationinterface = new AbstractView(model);

	}
	
	public static void main(String[] args) {
		new ParkingSimulatorBV();
	}

}
