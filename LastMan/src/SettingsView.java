import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import LMGraphics.LMButton;
import LMGraphics.LMLabel;
import LMGraphics.LMPanel; 

/**
 * @author Yasemin Doganci
 * SettingsView Class is used for updating the view of the Settings object.
 * It has a Settings object and a GameFrame object in it, which are used for updating the view. 
 * It also has a JSlider, JToggleButton and JButton. 
 *
 */
public class SettingsView extends JPanel implements ActionListener, LastManView, ChangeListener
{
	private static final long serialVersionUID = 5786938794922429875L;
	
	//Properties
	private SettingsController sc;
	private Settings settings;
	private JSlider soundSlider; 
	private JToggleButton music;
	private LMButton menuButton;
	private GameFrame frame;
	

	/**
	 * @param settings
	 * @param frame
	 * Constructor initializes the parameters and sets the view to this settings. 
	 * JPanel pan1 has a JSlider for showing the volume and a JToggleButton for showing whether 
	 * the music is on or off. It also has a JButton menuButton which is used to go back to main menu 
	 * of the game. 
	 */
	public SettingsView(Settings settings, GameFrame frame)
	{
		this.settings = settings;
		this.frame = frame;
		settings.setView(this);
		sc = new SettingsController(settings);
	
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.BLACK);
		
		Color defCol = Color.CYAN;
		
		LMPanel pan1 = new LMPanel();;
		LMLabel settingsLabel = new LMLabel("SETTINGS", defCol);
		pan1.add(settingsLabel);
		add(pan1);
		
		LMPanel pan = new LMPanel();
		LMLabel volumeLabel = new LMLabel("Volume", defCol);
		pan.add(volumeLabel);		
		soundSlider = new JSlider(JSlider.HORIZONTAL,0,150,0);
		soundSlider.setPreferredSize(new Dimension(120,40));
		soundSlider.setBackground(Color.BLACK);
		soundSlider.setForeground(defCol);
		soundSlider.setMinimum(0);
		soundSlider.setMaximum(10);
		soundSlider.setValue(settings.getVolume());
		soundSlider.addChangeListener(this); 
		pan.add(soundSlider); 
		add(pan);
		
		LMPanel pan2 = new LMPanel();
		music = new JToggleButton("Music?");
		music.setSelected(settings.isMusicOn());
		music.setSize(180,60);
		music.setBackground(Color.RED);
		music.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		music.addActionListener(this);	
		pan2.add(music);
		add(pan2);
		LMPanel pan3 = new LMPanel();
		pan3.setBackground(Color.BLACK);
		menuButton = new LMButton("Main Menu", defCol);
		menuButton.addActionListener((ActionListener) this);
		pan3.add(menuButton);
		add(pan3);
	}
	
	
	/* 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent((java.awt.Graphics) g);
	}	
	
	/* 
	 * @see LastManView#updateView()
	 */
	public void updateView()
	{
		soundSlider.setValue(settings.getVolume());
		music.setSelected(settings.isMusicOn());
	}

	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == music)
			sc.musicOnChanged(music.isSelected());
		if(e.getSource() == menuButton)
			frame.getLayout().show(frame.getCards(), "main");
	}
	
	/* 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		sc.volumeChanged(soundSlider.getValue());
	}	
} 