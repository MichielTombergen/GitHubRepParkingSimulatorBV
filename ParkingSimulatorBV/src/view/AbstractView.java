package view;

import javax.swing.*;
import model.*;

/**public abstract class AbstractView extends JPanel implements Observer {
	private static final long serialVersionUID = -2767764579227738552L;
	private static Observable observable;

	public static Observable getObservable(){
		return observable;
	}
	
	public static void setObservable(Observable observable) {
		AbstractView.observable = observable;
	}
	
	public AbstractView(Observable observable){
		observe(observable);
	}
	
	public void observe(Observable observable){
		setObservable(observable);
		getObservable().registerObserver(this);
	}
	
	public Model getModel() {
		return (Model) getObservable();
	}
	
	public void updateView() {
		repaint();
	}
	
	public void update(AbstractModel observable){
		updateView();
	}
}
*/