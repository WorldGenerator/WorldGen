public class Land {
	private Coordinate loc;
	private Plant plant = null;
	private boolean me = false;
	private Rabbit rabbits = null;
	private Fox foxy = null;

	public Land(int x, int y) {
		Coordinate loc  = new Coordinate(x,y);
	}

	public void insert(Object target) {
		if ((target instanceof Rabbit) && !isEmpty()){
			rabbits = (Rabbit)target;
		} else if ((target instanceof Rabbit) && !isEmpty()) {
			foxy = (Fox)target;
		} else {
			System.out.println("Should not be added");
		}
	}

	public boolean hasPlant() {
	    return plant != null;
    }

    public boolean removePlant() {
	    if (hasPlant()) {
	        plant = null;
	        return true;
        }
	    return false;
    }

    public void addPlant(Plant p) {
	    plant = p;
    }

    public boolean hasRabbit() {
	    return rabbits != null;
    }

    public void addRabbit(Rabbit r) {
	    rabbits = r;
    }

    public boolean removeRabbit() {
	    if (hasRabbit()) {
	        rabbits = null;
	        return true;
        }
        return false;
    }

    public boolean hasFox() {
	    return foxy != null;
    }

    public void addFox(Fox f) {
	    foxy = f;
    }

    public boolean removeFox() {
	    if (hasFox()) {
	        foxy = null;
	        return true;
        }
        return false;
    }

    public boolean isEmpty() {
	    return !(hasFox() || hasPlant() || hasRabbit());
    }
}