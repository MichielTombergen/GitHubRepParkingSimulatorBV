//package Parkeersimulator;

import java.awt.*;

/**
 * Dit is een abstracte class met methodes voor Auto's in de
 * simulatie.
 */
public abstract class ModelCar {

    private ModelLocation location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for objects of class Car
     */
    public ModelCar() {

    	
    }

    /**
     * Return de locatie waar de auto zich bevind.
     * @return de locatie.
     */
    public ModelLocation getLocation() {
        return location;
    }

    /**
     * Stel de locatie van de auto vast.
     * @param location
     */
    public void setLocation(ModelLocation location) {
        this.location = location;
    }

    /**
     * @return het aantal minuten dat de auto in 
     * de parkeergarage blijft staan.
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Stel vast hoeveel minuten de auto in de
     * parkeergarage blijft staan.
     * @param minutesLeft
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * Is de auto aan het betalen?
     * @return true of false
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Stel vast of de auto aan het betalen is.
     * @param isPaying, true of false
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Vraag of de auto moet betalen.
     * @return true of false
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * Stel vast dat een auto moet betalen of niet.
     * @param hasToPay, true of false.
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * Laat de tijd tikken.
     */
    public void tick() {
        minutesLeft--;
    }
    /**
     * Color is een class uit de java.awt package.
     * @return de kleur.
     */
    public abstract Color getColor();
}