import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JPanel; 


public class MainView extends JPanel implements ActionListener {
	
	
	private static final long serialVersionUID = -8378862729669669738L;
	private JButton newGameButton;
	private JButton settingsButton;
	private JButton helpButton;
	private JButton creditsButton;
	private GameFrame frame;
	
	public MainView(GameFrame frame)
	{
		
		this.frame = frame;
		setLocation(0,0); 
		setSize(1100,600); 
		setLayout(null); 
		
		//NEWGAME BUTTON
		newGameButton = new JButton(); 		
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false); 		
		newGameButton.setSize(180,60);
		newGameButton.setLocation(375, 415);
		newGameButton.addActionListener(this);
		add(newGameButton); 
		
		
		//HELP BUTTON
		helpButton = new JButton(); 		
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setSize(180,60);
		helpButton.setLocation(580, 415);
		helpButton.addActionListener(this);
		add(helpButton);		
		
		//SETTINGS BUTTON
		settingsButton = new JButton(); 		
		settingsButton.setOpaque(false);
		settingsButton.setContentAreaFilled(false); 		
		settingsButton.setSize(180,60);
		settingsButton.setLocation(375, 500);
		settingsButton.addActionListener(this);
		add(settingsButton);
		
		
		//CREDITS BUTTON 
		creditsButton = new JButton(); 		
		creditsButton.setOpaque(false);
		creditsButton.setContentAreaFilled(false); 		
		creditsButton.setSize(180,60);
		creditsButton.setLocation(580, 500);
		creditsButton.addActionListener(this);
		add(creditsButton);
		
		
	}
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
	
	public void paintComponent(Graphics g){
		
		String path = "assets\\mainMenu.png"; 
		
		g.drawImage(new ImageIcon(path).getImage(),0,0,1100,600,null); 
	}

}