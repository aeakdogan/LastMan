import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Burcu Canakci
 * 
 * LMap class initialize the map of the game and holds the properties of the map
 * 
 */
public class LMap 
{
	//Properties
	private String id;
	private ArrayList<Wall> walls;
	
	//Constructor
	/**
	 * Constructor of the LMap class initialize map of the game
	 * @param id unique ID of maps
	 * 
	 * constructor reads all maps from the file
	 * and take walls of the map with this id 
	 * 
	 */
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
						walls.add(new Wall(new Location(x*Location.CELL, y*Location.CELL), 1));
					}
					if(value == 2)
					{
						walls.add(new Wall(new Location(x*Location.CELL, y*Location.CELL), 2));
					}
				}
			}
						
			mapInfo.close();	
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public ArrayList<Wall> getWalls() 
	{
		return walls;
	}
	
	/**
	 * @param w
	 * remove wall of w from current walls
	 * 
	 */
	public void removeWall(Wall w)
	{
		walls.remove(w);
	}

	public void setWalls(ArrayList<Wall> walls) 
	{
		this.walls = walls;
	}
}
