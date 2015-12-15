import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import LMGraphics.LMButton;
import LMGraphics.LMPanel;
import LMGraphics.LMTextArea;
import javax.swing.BoxLayout; 

/**
 * @author Yasemin Doganci
 * HelpView class is used for the view of Help page. It has a JPanel to show its contents. 
 */
public class HelpView extends JPanel implements ActionListener
{
	//Constants
	private static final long serialVersionUID = -9093209230812299651L;
	
	//Properties
	private LMButton menuButton; 
	private GameFrame frame;
	
	
	/**
	 * @param frame 
	 * HelpView constructor is used for initializing this frame. JPanel pan1 has a TextArea in it,
	 * in order to show its contents as a String.  
	 */
	public HelpView(GameFrame frame)
	{
		
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(1100,600)); 
		setBackground(Color.BLACK);
		
		Color defCol = Color.RED;
		
		LMPanel pan1 = new LMPanel();
		LMTextArea helpText = new LMTextArea(
				"HELP\nUse arrow keys to move your character.\n"+ "Use the SPACE key to use your default weapon.\n" + "Use the S key to use your special weapon.\n\n" +
				           "Character\tHealth\tSpeed\nKil\t600\t10\nAyibogan\t1000\t2\nNemo\t800\t5\n\n"+ "Your default weapon damages both your enemies and you.\n" +
				           "Your special weapon damages only your enemies, and it has extra damage.\n\n"
				           + "Don't forget to pick up packs before they disappear!\n\n"
				           + "Good luck!", defCol); 
		helpText.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		pan1.add(helpText);
	
		LMPanel pan2 = new LMPanel();
		menuButton = new LMButton("Main Menu", defCol);
		menuButton.addActionListener(this);
		pan2.add(menuButton);
	
		add(pan1);
		add(pan2); 		
	}
	
	
	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * actionPerformed method is used for clicking the menu button. 
	 */
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