
public class Main {
	
	public static void main(String[] args){
		
		MainMenu welcomeScreen = new MainMenu();
		MainFrame frame = new MainFrame(welcomeScreen,"Last Man"); 
		welcomeScreen.setFrame(frame); 
		
	}

}
