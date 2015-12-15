package LMGraphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class LMButton extends JButton
{
	//Constants
	private static final long serialVersionUID = 7749535477078274195L;

	//Constructor
	public LMButton(String text, Color theme)
	{
		super(text);
		setForeground(theme);
		setBackground(DefaultProperties.DEFAULT_BG_COLOR);
		setFont(DefaultProperties.DEFAULT_FONT);
		setSize( new Dimension( 500, 50 ) );
		setBorderPainted(false);
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);		
	}
}
