package model;

import java.util.*;

import view.Observer;
	
	/**
	 * This class is an abstraction for the model. It implements the Observable interface and
	 * therefore provides methods for an observer to registrate as observer and a notify method
	 * for the model to call whenever there is a change. This abstractmodel is part of the
	 * observer design pattern. Note that multiple observers may observe the model.
	 */
	
public abstract class AbstractModel implements Observable {

	private List<Observer> observers;
	
	public AbstractModel() {
		this.observers=new ArrayList<Observer>(); // Maakt een Observer aan.
	}
}
