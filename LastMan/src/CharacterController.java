
public abstract class CharacterController 
{

	public static int DIRECTION_UP = 1;
	public static int DIRECTION_RIGHT = 2;
	public static int DIRECTION_LEFT = 3;
	public static int DIRECTION_DOWN = 4;
	
	//properties
	protected Character character;
	
	//methods
	public void directionKeyPressed(int direction)
	{
		Location newLoc = calculateNewLocation(direction);
		Location validLoc = character.getGame().getGameMap().validateLocation(newLoc);
		character.setLocation(validLoc);
	}
	
	public void weaponOneKeyPressed()
	{
		character.setWeaponOne();
	}
	
	public void weaponTwoKeyPressed()
	{
		character.setWeaponTwo();
	}
	
	private Location calculateNewLocation(int dir)
	{
		Location newL = new Location(character.getLocation().getX(),character.getLocation().getY());
		int move = character.getHero().getSpeed()*Location.BLOCK/5;
		//int move = character.getHero().getSpeed();
		
		//up
		if(dir == DIRECTION_UP)
		{
			newL.setY(newL.getY() - move );	
		}
		
		//right
		if(dir == DIRECTION_RIGHT)
		{
			newL.setX(newL.getX() + move);	
		}
		
		//left
		if(dir == DIRECTION_LEFT)
		{
			newL.setX(newL.getX() - move);	
		}
		
		//down
		if(dir == DIRECTION_DOWN)
		{
			newL.setY(newL.getY() + move);	
		}
		
		return newL;		
	}
	
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
}
