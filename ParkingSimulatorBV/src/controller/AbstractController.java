package controller;

import javax.swing.*;

import model.*;

public abstract class AbstractController extends JPanel {
	private static final long serialVersionUID = 4941730006940737729L;
	protected Model model;
	
	public AbstractController(Model model) {
		this.model=model;
	}
}
