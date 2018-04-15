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

		this.theWorld = new Land[x][y];
		this.plants = 0;
		this.fox = 0;
		this.rabbit = 0;

		while (i < size) {
			theWorld[(int)i/y][i%y] = new Land((int)i/y, i%y);
			i += 1;
		}
		this.me = new Player(new Coordinate(0,0), 100.0);
		
	}

    public Land[][] getWorld() {
        return this.theWorld;
    }

    public Land getLocation(Coordinate coor) {
    	return theWorld[coor.getxCord()][coor.getyCord()];
    }

    public Land getLocation(int x, int y) {
    	return theWorld[x][y];
    }

    public void addRabbit(Rabbit r) {
    	int place = RANDOM.nextInt(2500);
    	Land l = getLocation((int)place/50, place%50);
    	if (!l.hasRabbit()) {
    		l.insert(r);
    		rabbit += 1;
    	}
    	
    }
    
    public void addFox(Fox r) {
    	int place = RANDOM.nextInt(2500);
    	Land l = getLocation((int)place/50, place%50);
    	if (!l.hasFox()) {
    		l.insert(r);
    		fox += 1;
    	}
    }
    
    public void addPlant(Plant r) {
    	int place = RANDOM.nextInt(2500);
    	Land l = getLocation((int)place/50, place%50);
    	if (!l.hasPlant()) {
    		l.insert(r);
    		plants += 1;
    	}
    }

	public static void main(String[] args) {
		World n = new World(50, 50);
	}
}
