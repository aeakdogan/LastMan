import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import LMGraphics.LMButton;
import LMGraphics.LMPanel;
import LMGraphics.LMTextArea;

import javax.swing.BoxLayout; 

/**
 * @author Yasemin Doganci
 * This class is used for the view of Credits page. It has a JPanel to show its contents. 
 */
public class CreditsView extends JPanel implements ActionListener
{

	//Constants
	private static final long serialVersionUID = -4983868374121854280L;
	
	//Properties
	private LMButton menuButton; 
	private GameFrame frame;
	
	//Constructor
	/**
	 * @param frame
	 * This constructor, CreditsView, initializes the frame according to this frame. 
	 * Panel has a TextArea called LMText Area in it, in order to write the necessary String. 
	 *
	 */
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
	
	
	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * This is the actionPerformed method which is used for clicking the menu button. 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == menuButton)
		{
			frame.getLayout().show(frame.getCards(), "main");
		}
	}
	
	
	/* 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics  g)
	{
		super.paintComponent((java.awt.Graphics) g);		
	}
}