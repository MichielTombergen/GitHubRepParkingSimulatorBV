//package Parkeersimulator;


import java.util.Random;
import java.awt.*;

/**
 * Dit is een subklasse van de klasse Car. Deze klasse
 * voegt auto's zonder parking pass toe.
 *
 */
public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;
	
	/**
	 * Constructor voor objecten van class AdHocCar.
	 */
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    /**
     * @return de kleur.
     */
    public Color getColor(){
    	return COLOR;
    }
}
