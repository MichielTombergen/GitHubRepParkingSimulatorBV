package model;

import model.*;
import view.*;
import controller.Controller;

public class Model extends AbstractModel implements Runnable{

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int tickPause = 100;
    private boolean run;
	
	public Model() {
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		run=false;
	}
	
	@Override
	public void run(){
		run=true;
		while(run) {
	        for (int i = 0; i < 10000; i++) {
	            tick();
	        }
			try {
				Thread.sleep(10);
			} catch (Exception e) {} 
		}
	}
    /**
     * Laat de tijd tikken en updates de views. 
     */
    private void tick() {
    	advanceTime();
    	Controller.handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }

    /**
     * Geeft de tijd aan tot de minuut.
     */
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }
    
    /**
     * Auto's arriveren.
     */
    public void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    /**
     * Laat auto's de parkeergarage ingaan.
     * @param queue
     */
    public void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			simulatorView.getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = simulatorView.getFirstFreeLocation();
            simulatorView.setCarAt(freeLocation, car);
            i++;
        }
    }
    
    /**
     * Laat de eerste car die weggaat betalen als hij dat niet gedaan heeft anders laat je hem de
     * parkeergarage verlaten.
     */
    public void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = simulatorView.getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = simulatorView.getFirstLeavingCar();
        }
    }

    /**
     * Laat auto's betalen.
     */
    public void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    /**
     * Laat auto's weggaan.
     */
    public void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    
}
