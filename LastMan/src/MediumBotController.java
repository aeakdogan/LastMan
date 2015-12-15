/**
 * @author Burcu
 * Definition: This class is for controlling medium level bots.
 * This class is inherited from BotController class.
 */
public class MediumBotController extends BotController
{
	//Constructor
	/**
	 * This constructor initializes an easy bot controller.
	 * @param c determines which character this bot will be
	 */
	public MediumBotController(Character c) 
	{
		character = c;
	}

	//Methods
	/* (non-Javadoc)
	 * This method is overridden from BotController class.
	 * Determines what this medium bot will do.
	 * @see BotController#makeRandomMove()
	 */
	@Override
	public boolean makeRandomMove() 
	{
		int prob = 0 + (int)(Math.random()*101);
		
		if(prob < 4)
		{
			weaponTwoKeyPressed();
			return true;
		}
		
		if(prob < 8)
		{
			weaponOneKeyPressed();
			return true;
		}
		
		if(prob <= 100)
		{
			int dir = 1 + (int)(Math.random()*4);
			for(int i = 0; i < BotController.MEDIUM_MOVE; i++)
				directionKeyPressed(dir);
			return true;
		}
		
		return false;
	}

}
