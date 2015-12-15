public class EasyBotController extends BotController
{
	//Constructor
	public EasyBotController(Character c) 
	{
		character = c;
	}

	//Methods
	@Override
	public boolean makeRandomMove() 
	{
		int prob = 0 + (int)(Math.random()*101);
		
		if(prob < 2)
		{
			weaponTwoKeyPressed();
			return true;
		}
		
		if(prob < 4)
		{
			weaponOneKeyPressed();
			return true;
		}
		
		if(prob <= 100)
		{
			int dir = 1 + (int)(Math.random()*4);
			for(int i = 0; i < BotController.EASY_MOVE; i++)
				directionKeyPressed(dir);
			return true;
		}
		
		return false;
	}
}
