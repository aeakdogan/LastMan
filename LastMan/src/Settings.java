/**
 * @author Yasemin Doganci
 * This class, Settings, is a Model class for Settings object. It is used for the accessor and mutator 
 * methods.  
 */
public class Settings implements LastManModel
{
	//Properties
	private boolean musicOn;
	private int volume;
	private SettingsView view;

	
	/**
	 * Constructor for Settings: it initializes the musicOn, volume and view properties. 
	 */
	public Settings()
	{
		musicOn = true;
		volume = 10;
		view = null;
	}

	
	/**
	 * @returns true if music is On, returns false otherwise. 
	 */
	public boolean isMusicOn() 
	{
		return musicOn;
	}

	/**
	 * @param musicOn 
	 * setMusicOn method updates the View of the Settings objects and sets the music on. 
	 */
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