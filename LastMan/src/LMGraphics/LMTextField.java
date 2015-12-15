package LMGraphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

public class LMTextField extends JTextField
{
	//Constants
	private static final long serialVersionUID = -7591000319345176361L;
	
	//Constructor
	public LMTextField(Color theme)
	{
		setBackground(DefaultProperties.DEFAULT_BG_COLOR);
		setForeground(theme);
		setFont(DefaultProperties.DEFAULT_FONT);
		setPreferredSize( new Dimension( 500, 50 ));
	}

}
