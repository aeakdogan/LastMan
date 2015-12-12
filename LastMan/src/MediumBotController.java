
public class MediumBotController extends BotController
{

	@Override
	public boolean makeRandomMove() 
	{
		int prob = 0 + (int)(Math.random()*101);
		
		if(prob < 10)
		{
			weaponTwoKeyPressed();
			return true;
		}
		
		if(prob < 20)
		{
			weaponOneKeyPressed();
			return true;
		}
		
		if(prob <= 100)
		{
			int dir = 1 + (int)(Math.random()*4);
			directionKeyPressed(dir);
			return true;
		}
		
		return false;
	}

}
