package LMGraphics;

import java.awt.Color;
import javax.swing.JTextArea;

public class LMTextArea extends JTextArea
{
	//Constants
	private static final long serialVersionUID = 3426174951002226704L;

	//Constructor
	public LMTextArea(String text, Color theme)
	{
		super(text);
		setForeground(theme);
		setBackground(DefaultProperties.DEFAULT_BG_COLOR);
		setFont(DefaultProperties.DEFAULT_FONT);
		setSize(400,400);
	}
}
