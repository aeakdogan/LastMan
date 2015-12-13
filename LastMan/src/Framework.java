import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Framework {
	private static JFrame frame;
	
	static int MAIN_MENU_ID = 0;
	static int NEW_GAME_ID = 1;
	static int SETTINGS_MENU_ID = 2;
	static int HELP_MENU_ID = 3; 
	static int CREDITS_MENU_ID = 4; 
	
	private static ArrayList<JPanel> views;
	
	public Framework(JFrame frame){
		Framework.frame = frame;
		frame.setTitle("Last Man");
		frame.setSize(1100,600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		views = new ArrayList<JPanel>();
		views.add(new MainMenu());
		
		
		views.add(new GameView(new Game()));
		views.add(new SettingsView(new Settings()));
		views.add(new HelpView()); 
		views.add(new CreditsView()); 
		
		frame.setContentPane(views.get(MAIN_MENU_ID));
		frame.setVisible(true);
	}
	
	public static void switchMenu(int menuID){
		frame.setContentPane(views.get(menuID));
		frame.invalidate();
		frame.validate();
	}
}