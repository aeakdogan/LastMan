import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BoxLayout; 

public class HelpView extends JPanel implements ActionListener {

	private static final long serialVersionUID = -9093209230812299651L;
	
	//Properties
	private JButton menuButton; 
	private GameFrame frame;
	
	//constructor
	public HelpView(GameFrame frame)
	{
		
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(1100,600)); 
		setBackground(Color.BLACK);
		
		JPanel pan1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan1.setBackground(Color.BLACK);
		JTextArea helpText = new JTextArea("HELP\nUse arrow keys to move your character.\n"+ "Use the SPACE key to use your weapon.\n" + "Use the S key to use your special weapon."); 
		helpText.setSize(400,400);
		helpText.setForeground(Color.RED);
		helpText.setBackground(Color.BLACK);
		helpText.setLocation(350, 100);
		helpText.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		pan1.add(helpText);
	
		JPanel pan2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan2.setBackground(Color.BLACK);
		menuButton = new JButton("Main Menu");
		menuButton.setSize(180,60);
		menuButton.setLocation(900,500);
		menuButton.setHorizontalTextPosition(JButton.CENTER);
		menuButton.setVerticalTextPosition(JButton.CENTER);
		menuButton.setForeground(Color.RED); 
		menuButton.setBackground(Color.BLACK);
		menuButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		menuButton.setBorderPainted(false);
		menuButton.addActionListener(this);
		pan2.add(menuButton);
	
		add(pan1);
		add(pan2); 		
	}
	
	//methods
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