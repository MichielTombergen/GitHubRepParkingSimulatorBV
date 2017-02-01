package controller;

import javax.swing.*;
import model.*;
import java.awt.event.*;

public class Controller extends AbstractController implements ActionListener {
	private static final long serialVersionUID = -7413164724294460746L;
	private JButton mineen;
	private JButton pluseen;
	private JButton start;
	private JButton stop;
	
	public Controller(Model model) {
		super(model);
		
		setSize(450, 50);
		mineen=new JButton("-1");
		mineen.addActionListener(this);
		pluseen=new JButton("+1");
		pluseen.addActionListener(this);
		start=new JButton("Start");
		start.addActionListener(this);
		stop=new JButton("Stop");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(mineen);
		add(pluseen);
		add(start);
		add(stop);
		mineen.setBounds(50, 10, 70, 30);
		pluseen.setBounds(140, 10, 70, 30);
		start.setBounds(229, 10, 70, 30);
		stop.setBounds(319, 10, 70, 30);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==start) {
			model.start();
		}
		
		if (e.getSource()==stop) {
			model.stop();
			
		}
		
	}
	
}