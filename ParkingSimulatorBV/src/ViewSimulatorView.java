//package Parkeersimulator;

import javax.swing.*;
import java.awt.*;

/**
 * Deze klasse is een subklasse van JFrame
 */
public class ViewSimulatorView extends JFrame {
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private ModelCar[][][] cars;

    /**
     * Constructor voor objecten van SimulatorView.
     * @param numberOfFloors
     * @param numberOfRows
     * @param numberOfPlaces
     */
    public ViewSimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new ModelCar[numberOfFloors][numberOfRows][numberOfPlaces];
        
        carParkView = new CarParkView();

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);

        updateView();
    }

    /**
     * Roep de updateView in carParkView.
     */
    public void updateView() {
        carParkView.updateView();
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
    public ModelCar getCarAt(ModelLocation location) {
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
    public boolean setCarAt(ModelLocation location, ModelCar car) {
        if (!locationIsValid(location)) {
            return false;
        }
        ModelCar oldCar = getCarAt(location);
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
    public ModelCar removeCarAt(ModelLocation location) {
        if (!locationIsValid(location)) {
            return null;
        }
        ModelCar car = getCarAt(location);
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
    public ModelLocation getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    ModelLocation location = new ModelLocation(floor, row, place);
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
    public ModelCar getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    ModelLocation location = new ModelLocation(floor, row, place);
                    ModelCar car = getCarAt(location);
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
     * en de tijd laat tikken. en dus de minutesleft variabele word afgetrokken.
     */
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    ModelLocation location = new ModelLocation(floor, row, place);
                    ModelCar car = getCarAt(location);
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
    private boolean locationIsValid(ModelLocation location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    /**
     * Deze class is een subclass van de class Jpanel.
     * @author Marc Elzinga
     *
     */
    private class CarParkView extends JPanel {
        
        private Dimension size;
        private Image carParkImage;    
    
        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
            size = new Dimension(0, 0);
        }
    
        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    
        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }
    
            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }
    
        /**
         * Update de view van de simulatie. Welke hokken wit of de kleur die er bij past.
         */
        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        ModelLocation location = new ModelLocation(floor, row, place);
                        ModelCar car = getCarAt(location);
                        Color color = car == null ? Color.white : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }
    
        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, ModelLocation location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }

}
