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
			rabbits = (Rabbit)target;
		} else if ((target instanceof Rabbit) && !hasFox()) {
			foxy = (Fox)target;
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

    public void addPlant() {
	    plant = true;

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