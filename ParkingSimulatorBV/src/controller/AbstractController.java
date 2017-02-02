package controller;

import java.awt.event.ActionListener;
import model.*;

public abstract class AbstractController implements ActionListener{
	protected Model model;
	
	public AbstractController(Model model) {
		this.model = model;
	}
}
