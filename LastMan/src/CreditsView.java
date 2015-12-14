import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BoxLayout; 

public class CreditsView extends JPanel implements ActionListener
{

	private static final long serialVersionUID = -4983868374121854280L;
	private JTextArea creditsText; 
	private JButton menuButton; 
	private GameFrame frame;
	
	public CreditsView(GameFrame frame) 
	{
		this.frame = frame;		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); 
		setPreferredSize(new Dimension(1100,600)); 
		setBackground(Color.BLACK);	
		
		JPanel pan1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan1.setBackground(Color.BLACK);
		creditsText = new JTextArea("CREDITS\nEnes Akdogan\nBurcu Canakci\nYasemin Doganci\nOzgur Tasoluk");
		creditsText.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		creditsText.setSize(400,400);
		creditsText.setForeground(Color.YELLOW);
		creditsText.setBackground(Color.BLACK);
		pan1.add(creditsText);
		add(pan1);
		
		JPanel pan2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan2.setBackground(Color.BLACK);
		menuButton = new JButton("Main Menu");
		menuButton.setSize(180,60);
		menuButton.setHorizontalTextPosition(JButton.CENTER);
		menuButton.setVerticalTextPosition(JButton.CENTER);
		menuButton.setForeground(Color.YELLOW); 
		menuButton.setBackground(Color.BLACK);
		menuButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		menuButton.setBorderPainted(false);
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
	
	public void paintComponent(Graphics  g){
		super.paintComponent((java.awt.Graphics) g);
		
	}
	
}