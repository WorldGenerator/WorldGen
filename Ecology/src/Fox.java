import java.util.Random;

public class Fox implements Animal {
    private Coordinate location;
    private int x, y;
    private World w;
    private int hunger;
    private final Random RANDOM = new Random(234);

    public Fox(World w, Coordinate location) {
        x = location.getxCord();
        y = location.getyCord();
        this.location = location;
        this.w = w;
        hunger = 10;
    }

    public void eat() {
        //if same tile as a rabbit, probability of eating rabbit
        //if successful, hungry remains false
        Land area = w.getLand[x][y];
        if (area.hasRabbit) {
            if (RANDOM.nextInt(100) > 40) {
                area.removeRabbit();
                hunger = 10;
            }
        }
    }

    public Coordinate move() {
        //Update location in g randomly
        return null;
    }

    public void starvation() {
        if (hunger <= 0) {
            //Replace this instance with land, garbage collect
            w.getLand[x][y].removeFox();
        } else {
            hunger -= 1;
        }
    }

    public Coordinate getLocation() {
        return location;
    }
}
