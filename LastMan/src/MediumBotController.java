
public class MediumBotController extends BotController
{

	public MediumBotController(Character c) {
		character = c;
	}

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
			for(int i = 0; i < BotController.MEDIUM_P; i++)
				directionKeyPressed(dir);
			return true;
		}
		
		return false;
	}

}
