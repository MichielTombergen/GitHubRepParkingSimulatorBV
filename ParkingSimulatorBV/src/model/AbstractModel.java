package model;

import java.util.*;

import model.Observable;
import view.Observer;
	
	/**
	 * This class is an abstraction for the model. It implements the Observable interface and
	 * therefore provides methods for an observer to registrate as observer and a notify method
	 * for the model to call whenever there is a change. This abstractmodel is part of the
	 * observer design pattern. Note that multiple observers may observe the model.
	 */
	
public abstract class AbstractModel implements Observable {
	private List<Observer> observers; // list of observers
	
	public AbstractModel() {
		this.observers=new ArrayList<Observer>(); // Initialization
	}
	
	/**
	 * Register a observer to the model. Only registered observers will be notified by 
	 * changes via the notifyObservers method that is called by the model.
	 * @param observer the Observer to register as observer
	 */
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}
	
	/**
	 * notifyObservers() notifies all the observers. It is a method
	 * called by the model upon changes. 
	 */
	public void notifyObservers() {
		for(Observer o: this.observers) {
			o.update(this); 
			};
	}
}
