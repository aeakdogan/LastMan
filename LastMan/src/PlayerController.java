/**
 * @author Burcu Çanakçý
 * Definition: This class is for controlling a character which is directed by player.
 */
public class PlayerController extends CharacterController 
{
	//Constructor
	/**
	 * Initializes a player controller.
	 * @param c determines the character
	 */
	public PlayerController(Character c)
	{
		setCharacter(c);
	}
}
