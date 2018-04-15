public class Player {
    private Coordinate location;
    private int health;
    private World w;

    public Player(Coordinate startCord, int healthStart, World w) {
        this.location = location;
        health = healthStart;
        this.w = w;
    }

    public int getHealth() {
        return health;
    }

    public void hunger() {
        health -= 1;
    }

    //Z
    public void huntRabbit() {
        if (w.getLocation(location).hasRabbit()) {
            w.removeRabbit(location);
        }
    }

    //X
    public void huntFox() {
        if (w.getLocation(location).hasFox()) {
            w.removeFox(location);
        }
    }

    //C
    public void harvest() {
        if (w.getLocation(location).hasPlant()) {
            w.removePlant(location);
        }
    }

    //v
    public void grow() {
        if (!w.getLocation(location).hasPlant()) {
            w.addPlant(location);
        }
    }

    public Coordinate getLocation() {
        return location;
    }
}
