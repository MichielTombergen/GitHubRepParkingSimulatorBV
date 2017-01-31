package model;
//package Parkeersimulator;

import java.util.Random;
import java.awt.*;

/**
 * Dit is een subklasse van Car 
 * @author Marc Elzinga
 *
 */
public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;
	
	/**
	 * constructor voor objecten van ParkingPassCar.
	 */
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    /**
     * @return de kleur.
     */
    public Color getColor(){
    	return COLOR;
    }
}
