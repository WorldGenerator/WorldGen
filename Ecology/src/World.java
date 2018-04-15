public class World {
	private Land[][] theWorld = null;
	private int plants;
	private int fox;
	private int rabbit;

	public World(int x, int y) {
		theWorld = new Land[x][y];

		int i = 0;
		int size = x*y;
		while (i < size) {
			theWorld[(int)i/y][i%y] = new Land((int)i/y, i%y);
			i += 1;

		}
	}

	public static void main(String[] args) {
		World n = new World(50, 50);
	}
}
