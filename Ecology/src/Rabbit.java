public class Rabbit implements Animal {
    private Coordinate location;
    private boolean hungry;
    private World w;
    private int hunger;

    public Rabbit(World w, Coordinate location) {
        this.location = location;
        hungry = false;
        this.w = w;
        hunger = 10;
    }

    public void eat() {
        //if same tile as a rabbit, probability of eating rabbit
        //if successful, hungry remains false
        Land area = w.getLand[x][y];
        if (area.hasPlant) {
            area.removePlant();
            hunger = 10;
        }
    }

    public Coordinate move() {
        //Update location in g randomly
        return null;
    }

    public void starvation() {
        if (hunger <= 0) {
            //Replace this instance with land, garbage collect

        }
    }

    public Coordinate getLocation() {
        return location;
    }
}
