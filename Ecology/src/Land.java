public class Land {
	private final Coordinate loc;
	private boolean plant = false;
	private List<Rabbit>[] rabbits;
	private List<Fox>[] foxy;

	public Land(int x, int y) {
		loc  = new Coordinate(x,y);
		container = new ArrayList();
	}

	public void insert(Object target) {
		if (target instanceof Rabbit) {
			rabbits.add(target);
		} else if (target instanceof Fox) {
			foxy.add(target);
		} else {
			System.out.println("Should not be added");
		}
	}
}