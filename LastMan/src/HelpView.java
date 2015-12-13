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

public class HelpView extends JPanel implements ActionListener {

	//Properties
	private JPanel infoPanel; 
	private JTextArea helpText; 
	private JButton menuButton; 
	
	public HelpView(){
		
		infoPanel = new JPanel(); 
		infoPanel.setLayout(null);
		infoPanel.setPreferredSize(new Dimension(1100,600)); 
		infoPanel.setBackground(Color.BLACK);
		
		helpText = new JTextArea("HELP\nUse arrow keys to move your character.\n"+ "Use K button to use weapon.\n" + "Use J button to use special weapon."); 
		helpText.setSize(400,400);
		helpText.setForeground(Color.RED);
		helpText.setBackground(Color.BLACK);
		helpText.setLocation(350, 100);
		helpText.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
	
		menuButton = new JButton("MAIN MENU");
		menuButton.setSize(180,60);
		menuButton.setLocation(900,500);
		menuButton.setBackground(Color.RED);
		menuButton.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		menuButton.addActionListener((ActionListener) this);
		
		infoPanel.add(helpText);
		infoPanel.add(menuButton); 
	
		
		this.add(infoPanel);
		setVisible(true); 
		
	}
	
	public void actionPerformed(ActionEvent e){
		int newFrame = 3;
		if(e.getSource() == menuButton)
			newFrame = Framework.MAIN_MENU_ID;
		Framework.switchMenu(newFrame);
	}
	public void paintComponent(Graphics  g){
		super.paintComponent((java.awt.Graphics) g);
		
	}
	
}