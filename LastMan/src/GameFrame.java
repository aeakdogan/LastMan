import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Burcu Canakci
 *
 * GameFrame class is framework of LastMan game
 * this class initialize game window and switches the views of the game by using Card Layout
 * 
 */
public class GameFrame extends JFrame
{

	//Constants
	private static final long serialVersionUID = 5815024148421858765L;

	//Properties
	private CardLayout cl;
	private JPanel cards;
	private Settings settings;
	
	//Constructor
	public GameFrame()
	{
		settings = new Settings();
		setTitle("Last Man");
		setSize(1100,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		cl = new CardLayout();
		cards = new JPanel(cl);
		
		//Main View
		MainView mv = new MainView(this);
		cards.add(mv, "main");
		cl.show(cards, "main");
		
		add(cards);
	}
	
	//Methods
	public CardLayout getLayout() 
	{
		return cl;
	}
	
	public JPanel getCards() 
	{
		return cards;
	}

	public Settings getSettings() 
	{
		return settings;
	}

	public void setSettings(Settings settings) 
	{
		this.settings = settings;
	}
}
