import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;

public class World {

	private Land[][] theWorld = null;
	private int plants;
	private int fox;
	private int rabbit;
	private float[][] ratio = {{0.4f, -.8f, 0f}, {0.2f, 0.2f, -1.2f}, {0f, 0.25f, -0.5f}};

	private Rabbit[] rabbitList = new Rabbit[2500];
	private Fox[] foxList = new Fox[2500];
	private Plant[] plantList = new Plant[2500];
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
    	Rabbit[] temp = new Rabbit[2500];
        for (Rabbit r : rabbitList) {
        	if (r != null) {
	            Coordinate newLocation = r.move();
	            Land newLand = theWorld[newLocation.getxCord()][newLocation.getyCord()];
	            if (newLand.isEmpty()) {
	                Land oldLand = getLocation(r.getLocation());
	                oldLand.removeRabbit();
	                newLand.addRabbit(r);
	                temp[newLocation.getxCord()*50 + newLocation.getyCord()] = r;
	                r.updateLocation(newLocation);
	            } else {
	            	temp[r.getLocation().getxCord()*50 + r.getLocation().getyCord()] = r;
	            }   		
        	} 

        }
        rabbitList = temp;
    }

    //random Fox movements
    public void moveFox() {
    	Fox[] temp = new Fox[2500];
        for (Fox f : foxList) {
        	if (f != null) {
	            Coordinate newLocation = f.move();
	            Land newLand = theWorld[newLocation.getxCord()][newLocation.getyCord()];
	            if (newLand.isEmpty()) {
	                Land oldLand = getLocation(f.getLocation());
	                oldLand.removeFox();
	                newLand.addFox(f);
	                temp[newLocation.getxCord()*50 + newLocation.getyCord()] = f;
	                f.updateLocation(newLocation);
	            } else {
	            	temp[f.getLocation().getxCord()*50 + f.getLocation().getyCord()] = f;
	            }   		
        	} 

        }
        foxList = temp;
    }


/////////////////////////////////////////////////////////////////////
////         Set up Methods 									/////
/////////////////////////////////////////////////////////////////////



    //Set up
    private boolean addRabbit() {
        int place = RANDOM.nextInt(2500);
        int x = (int)place/50;
        int y = place%50;
        Land l = getLocation(x, y);
        if (!l.hasRabbit()) {
            Rabbit bunny = new Rabbit(new Coordinate(x,y));
            l.insert(bunny);
            rabbitList[place] = bunny;
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
            foxList[place] = cub;
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
            plantList[place] = seedling;
            this.plants += 1;
            return true;
        }
        return false;
    }

    // //random Plant growth
    // public void growPlant() {
    //     for (Plant p : plantList) {
    //         Coordinate newLocation = p.grow();
    //         Land newLand = getLocation(newLocation);
    //         if (newLand.isEmpty()) {
    //             addPlant(newLocation);
    //         }
    //     }
    // }

    // public void updatePlants() {
	   //  while (seedPlant.size() > 0) {
	   //      plantList.add(seedPlant.remove(0));
    //     }
    // }

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
    	if (getLocation(c).hasRabbit()) {
	    	plantList[c.getxCord()*50 + c.getyCord()] = null;
	        getLocation(c).removePlant();
	        plants -= 1;    		
    	}


    }

    public void removeRabbit(Coordinate c) {
        if (getLocation(c).hasRabbit()) {
        	rabbitList[c.getxCord()*50 + c.getyCord()] = null;
	        getLocation(c).removeRabbit();
	        rabbit -= 1;
        }
    }

    public void removeFox(Coordinate c) {
	    if (getLocation(c).hasFox()) {
	    	foxList[c.getxCord()*50 + c.getyCord()] = null;
	        getLocation(c).removeFox();
	        fox -= 1;
	    }
    }

/////////////////////////////////////////////////////////////////////
////         Target Achievers									/////
/////////////////////////////////////////////////////////////////////

    public void targetPlant(int target) {
		while (plants < target) {
			addPlant();
		}
		for (Plant p : plantList) {
			if (p != null && (RANDOM.nextInt(10) > 6) && plants > target) {
				removePlant(p.getLocation());
			}
		}
    }

    public void targetRabbit(int target) {
		while (rabbit < target) {
			addRabbit();
		}
		for (Rabbit r : rabbitList) {
			if (r != null && (RANDOM.nextInt(10) > 6) && rabbit > target) {
				removeRabbit(r.getLocation());
			}
		}
    }

    public void targetFox(int target) {
		while (fox < target) {
			addFox();
		} 
		for (Fox f : foxList) {
			if (f != null && (RANDOM.nextInt(10) > 6) && fox > target) {
				removeFox(f.getLocation());
			}
		}
    }

