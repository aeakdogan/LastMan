
public abstract class CharacterController 
{
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
		int move = character.getHero().getSpeed()*10;
		
		//up
		if(dir == 1)
		{
			newL.setY(newL.getY() - move );	
		}
		
		//right
		if(dir == 2)
		{
			newL.setX(newL.getX() + move);	
		}
		//left
		if(dir == 3)
		{
			newL.setX(newL.getX() - move);	
		}
		//down
		if(dir == 4)
		{
			newL.setX(newL.getY() + move);	
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
