import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Pack {

	//constants
	public static final int HEALTH_BOOST = 50;
	public static final int WEAPON_BOOST = 25;
	
	//properties
	private String id;
	private int time;
	private int delayTime;
	
	public Pack(String id)
	{
		try 
		{
			Scanner packInfo = new Scanner(new FileReader("packs.txt"));
			boolean created = false;
			String test;
			do
			{
				test = packInfo.next();
				if (test.equals(id))
				{
					delayTime = packInfo.nextInt();
					created = true;
				}
			}while (!created && packInfo.hasNext());
			
			packInfo.close();
			
			if(!created)
			{
				//handle error
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
