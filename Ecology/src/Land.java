<<<<<<< HEAD
import java.util.List;
import java.util.ArrayList;

=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> fc1c57a9789fb2963449822024a9fcc92d11281d

public class Land {
	private final Coordinate loc;
	private boolean plant = false;
	private List<Animal> rabbits;
	private List<Animal> foxy;

	public Land(int x, int y) {
		loc  = new Coordinate(x,y);
		rabbits = new ArrayList<Animal>();
		foxy = new ArrayList<Animal>();
	}

	public void insert(Animal target) {
		// if (target instanceof Rabbit) {
		// 	rabbits.add(target);
		// } else if (target instanceof Fox) {
		// 	foxy.add(target);
		// } else {
		// 	System.out.println("Should not be added");
		// }
	}
}