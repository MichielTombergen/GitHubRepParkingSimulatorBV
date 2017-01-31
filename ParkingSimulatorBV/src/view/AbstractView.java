package view;

import javax.swing.*;

import model.*;

public abstract class AbstractView extends JPanel implements Observer {
	private static final long serialVersionUID = -2767764579227738552L;
	private AbstractModel observable;

	public AbstractView(AbstractModel observable) {
		observe(observable);
	}
	
	public void observe(AbstractModel observable){
		this.observable = observable;
		observable.registerObserver(this);
	}
	
	public Model getModel() {
		return (Model) observable;
	}
	
	public void updateView() {
		repaint();
	}
	
	public void update(AbstractModel observable){
		updateView();
	}
}