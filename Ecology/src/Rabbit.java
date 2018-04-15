import java.util.Random;

public class Rabbit implements Animal {
    private Coordinate location;
    private int x, y;
    private boolean hungry;
    private World w;
    private int hunger;
    private Random RANDOM = new Random(234);

    public Rabbit(World w, Coordinate location) {
        this.location = location;
        x = location.getxCord();
        y = location.getyCord();
        hungry = false;
        this.w = w;
        hunger = 10;
    }

    public void eat() {
        //if same tile as a rabbit, probability of eating rabbit
        //if successful, hungry remains false
        Land area = w.getLocation(location);
        if (area.hasPlant()) {
            area.removePlant();
            hunger = 10;
        }
    }

    public Coordinate move() {
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
                    case 2:
                        if (y - 1 >= 0) {
                            y -= 1;
                        }
                    case 3:
                        if (y + 1 < 50) {
                            y += 1;
                        }
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

    public void starvation() {
        if (hunger <= 0) {
            //Replace this instance with land, garbage collect
            w.getWorld()[x][y].removeFox();
        } else {
            hunger -= 1;
        }
    }

    public Coordinate getLocation() {
        return location;
    }
}