/////////////////////////////////////////////////////////////////////
////         Interactive Occurance								/////
/////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

		World game = new World(50, 50);
		Player myself = game.me;

        int size  = 25;

		//Setting Plants, Rabbits, Fox in random locations
		int i = 0;
		while (i < 50) {
			if (game.addRabbit()) {
				i += 1;
			}
		}
		i = 0;
		while (i < 30) {
			if (game.addFox()) {
				i += 1;
			}
		}
		i = 0;
		while (i < 60) {
			if (game.addPlant()) {
				i += 1;
			}
		}


        StdDrawPlus.setCanvasSize(size * 100, size * 100);
        StdDrawPlus.setXscale(0, size * 2);
        StdDrawPlus.setYscale(0, size * 2);

        //MOVEMENT TIME
		Stopwatch timer1 = new Stopwatch();

		//ENVIRONMENT TIME
		
		Stopwatch timing = new Stopwatch();

		String img = "Image/front pose";
		String num = " 3";
		String cb = "";
        while (true) {
        	//Update Image
            StdDrawPlus.clear(new Color(0, 0, 0));
			StdDrawPlus.picture(size, size, "Image/mars plane.png");

			for (int x = 0; x < size *2 ; x += 1) {
				for (int y = 0 ; y <size*2 ;  y+= 1) {
					Land here = game.theWorld[x][y];
					if (here.hasRabbit()) {
						StdDrawPlus.picture(x, y, "Image/rabbit1.png");
					} else if (here.hasFox()) {
						StdDrawPlus.picture(x, y, "Image/fox1.png");
					} else if (here.hasPlant()) {
						StdDrawPlus.picture(x, y, "Image/PLANTalt.png");
					}
				}
			}

			num = " 3";
			StdDrawPlus.picture(myself.getLocation().getxCord(), myself.getLocation().getyCord(), img + num + cb + ".png");

		    if (StdDrawPlus.isZPressed()) {
		        myself.huntRabbit();
		        cb = " crowbar";
		    } else if (StdDrawPlus.isXPressed()) {
		    	myself.huntFox();
		    	cb = " crowbar";
		    } else if (StdDrawPlus.isCPressed()) {
		    	myself.harvest();
		    	cb = "";
		    } else if (StdDrawPlus.isVPressed()) {
		    	myself.grow();
		    	cb = "";
		    } else if (StdDrawPlus.isUPPressed()) {
		    	myself.moveUp();
		    	img = "Image/back pose";
		    } else if (StdDrawPlus.isDOWNPressed()) {
		    	myself.moveDown();
		    	img = "Image/front pose";
		    } else if (StdDrawPlus.isLEFTPressed()) {
		    	myself.moveLeft();
		    	img = "Image/left pose";
		    } else if (StdDrawPlus.isRIGHTPressed()) {
		    	myself.moveRight();
		    	img = "Image/RIGHT pose";
		    }

            StdDrawPlus.show(125);
            if (timer1.elapsedTime() > 3.0) {
	            game.moveRabbit();
	            game.moveFox();
	            // game.growPlant();
	            // game.updatePlants();
            	timer1 = new Stopwatch();

            }

            StdDrawPlus.clear(new Color(0, 0, 0));
			StdDrawPlus.picture(size, size, "Image/mars plane.png");

			for (int x = 0; x < size *2 ; x += 1) {
				for (int y = 0 ; y <size*2 ;  y+= 1) {
					Land here = game.theWorld[x][y];
					if (here.hasRabbit()) {
						StdDrawPlus.picture(x, y, "Image/rabbit2.png");
					} else if (here.hasFox()) {
						StdDrawPlus.picture(x, y, "Image/fox2.png");
					} else if (here.hasPlant()) {
						StdDrawPlus.picture(x, y, "Image/PLANTalt.png");
					}
				}
			}

			num = " 2";

			StdDrawPlus.picture(myself.getLocation().getxCord(), myself.getLocation().getyCord(), img + num + cb + ".png");
			StdDrawPlus.show(125);

            if (timing.elapsedTime() > 5.0) {
            	float[][] r = game.ratio;
            	int[] current = new int[3];

				current[0] = game.plants;
				current[1] = game.rabbit;
				current[2] = game.fox;

            	int targetP = (int) ((current[0]*(r[0][0])) + (current[1]*(r[0][1])) + (current[2]*(r[0][2])));
            	int targetR = (int) ((current[0]*(r[1][0])) + (current[1]*(r[1][1])) + (current[2]*(r[1][2])));
            	int targetF = (int) ((current[0]*(r[2][0])) + (current[1]*(r[2][1])) + (current[2]*(r[2][2])));

            	game.targetPlant(targetP);
            	game.targetRabbit(targetR);
            	game.targetFox(targetF);
            	timing = new Stopwatch();
            }
            // StdDrawPlus.show(1500);

        }
    }
}
