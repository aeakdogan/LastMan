
public class Character
{
	//constants
	public static final int EASY = -3;
	public static final int MEDIUM = -2;
	public static final int HARD = -1;
	
	//properties
	private Location location;
	private int hP;
	private int level;
	private int noWeaponOneUsageFor;
	private int noWeaponTwoUsageFor;	
	private int deathTime;
	private Hero hero;
	private Game game;
	
	//constructor
	public Character(String heroId, int l, Game g) 
	{
		hero = new Hero(heroId);
		hP = hero.getInitialHealthPoints();
		level = l;
		noWeaponOneUsageFor = 0;
		noWeaponTwoUsageFor = 0;
		game = g;
		location = new Location(40,40);
		deathTime = -1;
	}
	

	//methods
	public boolean setWeaponOne()
	{
		if(noWeaponOneUsageFor == 0)
		{
			game.getGameMap().addToCurrentWeapons(location, hero.getWeaponOne());
			noWeaponOneUsageFor = Weapon.ONE_DELAY;
			return true;
		}
		
		return false;	
	}
	
	public boolean setWeaponTwo()
	{
		if(noWeaponTwoUsageFor == 0)
		{
			game.getGameMap().addToCurrentWeapons(location, hero.getWeaponTwo());
			noWeaponTwoUsageFor = hero.getWeaponTwo().getDelayTime();
			return true;
		}
		
		return false;	
	}
	
	private boolean implementPack(Pack p)
	{
		if(p.getId().equals("healthBoost"))
		{
			hP += Pack.HEALTH_BOOST;
			return true;
		}
		if(p.getId().equals("weaponOneBoost"))
		{
			hero.getWeaponOne().setDamage( hero.getWeaponOne().getDamage() + Pack.WEAPON_BOOST);
			return true;
		}
		
		return false;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) 
	{
		this.location = location;
		if(game.getGameMap().getPackAt(location) != null && level != EASY)
		{
			game.getGameMap().removePackAt(location);
			if(level == HARD)
				implementPack(game.getGameMap().getPackAt(location));
		}
	}

	public int gethP() {
		return hP;
	}

	public void sethP(int hP) {
		this.hP = hP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNoWeaponOneUsageFor() {
		return noWeaponOneUsageFor;
	}

	public void setNoWeaponOneUsageFor(int noWeaponOneUsageFor) {
		if(noWeaponOneUsageFor < 0) noWeaponOneUsageFor = 0;
		this.noWeaponOneUsageFor = noWeaponOneUsageFor;
	}

	public int getNoWeaponTwoUsageFor() {
		return noWeaponTwoUsageFor;
	}

	public void setNoWeaponTwoUsageFor(int noWeaponTwoUsageFor) {
		if(noWeaponTwoUsageFor < 0) noWeaponTwoUsageFor = 0;
		this.noWeaponTwoUsageFor = noWeaponTwoUsageFor;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getDeathTime() {
		return deathTime;
	}

	public void setDeathTime(int deathTime) {
		this.deathTime = deathTime;
	}

}
