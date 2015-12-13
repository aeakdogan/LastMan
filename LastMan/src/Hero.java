import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Hero{
	
	//properties
	private int initialHealthPoints;
	private int speed;
	private String id;
	private Weapon weaponOne;
	private Weapon weaponTwo;
	
	//constructor
	public Hero(String id)
	{
		try 
		{
			Scanner heroInfo = new Scanner(new FileReader("assets\\heroes.txt"));
			this.id = id;
			boolean created = false;
			String test;
			do
			{
				test = heroInfo.next();
				if (test.equals(id))
				{
					initialHealthPoints = heroInfo.nextInt();
					speed = heroInfo.nextInt();
					weaponTwo = new Weapon(heroInfo.nextInt(), heroInfo.nextInt(), heroInfo.nextInt(), false);
					weaponOne = new Weapon(Weapon.ONE_RANGE, heroInfo.nextInt(), Weapon.ONE_DELAY, true);
					created = true;
				}
			}while (!created && heroInfo.hasNext());
			
			heroInfo.close();
			
			if(!created)
			{
				System.out.println("Error");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//methods
	public int getInitialHealthPoints() {
		return initialHealthPoints;
	}

	public void setInitialHealthPoints(int initialHealthPoints) {
		this.initialHealthPoints = initialHealthPoints;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Weapon getWeaponOne() {
		return weaponOne;
	}

	public void setWeaponOne(Weapon weaponOne) {
		this.weaponOne = weaponOne;
	}

	public Weapon getWeaponTwo() {
		return weaponTwo;
	}

	public void setWeaponTwo(Weapon weaponTwo) {
		this.weaponTwo = weaponTwo;
	}
}
