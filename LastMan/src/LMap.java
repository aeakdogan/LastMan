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
			this.id = id;
			walls = new ArrayList<Wall>();
			Scanner mapInfo = new Scanner(new FileReader("docs//maps.txt"));
			String test;
			do
			{
				test = mapInfo.next();
			}while(!test.equals(id));
			
			for(int y = 0; y < Location.Y_WALL_COUNT; y++)
			{
				for(int x = 0; x < Location.X_WALL_COUNT; x++)
				{
					int value = mapInfo.nextInt();
					if(value == 1)
					{
						walls.add(new Wall(new Location(x*40, y*40), 1));
					}
					if(value == 2)
					{
						walls.add(new Wall(new Location(x*40, y*40), 2));
					}
				}
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
