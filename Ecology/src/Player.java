public class Player {
    private Coordinate location;
    private int health;
    private World w;
    private Land currLand;

    public Player(Coordinate startCord, int healthStart, World w) {
        this.location = location;
        health = healthStart;
        this.w = w;
        Land currLand = w.getWorld()[location.getxCord()][location.getyCord()];
    }

    public int getHealth() {
        return health;
    }

    public void huntRabbit() {
        if (currLand.hasRabbit()) {
            currLand.removeRabbit();
        }
    }

    public void huntFox() {
        if (currLand.hasFox()) {
            currLand.removeFox();
        }
    }

    public void harvest() {
        if (currLand.hasPlant()) {
            currLand.removePlant();
        }
    }

    public void grow() {
        if (!currLand.hasPlant()) {
            currLand.addPlant();
        }
    }

    public Coordinate getLocation() {
        return location;
    }
}
