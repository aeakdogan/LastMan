package LMGraphics;

import java.awt.FlowLayout;
import javax.swing.JPanel;

public class LMPanel extends JPanel
{
	//Constructor
	private static final long serialVersionUID = -9077040295242287205L;
	
	//Constructor
	public LMPanel()
	{
		super(new FlowLayout(FlowLayout.CENTER));
		setBackground(DefaultProperties.DEFAULT_BG_COLOR);
	}

}
