import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LMap 
{
	//properties
	private String id;
	private ArrayList<Wall> walls;
	
	//constructor
	public LMap(String id)
	{
		try 
		{
			Scanner mapInfo = new Scanner(new FileReader("maps.txt"));
			String test;
			do
			{
				test = mapInfo.next();
			}while(!test.equals(id));
			
			int value = mapInfo.nextInt();
			while(value != -1)
			{
				walls.add(new Wall(new Location(value, mapInfo.nextInt()), mapInfo.nextInt()));
				value = mapInfo.nextInt();
			}
						
			mapInfo.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}
	
	public void removeWall(Wall w)
	{
		walls.remove(w);
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}
}
