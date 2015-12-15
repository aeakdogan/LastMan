import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import LMGraphics.LMButton;
import LMGraphics.LMPanel;
import LMGraphics.LMTextArea;

import javax.swing.BoxLayout; 

public class CreditsView extends JPanel implements ActionListener
{

	//Constants
	private static final long serialVersionUID = -4983868374121854280L;
	
	//Properties
	private LMButton menuButton; 
	private GameFrame frame;
	
	//Constructor
	public CreditsView(GameFrame frame) 
	{
		this.frame = frame;		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.BLACK);	
		
		LMPanel pan1 = new LMPanel();	
		LMTextArea creditsText = new LMTextArea("CREDITS\nEnes Akdogan\nBurcu Canakci\nYasemin Doganci\nOzgur Tasoluk", Color.YELLOW);
		pan1.add(creditsText);
		add(pan1);
		
		LMPanel pan2 = new LMPanel();		
		menuButton = new LMButton("Main Menu", Color.YELLOW);
		menuButton.addActionListener(this);
		pan2.add(menuButton);
		add(pan2);		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == menuButton)
		{
			frame.getLayout().show(frame.getCards(), "main");
		}
	}
	
	public void paintComponent(Graphics  g)
	{
		super.paintComponent((java.awt.Graphics) g);		
	}
}