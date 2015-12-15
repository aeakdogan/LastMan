/**
 * @author Burcu Canakci
 * Definition: This class is the main class for controlling all characters
 * in this game including both players and bots. Subclasses will be inherited
 * from this class.
 * 
 */
public abstract class CharacterController 
{	
	//Properties
	protected Character character;
	
	//Methods
	/**
	 * This method is activated when a direction key is pressed. 
	 * It finds a valid new location for the character.
	 * @param direction determines which direction is pressed
	 */
	public void directionKeyPressed(int direction)
	{
		Location newLoc = calculateNewLocation(direction);
		Location validLoc = character.getGame().getGameMap().validateLocation(newLoc, direction);
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
	/**
	 * This method calculates a new location for the character
	 * in which direction is pressed.
	 * @param dir determines which direction is pressed
	 * @return new location for the character is returned
	 */
	private Location calculateNewLocation(int dir)
	{
		Location newL = new Location(character.getLocation().getX(),character.getLocation().getY());
		int move = character.getHero().getSpeed()*Location.BLOCK/Character.MOVE_CONSTANT;
		
		if(dir == Location.DIRECTION_UP)
		{
			newL.setY(newL.getY() - move );	
		}
		if(dir == Location.DIRECTION_RIGHT)
		{
			newL.setX(newL.getX() + move);	
		}
		if(dir == Location.DIRECTION_LEFT)
		{
			newL.setX(newL.getX() - move);	
		}
		if(dir == Location.DIRECTION_DOWN)
		{
			newL.setY(newL.getY() + move);	
		}
		
		return newL;		
	}
	
	public Character getCharacter() 
	{
		return character;
	}

	public void setCharacter(Character character) 
	{
		this.character = character;
	}
}