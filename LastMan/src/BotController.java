/**
 * @author Burcu Canakci
 * Definition: Abstract Class for controlling bots. It will be used by 
 * subclassing for varying bot levels. This class has been inherited from
 * CharacterController class.
 * 
 */
public abstract class BotController extends CharacterController
{
	//Constants
	public static final int HARD_MOVE = 2;
	public static final int MEDIUM_MOVE = 1;
	public static final int EASY_MOVE = 1;

	//Methods
	public abstract boolean makeRandomMove();
}
