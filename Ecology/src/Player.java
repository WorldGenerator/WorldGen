public class Player {
    private Coordinate location;
    private int health;
    private World w;

    public Player(Coordinate startCord, int healthStart, World w) {
        this.location = startCord;
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
        } else {

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

    public void moveUp() {
        //Update location in w randomly
        int y = location.getyCord();
        if (y+1 >= 50) {
            return;
        }
        
        location = new Coordinate(location.getxCord(), y+1);
    }
    public void moveDown() {
        //Update location in w randomly
        int y = location.getyCord();
        if (y-1 < 0) {
            return;
        }
        
        location = new Coordinate(location.getxCord(), y-1);
    }
    public void moveRight() {
        //Update location in w randomly
        int x = location.getxCord();
        if (x+1 >= 50) {
            return;
        }
        
        location = new Coordinate(x+1, location.getyCord());
    }
    public void moveLeft() {
        //Update location in w randomly
        int x = location.getxCord();
        if (x-1 < 0) {
            return;
        }
        
        location = new Coordinate(x-1, location.getyCord());
    }

    public Coordinate getLocation() {
        return location;
    }
}
