public class SettingsController 
{
	//Properties
	private Settings settings;

	//Constructor
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