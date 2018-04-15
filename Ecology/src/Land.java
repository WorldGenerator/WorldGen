import java.util.List;
import java.util.ArrayList;

public class Land {
	private Coordinate loc;
	private boolean plant = false;
	private boolean me = false;
	private List<Rabbit> rabbits;
	private List<Fox> foxy;

	public Land(int x, int y) {
		Coordinate loc  = new Coordinate(x,y);
		ArrayList container = new ArrayList();
		rabbits = new ArrayList<Rabbit>();
		foxy = new ArrayList<Fox>();
	}

	public void insert(Object target) {
		if (target instanceof Rabbit) {
			rabbits.add((Rabbit) target);
		} else if (target instanceof Fox) {
			foxy.add((Fox) target);
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
	    return rabbits.size() > 0;
    }

    public void removeRabbit() {
	    rabbits.remove(0);
    }

    public boolean hasFox() {
	    return foxy.size() > 0;
    }

    public void removeFox() {
	    foxy.remove(0);
    }
}