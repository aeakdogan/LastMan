package LMGraphics;

import java.awt.Color;
import javax.swing.JLabel;

public class LMLabel extends JLabel
{
	//Constants
	private static final long serialVersionUID = 8434385519688748402L;
	
	//Constructor
	public LMLabel(String text, Color theme)
	{
		super(text);
		setForeground(theme);
		setFont(DefaultProperties.DEFAULT_FONT);
	}

}
