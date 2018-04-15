import java.util.List;
import java.util.ArrayList;

public class Land {
	private Coordinate loc;
	private boolean plant = false;
	private boolean me = false;
	private Rabbit rabbits = null;
	private Fox foxy = null;

	public Land(int x, int y) {
		Coordinate loc  = new Coordinate(x,y);
	}

	public void insert(Object target) {
		if ((target instanceof Rabbit) && !hasRabbit()){
			rabbits = target;
		} else if ((target instanceof Rabbit) && !hasFox()) {
			foxy = target;
		} else {
			System.out.println("Should not be added");
		}
	}

	public boolean hasPlant() {
	    return plant;
    }

    public void removePlant() {
	    plant = false;
    }

    public boolean hasRabbit() {
	    return rabbits != null;
    }

    public void removeRabbit() {
	    rabbits = null;
    }

    public boolean hasFox() {
	    return foxy != null;
    }

    public void removeFox() {
	    foxy = null;
    }
}