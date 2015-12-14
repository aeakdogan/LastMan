
public abstract class CharacterController 
{	
	//properties
	protected Character character;
	
	//methods
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
	
	private Location calculateNewLocation(int dir)
	{
		Location newL = new Location(character.getLocation().getX(),character.getLocation().getY());
		int move = character.getHero().getSpeed()*Location.BLOCK/5;
		//int move = character.getHero().getSpeed();
		
		//up
		if(dir == Location.DIRECTION_UP)
		{
			newL.setY(newL.getY() - move );	
		}
		
		//right
		if(dir == Location.DIRECTION_RIGHT)
		{
			newL.setX(newL.getX() + move);	
		}
		
		//left
		if(dir == Location.DIRECTION_LEFT)
		{
			newL.setX(newL.getX() - move);	
		}
		
		//down
		if(dir == Location.DIRECTION_DOWN)
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