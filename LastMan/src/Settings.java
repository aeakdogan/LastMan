public class Settings implements LastManModel
{
	//Properties
	private boolean musicOn;
	private int volume;
	private SettingsView view;

	//Constructor
	public Settings()
	{
		musicOn = true;
		volume = 10;
		view = null;
	}

	//Methods
	public boolean isMusicOn() 
	{
		return musicOn;
	}

	public void setMusicOn(boolean musicOn)
	{
		this.musicOn = musicOn;
		view.updateView();
	}

	public int getVolume()
	{
		return volume;
	}

	public void setVolume(int volume)
	{
		this.volume = volume;
		view.updateView();
	}
	
	public SettingsView getView()
	{
		return view;
	}

	public void setView(SettingsView view) 
	{
		this.view = view;
	}
	
}