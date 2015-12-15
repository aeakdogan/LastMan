package LMGraphics;

import java.awt.Color;
import javax.swing.JComboBox;

public class LMComboBox extends JComboBox<String>
{
	//Constants
	private static final long serialVersionUID = 3054776763425051659L;
	
	//Constructor
	public LMComboBox(String[] data, Color theme)
	{
		super(data);
		setSelectedIndex(0);
		setBackground(DefaultProperties.DEFAULT_BG_COLOR);
		setForeground(theme);
		setFont(DefaultProperties.DEFAULT_FONT);
	}

}
