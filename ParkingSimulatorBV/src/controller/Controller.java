package controller;

import javax.swing.*;
import model.*;
import java.awt.event.*;
import view.*;

public class Controller extends AbstractController implements ActionListener {
	
	private static final long serialVersionUID = -7413164724294460746L;
	
	private JButton mineen;
	private JButton pluseen;
	private JButton start;
	private JButton stop;
	
	public static final String AD_HOC = "1";
	public static final String PASS = "2";
	
	public CarQueue entranceCarQueue;
    public CarQueue entrancePassQueue;
    public CarQueue paymentCarQueue;
    public CarQueue exitCarQueue;
    public SimulatorView simulatorView;
	
	public Controller(Model model) {
		super(model);
		
		setSize(450, 50);
		start=new JButton("Start");
		start.addActionListener(this);
		stop=new JButton("Stop");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(start);
		add(stop);
		start.setBounds(229, 10, 70, 30);
		stop.setBounds(319, 10, 70, 30);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==start) {
			model.start();
		}
		
		if (e.getSource()==stop) {
			model.stop();
			
		}
		
	}
	
    /**
     * Laat auto's aankomen en binnengaan.
     */
    public void handleEntrance(){
    	model.carsArriving();
    	model.carsEntering(entrancePassQueue);
    	model.carsEntering(entranceCarQueue);  	
}

    /**
     * Laat auto's weggaan en betalen.
     */
    public void handleExit(){
    	model.carsReadyToLeave();
    	model.carsPaying();
    	model.carsLeaving();
}

    /**
     * 
     */
    public void updateViews(){
    	simulatorView.tick();
    	// Update the car park view.
    	simulatorView.updateView();
}
	
    /**
     * Verwijder een auto van zijn locatie en laat hem in de rij van de uitgang staan. 
     * @param car
     */
    public void carLeavesSpot(Car car){
    	simulatorView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
   
    /**
     * Voeg aankomende auto's toe.
     * @param numberOfCars
     * @param type
     */
    public void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    
    }
    
}