public class HardBotController extends BotController
{

	//Constructor
	public HardBotController(Character c) 
	{
		character = c;
	}

	//Methods
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
