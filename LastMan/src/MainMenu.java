import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.image.BufferedImage; 

import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 


public class MainMenu extends JPanel implements ActionListener {
	
	JButton newGameButton;
	JButton settingsButton;
	JButton helpButton;
	JButton creditsButton;
	
	public MainMenu(){
		
		setLocation(0,0); 
		setSize(1100,600); 
		setLayout(null); 
		
		//NEWGAME BUTTON
		newGameButton = new JButton(); 
		
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false); 
		//newGameButton.setBorderPainted(false);
		
		
		newGameButton.setSize(180,60);
		newGameButton.setLocation(375, 415);
		newGameButton.addActionListener(this);
		add(newGameButton); 
		
		
		//HELP BUTTON
		helpButton = new JButton(); 
		
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		//helpButton.setBorderPainted(false);
		
		
		helpButton.setSize(180,60);
		helpButton.setLocation(580, 415);
		helpButton.addActionListener(this);
		add(helpButton);
		
		
		
		//SETTINGS BUTTON
		settingsButton = new JButton(); 
		
		settingsButton.setOpaque(false);
		settingsButton.setContentAreaFilled(false); 
		//settingsButton.setBorderPainted(false);
		
		
		settingsButton.setSize(180,60);
		settingsButton.setLocation(375, 500);
		settingsButton.addActionListener(this);
		add(settingsButton);
		
		
		//CREDITS BUTTON 
		creditsButton = new JButton(); 
		
		creditsButton.setOpaque(false);
		creditsButton.setContentAreaFilled(false); 
		//creditsButton.setBorderPainted(false);
		
		
		creditsButton.setSize(180,60);
		creditsButton.setLocation(580, 500);
		creditsButton.addActionListener(this);
		add(creditsButton);
		
		
	}
	public void actionPerformed(ActionEvent e){
		int newFrame = 0;
		if(e.getSource() == settingsButton)
			newFrame = Framework.SETTINGS_MENU_ID;
		Framework.switchMenu(newFrame);
	}
	
	public void paintComponent(Graphics g){
		
		String path = "assets\\mainMenu.png"; 
		
		g.drawImage(new ImageIcon(path).getImage(),0,0,1100,600,null); 
	}

}