public class Settings {
	
	//properties
	private boolean musicOn;
	private int volume;
	private SettingsView view;
	

	//constructor
	public Settings(){
		musicOn = true;
		volume = 10;
		view = null;asdasdasd
	}

	//methods
	public boolean isMusicOn() {
		return musicOn;
	}

	public void setMusicOn(boolean musicOn){
		this.musicOn = musicOn;
		view.updateView();
	}

	public int getVolume(){
		return volume;
	}

	public void setVolume(int volume){
		this.volume = volume;
		view.updateView();
	}
	
	public SettingsView getView() {
		return view;
	}

	public void setView(SettingsView view) {
		this.view = view;
	}
	
}
