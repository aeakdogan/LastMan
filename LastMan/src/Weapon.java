
public class Weapon implements LastManModel{
	
	//properties
	public final static int ONE_RANGE = 10;
	public final static int ONE_DELAY = 20;
			
	private int range;
	private int damage;
	private int delayTime;
	private int time;
	private boolean firstType;
	
	
	//constructor	
	public Weapon(int range, int damage, int delayTime, boolean firstType) 
	{
		this.range = range;
		this.damage = damage;
		this.delayTime = delayTime;
		this.firstType = firstType;
	}
	
	
	//methods
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		if(time < 0) time = 0;
		this.time = time;
	}
	public boolean isFirstType() {
		return firstType;
	}
	public void setFirstType(boolean firstType) {
		this.firstType = firstType;
	}
	
	

}
