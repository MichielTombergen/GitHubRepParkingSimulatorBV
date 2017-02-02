package view;

import model.*;
import java.awt.*;

/**
 * Deze klasse is 
 */
public class SimulatorView extends AbstractView{
	private static final long serialVersionUID = -2767764579227738552L;
	
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;
    
	public SimulatorView(Model model){
		super(model);
		setSize(200, 200);
		
	}
	
    /**
     * Roep de updateView in carParkView.
     */
    public void updateView() {
        updateView();
    }
    
    /**
     * @return Het aantal verdiepingen.
     */
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

	/**
	 * @return het aantal rijen.
	 */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * @return het aantal plekken.
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /*
     * @return het aantal open plekken.
     */
    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    /**
     * 
     * @param location
     * @return de auto die op die locatie zit.
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Plaats een auto op een bepaalde locatie.
     * @param location
     * @param car
     * @return true of false of er wel of niet een auto kan worden geplaatst.
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    /**
     * Verwijder een auto van een bepaalde locatie.
     * @param location
     * @return
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    /**
     * Wat is de eerste vrije locatie.
     * @return de eerste vrije locatie.
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Welke auto gaat als eerstvolgende weg?
     * @return de auto die weggaat.
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Deze methode zorgt ervoor dat voor elke auto de methode tick() in Car word aangeroepen
     * en de tijd laat tikken. en dus van de minutesleft variabele word afgetrokken.
     */
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }
/**
 * Klopt de gegevenlocatie? Is de waarde groter dan 0 en kleiner dan het aantal plekken?
 * @param location
 * @return true of false
 */
    public boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
}