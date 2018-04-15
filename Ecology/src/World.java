import java.util.Random;

public class World {
	private Land[][] theWorld = null;
	private int plants;
	private int fox;
	private int rabbit;
	private Player me;
	private Random RANDOM = new Random();

	public World(int x, int y) {
		int i = 0;
		int size = x*y;
		theWorld = new Land[x][y];
		plants = 0;
		fox = 0;
		rabbit = 0;

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

    //Set up
    private void addRabbit(Rabbit r) {
    	int place = RANDOM.nextInt(2500);
    	Land l = getLocation((int)place/50, place%50);
    	if (!l.hasRabbit()) {
    		l.insert(r);
    		rabbit += 1;
    	}
    	
    }
    
    //Set up
    private void addFox(Fox r) {
    	int place = RANDOM.nextInt(2500);
    	Land l = getLocation((int)place/50, place%50);
    	if (!l.hasFox()) {
    		l.insert(r);
    		fox += 1;
    	}
    }
    
    //Set up
    private void addPlant(Plant r) {
    	int place = RANDOM.nextInt(2500);
    	Land l = getLocation((int)place/50, place%50);
    	if (!l.hasPlant()) {
    		l.insert(r);
    		plants += 1;
    	}
    }

    //Called by Player
    public void addPlant(Coordinate c) {
    	getLocation(c).addPlant();
    	plants += 1;
    }

    //Called by Player
    public void removePlant(Coordinate c) {
		getLocation(c).removePlant();
		plants -= 1;

    }

    //Called by Player
    public void removeRabbit(Coordinate c) {
    	getLocation(c).removeRabbit();
    	rabbit -= 1;
    }

    //Called by Player
    public void removeFox(Coordinate c) {
    	getLocation(c).removeFox();
    	fox -= 1;
    }

	public static void main(String[] args) {
		World n = new World(50, 50);
	}
}
