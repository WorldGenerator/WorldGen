import java.util.Random;

public class Rabbit {
    private Coordinate location;
    private int x, y;
    private Random RANDOM = new Random(234);

    public Rabbit(Coordinate location) {
        this.location = location;
        x = location.getxCord();
        y = location.getyCord();
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

    public Coordinate getLocation() {
        return location;
    }
}
