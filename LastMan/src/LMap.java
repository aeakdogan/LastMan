import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LMap 
{
	//properties
	private String id;
	private ArrayList<Wall> walls;
	
	//constructor
	
	
	public LMap(String id)
	{
		if(id.equals("tmp")){
			int mapHeight = 11;
			int mapWidth = 22;
			id = "dummy";
			walls = new ArrayList<Wall>();
			for(int i = 0 ; i < mapWidth; i++){
				walls.add(new Wall(new Location(0, i), Wall.HARD));
			}
			for(int i = 0 ; i < mapWidth; i++){
				walls.add(new Wall(new Location(mapHeight-1, i), Wall.HARD));
			}
			
			for(int i = 0 ; i < mapHeight; i++){
				walls.add(new Wall(new Location(i, 0), Wall.HARD));
			}
			for(int i = 0 ; i < mapHeight; i++){
				walls.add(new Wall(new Location(i, mapWidth-1), Wall.HARD));
			}
			
			for(int i = 2 ; i < mapHeight ; i+=2){
				for(int j = 2 ; j < mapWidth ; j+=2){
					walls.add(new Wall(new Location(i, j), Wall.HARD));
				}
			}
			
			for(int i = 0 ; i < 50; i++){
				int randX = new Random().nextInt(mapHeight-2)+1;
				int randY = new Random().nextInt(mapWidth-2)+1;
				
				if(randX %2 ==1 || randY %2 ==1)
					walls.add(new Wall(new Location(randX, randY), Wall.SOFT));
			}
			return;
		}
		try 
		{
			this.id = id;
			walls = new ArrayList<Wall>();
			Scanner mapInfo = new Scanner(new FileReader("maps.txt"));
			String test;
			do
			{
				test = mapInfo.next();
			}while(!test.equals(id));
			
			for(int x = 0; x < 22; x++)
			{
				for(int y = 0; y < 11; y++)
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
