/**
 * @author Burcu Canakci
 * Controller class for Settings object. 
 * Has volumeChanged and musicOnChanged methods for controlling the Settings object. 
 */
public class SettingsController 
{
	//Properties
	private Settings settings;

	
	/**
	 * @param settings
	 * Constructor initializes this settings to settings. 
	 */
	public SettingsController(Settings settings) 
	{
		this.settings = settings;
	}
	
	//Methods
	public void volumeChanged(int value)
	{
		settings.setVolume(value);
	}
	
	public void musicOnChanged(boolean value)
	{
		settings.setMusicOn(value);
	}
	
}