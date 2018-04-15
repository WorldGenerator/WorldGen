public class Player {
    private Coordinate location;
    private int health;
    private World w;
    private Land currLand;

    public Player(Coordinate startCord, int healthStart, World w) {
        this.location = location;
        health = healthStart;
        this.w = w;
    }

    public int getHealth() {
        return health;
    }

    public void huntRabbit() {
        if (currLand.hasRabbit()) {
            w.removeRabbit(location);
        }

    }

    public void huntFox() {
        if (currLand.hasFox()) {
            w.removeFox(location);
        }
    }

    public void harvest() {
        if (currLand.hasPlant()) {
            w.removePlant(location);
        }
    }

    public void grow() {
        if (!currLand.hasPlant()) {
            w.addPlant(location);
        }
    }

    public Coordinate getLocation() {
        return location;
    }
}
