import java.util.ArrayList;
import java.util.Random;

public class Map {
	private String id;
	private ArrayList<Wall> walls;
	
	public Map(String id){
		this.id = id;
	}
	public Map(){
		int mapHeight = 12;
		int mapWidth = 18;
		id = "dummy";
		walls = new ArrayList<Wall>();
		for(int i = 0 ; i < mapWidth; i++){
			walls.add(new Wall(2, 0, i));
		}
		for(int i = 0 ; i < mapWidth; i++){
			walls.add(new Wall(2, mapHeight-1, i));
		}
		
		for(int i = 0 ; i < mapHeight; i++){
			walls.add(new Wall(2, i, 0));
		}
		for(int i = 0 ; i < mapHeight; i++){
			walls.add(new Wall(2, i, mapWidth-1));
		}
		
		for(int i = 2 ; i < mapHeight ; i+=2){
			for(int j = 2 ; j < mapWidth ; j+=2){
				addWall(new Wall(2, i, j));
			}
		}
		
		for(int i = 0 ; i < 50; i++){
			int randX = new Random().nextInt(mapHeight-2)+1;
			int randY = new Random().nextInt(mapWidth-2)+1;
			
			if(randX %2 ==1 || randY %2 ==1)
				addWall(new Wall(1, randX, randY));
		}
	}
	
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	
	
	public void addWall(Wall wall){
		walls.add(wall);
	}
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	
}
