import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener, WindowListener
{

	private static final long serialVersionUID = 5815024148421858765L;

	//properties
	private CardLayout cl;
	private JPanel cards;
	private Settings settings;
	//constructor
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
		addWindowListener(this);
		
		//main view
		MainView mv = new MainView(this);
		cards.add(mv, "main");
		cl.show(cards, "main");
		
		add(cards);
	}
	
	//methods
	
	
	
	
	
	
	
	
	
	
	
	public CardLayout getLayout() 
	{
		return cl;
	}
	
	public JPanel getCards() 
	{
		return cards;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
