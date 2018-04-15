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
    public Coordinate moveUp() {
        //Update location in w randomly
        boolean[] invalid = new boolean[5];
        boolean valid = false;
        while (!valid) {
            int direction = RANDOM.nextInt(4);
            if (!invalid[direction]) {
                invalid[direction] = true;
                switch (direction) {
                    case 0:
                        if (x - 1 >= 0) {
                            x -= 1;
                        }
                        break;
                    case 1:
                        if (x + 1 < 50) {
                            x += 1;
                        }
                        break;
                    case 2:
                        if (y - 1 >= 0) {
                            y -= 1;
                        }
                        break;
                    case 3:
                        if (y + 1 < 50) {
                            y += 1;
                        }
                        break;
                    case 4:
                        valid = true;
                        break;
                }
                if (location.getyCord() != y || location.getxCord() != x) {
                    valid = true;
                }
            }
        }
        return new Coordinate(x, y);
    }

    public Coordinate getLocation() {
        return location;
    }
}
