import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;

public class World {

	private Land[][] theWorld = null;
	private int plants;
	private int fox;
	private int rabbit;

	private HashSet<Rabbit> rabbitList = new HashSet<>();
	private HashSet<Fox> foxList = new HashSet<>();
	private HashSet<Plant> plantList = new HashSet<>();
	private ArrayList<Plant> seedPlant = new ArrayList<>();

	private Player me;
	private Random RANDOM = new Random();

	public World(int x, int y) {
		int i = 0;
		int size = x*y;
		theWorld = new Land[x][y];
		this.plants = 0;
		this.fox = 0;
		this.rabbit = 0;
		this.me = new Player(new Coordinate(30,30), 100, this);

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
            seedPlant.add(seedling);
            plants += 1;
            return true;
        }
        return false;
    }

    public void updatePlants() {
	    while (seedPlant.size() > 0) {
	        plantList.add(seedPlant.remove(0));
        }
    }

/////////////////////////////////////////////////////////////////////
////         Called by Player									/////
/////////////////////////////////////////////////////////////////////
    public void addPlant(Coordinate c) {
        Land l = getLocation(c);
        if (!l.hasPlant()) {
            Plant seedling = new Plant(c);
            l.insert(seedling);
            seedPlant.add(seedling);
            plants += 1;
        }
    }

    public void removePlant(Coordinate c) {
        getLocation(c).removePlant();

        plants -= 1;

    }

    public void removeRabbit(Coordinate c) {
        rabbitList.remove(getLocation(c).getRabbit());
        getLocation(c).removeRabbit();
        System.out.println("Removed");
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

        int size  = 25;
		//Setting Plants, Rabbits, Fox in random locations
		int i = 0;
		while (i < size) {
			if (game.addRabbit()) {
				i += 1;
			}
		}
		i = 0;
		while (i < size) {
			if (game.addFox()) {
				i += 1;
			}
		}
		i = 0;
		while (i < size) {
			if (game.addPlant()) {
				i += 1;
			}
		}
		current[0] = game.plants;
		current[1] = game.fox;
		current[2] = game.rabbit;
		


        // while (timer1.elapsedTime() < 10.0) {
        // 	System.out.println(timer1.elapsedTime());
        // }
        StdDrawPlus.setCanvasSize(size * 100, size * 100);
        StdDrawPlus.setXscale(0, size * 2);
        StdDrawPlus.setYscale(0, size * 2);


		Stopwatch timer1 = new Stopwatch();
		String img = "Image/astro pose front.png";
        while (true) {
        	//Update Image
            StdDrawPlus.clear(new Color(0, 0, 0));
			StdDrawPlus.picture(size, size, "Image/mars plane.png");

            for (Rabbit r : game.rabbitList) {
                Coordinate newloc = r.getLocation();
                StdDrawPlus.picture(newloc.getxCord(), newloc.getyCord(), "Image/rabbit1.png");
            }
            for (Fox f : game.foxList) {
                Coordinate newloc = f.getLocation();
                StdDrawPlus.picture(newloc.getxCord(), newloc.getyCord(), "Image/fox1.png");
            }
            for (Plant p : game.plantList) {
                Coordinate newloc = p.getLocation();
                StdDrawPlus.picture(newloc.getxCord(), newloc.getyCord(), "Image/PLANTalt.png");
            }

			StdDrawPlus.picture(myself.getLocation().getxCord(), myself.getLocation().getyCord(), img);

		    if (StdDrawPlus.isZPressed()) {
		        myself.huntRabbit();
		    } else if (StdDrawPlus.isXPressed()) {
		    	myself.huntFox();
		    } else if (StdDrawPlus.isCPressed()) {
		    	myself.harvest();
		    } else if (StdDrawPlus.isVPressed()) {
		    	myself.grow();
		    } else if (StdDrawPlus.isUPPressed()) {
		    	myself.moveUp();
		    	img = "Image/astro pose back.png";
		    } else if (StdDrawPlus.isDOWNPressed()) {
		    	myself.moveDown();
		    	img = "Image/astro pose front.png";
		    } else if (StdDrawPlus.isLEFTPressed()) {
		    	myself.moveLeft();
		    	img = "Image/astro pose facing left.png";
		    } else if (StdDrawPlus.isRIGHTPressed()) {
		    	myself.moveRight();
		    	img = "Image/astro pose facing right.png";
		    }

            StdDrawPlus.show(250);
            if (timer1.elapsedTime() > 5.0) {
	            game.moveRabbit();
	            game.moveFox();
	            game.growPlant();
	            game.updatePlants();
            	timer1 = new Stopwatch();
            }
            // StdDrawPlus.show(1500);

        }
    }
}
