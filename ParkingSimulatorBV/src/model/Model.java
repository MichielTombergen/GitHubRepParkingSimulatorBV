package model;

import view.*;
import java.util.*;

public class Model extends AbstractModel implements Runnable{

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int tickPause = 100;
    
	public static final String AD_HOC = "1";
	public static final String PASS = "2";
	public static final String ABO = "3";
    
    private boolean run;
	
    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 75; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour
    int weekDayAboArrivals= 50;// average number of arriving cars per hour
    int weekendAboArrivals= 10;//average number of arriving cars per hour
    
    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
    
	public CarQueue entranceCarQueue;
    public CarQueue entrancePassQueue;
    public CarQueue paymentCarQueue;
    public CarQueue exitCarQueue;
    public SimulatorView simulatorView;
    
	public Model() {

        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
		
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
    public void tick() {
    	advanceTime();
    	handleExit();
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
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, ABO);  
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
  
    /**
     * Bereken het aantal auto's per uur.
     * @param weekDay
     * @param weekend
     * @return het aantal auto's per uur.
     */
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
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
    
	
    /**
     * Laat auto's aankomen en binnengaan.
     */
    public void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
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
     * 
     */
    public void updateViews(){
    	simulatorView.tick();
    	// Update the car park view.
    	simulatorView.updateView();
}

    /**
     * Laat auto's weggaan en betalen.
     */
    public void handleExit(){
    	carsReadyToLeave();
    	carsPaying();
    	carsLeaving();
}
    
}