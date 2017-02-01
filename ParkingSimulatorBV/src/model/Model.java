package model;

import model.*;
import view.*;

public class Model extends AbstractModel{

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int tickPause = 100;
    private boolean run;
	
	public Model() {
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		run=false;
	}
	
	@Override
	public void run(){
		run=true;
		while(run) {
	        for (int i = 0; i < 10000; i++) {
	            tick();
	        }
			try {
				Thread.sleep(10);
			} catch (Exception e) {} 
		}
	}
    /**
     * Laat de tijd tikken en updates de views. 
     */
    private void tick() {
    	advanceTime();
    	handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }

    /**
     * Geeft de tijd aan tot de minuut.
     */
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

}
