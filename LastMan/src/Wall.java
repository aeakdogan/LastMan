import java.awt.Point;

public class Wall {
	int resistance;
	Point location;
	
	public Wall(int resistance, int locationX, int locationY){
		this.resistance = resistance;
		this.location = new Point(locationX, locationY);
	}
	
	public void setResistance( int resistance){
		this.resistance = resistance;
	}
	public void setLocation(int locationX, int locationY){
		this.location = new Point(locationX, locationY);
	}
	
	public int getResistance(){
		return resistance;
	}
	public Point getLocation(){
		return location;
	}
	
}
