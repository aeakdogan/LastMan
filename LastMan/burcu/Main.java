
public class Main 
{

	public static void main(String[] args)
	{
		Hero kil = new Hero("kil");
		Hero ayibogan = new Hero("ayibogan");
		
		System.out.println("YO");
		 System.out.println(kil.getInitialHealthPoints());
		 System.out.println(kil.getWeaponTwo().getDamage());
		 System.out.println(ayibogan.getInitialHealthPoints());
		 System.out.println(ayibogan.getWeaponTwo().getRange());
	}
}
