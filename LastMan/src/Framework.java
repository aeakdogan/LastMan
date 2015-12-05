import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Framework {
	private static JFrame frame;
	
	static int MAIN_MENU_ID = 0;
	static int SETTINGS_MENU_ID = 1;
	
	private static ArrayList<JPanel> views;
	
	public Framework(JFrame frame){
		Framework.frame = frame;
		frame.setTitle("Last Man");
		frame.setSize(1100,600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		views = new ArrayList<JPanel>();
		views.add(new MainMenu());
		views.add(new SettingsView(new Settings()));
		
		frame.setContentPane(views.get(MAIN_MENU_ID));
		frame.setVisible(true);
	}
	
	public static void switchMenu(int menuID){
		frame.setContentPane(views.get(menuID));
		frame.invalidate();
		frame.validate();
	}
}