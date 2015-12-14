
public class Location {

	//constants
	public static final int BLOCK = 8;
	public static final int CELL = 40;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_LEFT = 3;
	public static final int DIRECTION_DOWN = 4;
	public static final int X_WALL_COUNT = 21;
	public static final int Y_WALL_COUNT = 11;
	public static final int X_LIMIT = CELL * X_WALL_COUNT;
	public static final int Y_LIMIT = CELL * Y_WALL_COUNT;
	
	//properties
	private int x;
	private int y;
	
	//constructor
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//methods
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Location l)
	{
		if(l.getX() == x && l.getY() == y)
			return true;
		return false;
	}
		
}
