public abstract class CharacterController 
{	
	//Properties
	protected Character character;
	
	//Methods
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