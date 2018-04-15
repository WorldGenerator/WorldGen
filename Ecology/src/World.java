import java.util.Random;
import java.util.ArrayList;


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

		while (i < size) {
			theWorld[i/y][i%y] = new Land(i/y, i%y);
			i += 1;
		}
		me = new Player(new Coordinate(0,0), 100, this);
		
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
    		l.insert(new Rabbit(new Coordinate(x,y)));

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
    		l.insert(new Fox(new Coordinate(x,y)));
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
    		l.insert(new Plant(new Coordinate(x,y)));
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

		World n = new World(50, 50);

		//Setting Plants, Rabbits, Fox in random locations
		int i = 0;
		while (i < 50) {
			if (n.addRabbit()) {
				i += 1;
			}
		}
		i = 0;
		while (i < 50) {
			if (n.addFox()) {
				i += 1;
			}
		}
		i = 0;
		while (i < 50) {
			if (n.addPlant()) {
				i += 1;
			}
		}

		int size  = 50;
		StdDrawPlus.setScale(0, 25);
		for (int x = 0; size > x; x += 1){
			for (int y = 0; size> y; y += 1){
		        if ((x + y) % 2 == 0){
		        	StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
		        }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                }
            	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
          	}
		}

	}
}








