import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class World {

	private Land[][] theWorld = null;
	private int plants;
	private int fox;
	private int rabbit;

	private ArrayList<Rabbit> rabbitList = new ArrayList<>();
	private ArrayList<Fox> foxList = new ArrayList<>();
	private ArrayList<Plant> plantList = new ArrayList<>();

	private Player me;
	private Random RANDOM = new Random();

	public World(int x, int y) {
		int i = 0;
		int size = x*y;
		theWorld = new Land[x][y];
		this.plants = 0;
		this.fox = 0;
		this.rabbit = 0;
		this.me = new Player(new Coordinate(0,0), 100, this);

		while (i < size) {
			theWorld[i/y][i%y] = new Land(i/y, i%y);
			i += 1;
		}
		
	}

    public Land[][] getWorld() {
        return theWorld;
    }

    public Land getLocation(Coordinate coor) {
        return theWorld[coor.getxCord()][coor.getyCord()];
    }

    public Land getLocation(int x, int y) {
        return theWorld[x][y];
    }

    //random Rabbit movements
    public void moveRabbit() {
        for (Rabbit r : rabbitList) {
            Coordinate newLocation = r.move();
            Land newLand = theWorld[newLocation.getxCord()][newLocation.getyCord()];
            if (newLand.isEmpty()) {
                Land oldLand = getLocation(r.getLocation());
                oldLand.removeRabbit();
                newLand.addRabbit(r);
                r.updateLocation(newLocation);
            }
        }
    }

    //random Fox movements
    public void moveFox() {
        for (Fox f : foxList) {
            Coordinate newLocation = f.move();
            Land newLand = getLocation(newLocation);
            if (newLand.isEmpty()) {
                Land oldLand = getLocation(f.getLocation());
                oldLand.removeFox();
                newLand.addFox(f);
                f.updateLocation(newLocation);
            }
        }
    }

    //random Plant growth
    public void growPlant() {
        for (Plant p : plantList) {
            Coordinate newLocation = p.grow();
            Land newLand = getLocation(newLocation);
            if (newLand.isEmpty()) {
                addPlant(newLocation);
            }
        }
    }

    //Set up
    private boolean addRabbit() {
        int place = RANDOM.nextInt(2500);
        int x = (int)place/50;
        int y = place%50;
        Land l = getLocation(x, y);
        if (!l.hasRabbit()) {
            Rabbit bunny = new Rabbit(new Coordinate(x,y));
            l.insert(bunny);
            rabbitList.add(bunny);
            this.rabbit += 1;
            return true;
        }
        return false;
    }

    //Set up
    private boolean addFox() {
        int place = RANDOM.nextInt(2500);
        int x = (int)place/50;
        int y = place%50;
        Land l = getLocation(x, y);
        if (!l.hasFox()) {
            Fox cub = new Fox(new Coordinate(x,y));
            l.insert(cub);
            foxList.add(cub);
            this.fox += 1;
            return true;
        }
        return false;
    }

    //Set up
    private boolean addPlant() {
        int place = RANDOM.nextInt(2500);
        int x = (int)place/50;
        int y = place%50;
        Land l = getLocation(x, y);
        if (!l.hasPlant()) {
            Plant seedling = new Plant(new Coordinate(x,y));
            l.insert(seedling);
            plants += 1;
            return true;
        }
        return false;
    }

/////////////////////////////////////////////////////////////////////
////         Called by Player									/////
/////////////////////////////////////////////////////////////////////
    public void addPlant(Coordinate c) {
        Land l = getLocation(c);
        if (!l.hasPlant()) {
            l.insert(new Plant(c));
            plants += 1;
        }
    }

    public void removePlant(Coordinate c) {
        getLocation(c).removePlant();
        plants -= 1;

    }

    public void removeRabbit(Coordinate c) {
        getLocation(c).removeRabbit();
        rabbit -= 1;
    }

    public void removeFox(Coordinate c) {
        getLocation(c).removeFox();
        fox -= 1;
    }



/////////////////////////////////////////////////////////////////////
////         Interactive Occurance								/////
/////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

		World game = new World(50, 50);
		Player myself = game.me;
		int[] current = new int[3];
		float[][] ratio = new float[3][3];

		//Setting Plants, Rabbits, Fox in random locations
		int i = 0;
		while (i < 50) {
			if (game.addRabbit()) {
				i += 1;
			}
		}
		i = 0;
		while (i < 50) {
			if (game.addFox()) {
				i += 1;
			}
		}
		i = 0;
		while (i < 50) {
			if (game.addPlant()) {
				i += 1;
			}
		}
		current[0] = game.plants;
		current[1] = game.fox;
		current[2] = game.rabbit;
		
		Stopwatch timer1 = new Stopwatch();
        double sum1 = 60.0;

        // while (timer1.elapsedTime() < 10.0) {
        // 	System.out.println(timer1.elapsedTime());
        // }

        int size  = 50;
        StdDrawPlus.setCanvasSize(size * 50, size * 50);
        StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);

//        i = 0;
//        while (i < 50) {
//	        for (int x = 0; size > x; x += 1){
//	            for (int y = 0; size> y; y += 1){
//	                StdDrawPlus.picture(x + .5, y + .5, "Image/grass.png");
//	            }
//	        }
//	        StdDrawPlus.picture(.5 + i, .5 + i, "Image/astro pose walk 1.png");
//	        StdDrawPlus.show(15);
//	        i += 1;
//        }
        while (true) {
            StdDrawPlus.clear(new Color(0, 0, 0));
            StdDrawPlus.picture(size / 2, size / 2, "Image/mars plane.png");
            StdDrawPlus.picture(size * 3 / 5, size * 3 / 5, "Image/astro pose front.png");
            for (Rabbit r : game.rabbitList) {
                Coordinate newloc = r.getLocation();
                StdDrawPlus.picture(newloc.getxCord(), newloc.getyCord(), "Image/astro pose back.png");
            }
            StdDrawPlus.show(250);
            game.moveRabbit();
        }
    }
}
