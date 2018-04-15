import java.util.List;
import java.util.ArrayList;

public class Land {
	private Coordinate loc;

	private Player me = null;
	private Plant plant = null;
	private Rabbit rabbits = null;
	private Fox foxy = null;

	public Land(int x, int y) {
		Coordinate loc  = new Coordinate(x,y);
	}


	public void insert(Rabbit target) {
		if (!hasRabbit()){
			rabbits = target;
		} 
	}
    public boolean hasRabbit() {
	    return rabbits != null;
    }
    public void removeRabbit() {
	    rabbits = null;
    }


	public void insert(Fox target) {
		if (!hasFox()) {
			foxy = target;
		} 
	}
    public boolean hasFox() {
	    return foxy != null;
    }
    public void removeFox() {
	    foxy = null;
    }


	public void insert(Plant target) {
		if (!hasPlant()) {
			plant = target;
		}
	}
	public boolean hasPlant() {
	    return plant != null;
    }
    public void removePlant() {
	    plant = null;
    }

}