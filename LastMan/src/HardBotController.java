/**
 * @author Burcu Canakci
 * Definition: This class is for controlling hard level bots.
 * This class is inherited from BotController class.
 */
public class HardBotController extends BotController
{

	//Constructor
	/**
	 * This constructor initializes an hard bot controller.
	 * @param c determines which character this bot will be
	 */
	public HardBotController(Character c) 
	{
		character = c;
	}

	//Methods
	/* (non-Javadoc)
	 * This method is overridden from BotController class.
	 * Determines what this hard bot will do.
	 * @see BotController#makeRandomMove()
	 */
	@Override
	public boolean makeRandomMove() 
	{
		int prob = 0 + (int)(Math.random()*101);
		
		if(prob < 6)
		{
			weaponTwoKeyPressed();
			return true;
		}
		
		if(prob < 10)
		{
			weaponOneKeyPressed();
			return true;
		}
		
		if(prob <= 100)
		{
			int dir = 1 + (int)(Math.random()*4);
			directionKeyPressed(dir);
			for(int i = 0; i < BotController.HARD_MOVE; i++)
				directionKeyPressed(dir);
			return true;
		}
		return false;
	}
}
