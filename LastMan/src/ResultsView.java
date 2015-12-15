import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import LMGraphics.LMButton;
import LMGraphics.LMPanel;
import LMGraphics.LMTextArea; 

public class ResultsView extends JPanel implements ActionListener
{
	//Constants
	private static final long serialVersionUID = -4983868374121854280L;
	
	//Properties
	private LMButton menuButton; 
	private GameFrame frame;
	
	//Constructor
	public ResultsView(String dataS, GameFrame frame) 
	{
		this.frame = frame;		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); 
		setPreferredSize(new Dimension(1100,600)); 
		setBackground(Color.BLACK);
		
		Color defCol = Color.PINK;
		
		LMPanel pan1 = new LMPanel();
		LMTextArea data = new LMTextArea(dataS, defCol);
		pan1.add(data);
		add(pan1);
		
		LMPanel pan2 = new LMPanel();
		menuButton = new LMButton("Main Menu", defCol);
		menuButton.addActionListener(this);
		pan2.add(menuButton);
		add(pan2);		
	}
	
	public void paintComponent(Graphics  g)
	{
		super.paintComponent((java.awt.Graphics) g);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == menuButton)
		{
			frame.getLayout().show(frame.getCards(), "main");
		}
	}
}