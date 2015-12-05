public class SettingsController 
{
	private Settings settings;

	public SettingsController(Settings settings) 
	{
		this.settings = settings;
	}

	public Settings getSettings() 
	{
		return settings;
	}

	public void setSettings(Settings settings) 
	{
		this.settings = settings;
	}
	
	public void setVolume(int value)
	{
		settings.setVolume(value);
	}
	
	public void setMusicOn(boolean value)
	{
		settings.setMusicOn(value);
	}
	
}
