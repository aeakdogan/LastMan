import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 2269971701250845501L;

	public MainFrame()
	{
		setTitle("Last Man");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Settings s = new Settings();
		SettingsView sv = new SettingsView(s);
		
		this.add(sv);
		this.setVisible(true);
	}

}
