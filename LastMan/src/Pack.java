
public class Pack {

	//constants
	public static final int HEALTH_BOOST = 50;
	public static final int WEAPON_BOOST = 25;
	public static final int RATE = 20;
	
	//properties
	private String id;
	private int time;
	private int delayTime;
	
	public Pack(String id)
	{
		this.id = id;
		if(id.equals("healthBoost"))
			delayTime = 10;
		
		if(id.equals("weaponBoost"))
			delayTime = 5;		
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		if(time < 0) time = 0;
		this.time = time;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
