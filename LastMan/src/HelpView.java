import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JButton; 
import javax.swing.JTextArea; 

public class HelpView extends JPanel {

	//Properties
	private JPanel infoPanel; 
	private JTextArea helpText; 
	private JButton menuButton; 
	
	public HelpView(){

		Font font = new Font("Verdana", Font.BOLD, 20); 
		
		infoPanel = new JPanel(); 
		infoPanel.setLayout(null);
		infoPanel.setPreferredSize(new Dimension(1100,600)); 
		infoPanel.setBackground(Color.BLACK);
		
		helpText = new JTextArea("HELP\nUse arrow keys to move your character.\n"+ "Use K button to use weapon.\n" + "Use J button to use special weapon."); 
		helpText.setSize(400,400);
		helpText.setForeground(Color.GREEN);
		helpText.setBackground(Color.BLACK);
		helpText.setLocation(350, 100);
		helpText.setFont(font);
	
		menuButton = new JButton("Main Menu");
		menuButton.setSize(180,60);
		menuButton.setLocation(900,500);
		menuButton.setBackground(Color.GREEN);
		
		
		infoPanel.add(helpText);
		infoPanel.add(menuButton); 
	
		
		this.add(infoPanel);
		setVisible(true); 
		
	}
	
	
	public void paintComponent(Graphics  g){
		super.paintComponent((java.awt.Graphics) g);
		
	}
	
}