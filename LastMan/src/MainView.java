import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JPanel; 

/**
 * @author Burcu Canakci 
 * MainView class is used for showing the Main Menu of the Last Man game. It has different JButtons for 
 * switching the menus. 
 *
 */
public class MainView extends JPanel implements ActionListener 
{
	//Constants	
	private static final long serialVersionUID = -8378862729669669738L;
	
	//Properties
	private JButton newGameButton, settingsButton, helpButton, creditsButton;
	private GameFrame frame;
	
	/**
	 * @param frame
	 * Constructor is used for initializing this frame. It has newGameButton, helpButton,settingsButton,
	 * and creditsButton in it, in order to switch the menus. 
	 */
	public MainView(GameFrame frame)
	{
		this.frame = frame;
		setLocation(0,0); 
		setSize(1100,600); 
		setLayout(null); 
		
		//NewGame Button
		newGameButton = new JButton(); 		
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setSize(180,60);
		newGameButton.setLocation(375, 415);
		newGameButton.addActionListener(this);
		add(newGameButton); 
		
		
		//Help Button
		helpButton = new JButton(); 		
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setSize(180,60);
		helpButton.setLocation(580, 415);
		helpButton.setBorderPainted(false);
		helpButton.addActionListener(this);
		add(helpButton);		
		
		//Settings Button
		settingsButton = new JButton(); 		
		settingsButton.setOpaque(false);
		settingsButton.setContentAreaFilled(false); 		
		settingsButton.setSize(180,60);
		settingsButton.setLocation(375, 500);
		settingsButton.setBorderPainted(false);
		settingsButton.addActionListener(this);
		add(settingsButton);
		
		
		//Credits Button
		creditsButton = new JButton(); 		
		creditsButton.setOpaque(false);
		creditsButton.setContentAreaFilled(false); 		
		creditsButton.setSize(180,60);
		creditsButton.setLocation(580, 500);
		creditsButton.setBorderPainted(false);
		creditsButton.addActionListener(this);
		add(creditsButton);	
	}

	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newGameButton)
		{
			GameCreatorView gcv = new GameCreatorView(frame);
			frame.getCards().add(gcv, "create");
			frame.getLayout().show(frame.getCards(), "create");
		}
		if(e.getSource() == settingsButton)
		{
			SettingsView sv = new SettingsView(frame.getSettings(), frame);
			frame.getCards().add(sv, "settings");
			frame.getLayout().show(frame.getCards(), "settings");			
		}
		if(e.getSource() == helpButton)
		{
			HelpView hv = new HelpView(frame);
			frame.getCards().add(hv, "help");
			frame.getLayout().show(frame.getCards(), "help");			
		}
		if(e.getSource() == creditsButton)
		{
			CreditsView cv = new CreditsView(frame);
			frame.getCards().add(cv, "credits");
			frame.getLayout().show(frame.getCards(), "credits");			
		}
	}
	
	
	/* 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{	
		String path = "images//mainMenu.png"; 
		g.drawImage(new ImageIcon(path).getImage(),0,0,1100,600,null); 
	}
}