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

public class CreditsView extends JPanel implements ActionListener{

	private JPanel infoPanel; 
	private JTextArea creditsText; 
	private JButton menuButton; 
	
	public CreditsView() {
		
		infoPanel = new JPanel(); 
		infoPanel.setLayout(null);
		infoPanel.setPreferredSize(new Dimension(1100,600)); 
		infoPanel.setBackground(Color.BLACK);	
		
		creditsText = new JTextArea("ENES AKDO–AN\n\nBURCU «ANAK«I\n\nYASEM›N DO–ANCI\n\n÷ZG‹R TAﬁOLUK");
		creditsText.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		creditsText.setSize(400,400);
		creditsText.setForeground(Color.YELLOW);
		creditsText.setBackground(Color.BLACK);
		creditsText.setLocation(350, 100);
		
	
		menuButton = new JButton("MAIN MENU");
		menuButton.setSize(180,60);
		menuButton.setLocation(900,500);
		menuButton.setBackground(Color.YELLOW);
		menuButton.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		menuButton.addActionListener((ActionListener) this);
		
		infoPanel.add(creditsText);
		infoPanel.add(menuButton);
	
		
		this.add(infoPanel);
		setVisible(true); 
		
	}
	
	public void actionPerformed(ActionEvent e){
		int newFrame = 4;
		if(e.getSource() == menuButton)
			newFrame = Framework.MAIN_MENU_ID;
		Framework.switchMenu(newFrame);
	}
	
	public void paintComponent(Graphics  g){
		super.paintComponent((java.awt.Graphics) g);
		
	}
	
}
	