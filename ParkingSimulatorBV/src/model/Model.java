package model;

public class Model extends AbstractModel{

	private String hoi;
	
	public Model() {
	}
	
	@Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }
}
