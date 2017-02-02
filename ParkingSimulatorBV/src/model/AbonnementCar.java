package model;

import java.util.Random;
import java.awt.*;

/**
 * Dit is een subklasse van de klasse Car. Deze klasse
 * voegt auto's zonder parking pass toe.
 *
 */
public class AbonnementCar extends Car {
	private static final Color COLOR=Color.pink;
	
	/**
	 * Constructor voor objecten van class AdHocCar.
	 */
    public AbonnementCar() {
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
