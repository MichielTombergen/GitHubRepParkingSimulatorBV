//package Parkeersimulator;
import java.util.LinkedList;
import java.util.Queue;

public class ModelCarQueue {
    private Queue<ModelCar> queue = new LinkedList<>();

    /**
     * Voeg een auto toe aan de rij.
     * @param car
     */
    public boolean addCar(ModelCar car) {
        return queue.add(car);
    }

    /**
     * Verwijder de eerste auto uit de rij.
     */
    public ModelCar removeCar() {
        return queue.poll();
    }

    /**
     * Return de hoeveelheid auto's in de rij.
     * @return de grote van de rij.
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
