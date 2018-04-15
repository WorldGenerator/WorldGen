import java.util.List;
import java.util.ArrayList;

public class Land {
	private final Coordinate loc;
	private boolean plant = false;
	private boolean me = false;
	private List<Animal> rabbits;
	private List<Animal> foxy;

	public Land(int x, int y) {
		loc  = new Coordinate(x,y);
		rabbits = new ArrayList<Animal>();
		foxy = new ArrayList<Animal>();
	}

	public void insert(Animal target) {
		if (target instanceof Rabbit) {
			rabbits.add(target);
		} else if (target instanceof Fox) {
			foxy.add(target);
		} else {
			System.out.println("Should not be added");
		}
	}

}