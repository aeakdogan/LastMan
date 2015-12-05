import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.image.BufferedImage; 

import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 


public class MainMenu extends JPanel {
	
	
	private MainFrame f; 
	
	public MainMenu(){
		
		setLocation(0,0); 
		setSize(1100,900); 
		setLayout(null); 
		
		//NEWGAME BUTTON
		JButton newGameButton = new JButton(); 
		
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false); 
		//newGameButton.setBorderPainted(false);
		
		
		newGameButton.setSize(180,90);
		newGameButton.setLocation(375, 625);
		add(newGameButton); 
		
		
		//HELP BUTTON
		JButton helpButton = new JButton(); 
		
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false); 
		//helpButton.setBorderPainted(false);
		
		
		helpButton.setSize(180,90);
		helpButton.setLocation(580, 625);
		add(helpButton);
		
		
		
		//SETTINGS BUTTON
		JButton settingsButton = new JButton(); 
		
		settingsButton.setOpaque(false);
		settingsButton.setContentAreaFilled(false); 
		//settingsButton.setBorderPainted(false);
		
		
		settingsButton.setSize(180,90);
		settingsButton.setLocation(375, 750);
		add(settingsButton);
		
		
		//CREDITS BUTTON 
		JButton creditsButton = new JButton(); 
		
		creditsButton.setOpaque(false);
		creditsButton.setContentAreaFilled(false); 
		//creditsButton.setBorderPainted(false);
		
		
		creditsButton.setSize(180,90);
		creditsButton.setLocation(580, 750);
		add(creditsButton);
		
		
	}
	
	
	public void setFrame(MainFrame frame){
		this.frame = frame; 
	}
	
	
	public void paintComponent(Graphics g){
		
		String path = "Images\\Main Menu png.png"; 
		
		g.drawImage(new ImageIcon(path).getImage(),0,0,1100,900,null); 
	}

}