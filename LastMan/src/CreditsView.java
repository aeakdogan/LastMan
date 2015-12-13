import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea; 

public class CreditsView extends JPanel{

	private JPanel infoPanel; 
	private JTextArea creditsText; 
	private JButton menuButton; 
	
	public CreditsView(){
		
		infoPanel = new JPanel(); 
		infoPanel.setLayout(null);
		infoPanel.setPreferredSize(new Dimension(1100,600)); 
		infoPanel.setBackground(Color.BLACK);
		
		
		Font font = new Font("Verdana", Font.BOLD, 20); 
		
		creditsText = new JTextArea("ENES AKDO–AN\n\nBURCU «ANAK«I\n\nYASEM›N DO–ANCI\n\n÷ZG‹R TAﬁOLUK");
		creditsText.setSize(400,400);
		creditsText.setForeground(Color.YELLOW);
		creditsText.setBackground(Color.BLACK);
		creditsText.setLocation(350, 100);
		creditsText.setFont(font);
		
	
		menuButton = new JButton("Main Menu");
		menuButton.setSize(180,60);
		menuButton.setLocation(900,500);
		menuButton.setBackground(Color.YELLOW);
		
		infoPanel.add(creditsText);
		infoPanel.add(menuButton);
	
		
		this.add(infoPanel);
	
		setVisible(true); 
		
		
	}
	
	public void paintComponent(Graphics  g){
		super.paintComponent((java.awt.Graphics) g);
		
	}
	
}
	