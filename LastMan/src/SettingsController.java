public class SettingsController 
{
	private Settings settings;

	public SettingsController(Settings settings) 
	{
		this.settings = settings;
	}
	
	public void volumeChanged(int value)
	{
		settings.setVolume(value);
	}
	
	public void musicOnChanged(boolean value)
	{
		settings.setMusicOn(value);
	}
	
}