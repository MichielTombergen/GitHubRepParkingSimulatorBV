package view;

import model.AbstractModel;

public interface Observer {

	public void update(AbstractModel observable);
}
