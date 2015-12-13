import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider; 

public class SettingsView extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 5786938794922429875L;
	
	//Properties
	private SettingsController sc;
	private Settings settings;
	private JLabel volumeLabel, settingsLabel; 
	private JSlider soundSlider; 
	private JToggleButton music;
	private JButton menuButton;
	private GameFrame frame;
	
	//Constructor
	public SettingsView(Settings settings, GameFrame frame)
	{
		this.settings = settings;
		this.frame = frame;
		settings.setView(this);
		sc = new SettingsController(settings);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.BLACK);
		
		JPanel pan1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan1.setBackground(Color.BLACK);
		settingsLabel = new JLabel();
		settingsLabel.setSize(100,100);
		settingsLabel.setText("SETTINGS");
		settingsLabel.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		settingsLabel.setForeground(Color.GREEN);
		pan1.add( settingsLabel );
		add(pan1);
		
		JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan.setBackground(Color.BLACK);
		volumeLabel = new JLabel();
		volumeLabel.setSize(400,400);
		volumeLabel.setText( "Volume ");
		volumeLabel.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		volumeLabel.setForeground(Color.GREEN);
		pan.add( volumeLabel );		
		soundSlider = new JSlider(JSlider.HORIZONTAL,0,150,0);
		soundSlider.setPreferredSize(new Dimension(120,40));
		soundSlider.setBackground(Color.BLACK);
		soundSlider.setForeground(Color.GREEN);
		soundSlider.setMinimum(0);
		soundSlider.setMaximum(10);
		soundSlider.setValue(settings.getVolume());
		soundSlider.setVisible(true); 		
		SlideListener listener = new SlideListener();
		soundSlider.addChangeListener(listener); 
		pan.add(soundSlider); 
		add(pan);
		
		JPanel pan2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan2.setBackground(Color.BLACK);
		music = new JToggleButton("Music?");
		music.setSelected(settings.isMusicOn());
		music.setSize(180,60);
		music.setBackground(Color.RED);
		music.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		music.addActionListener(this);	
		pan2.add(music);
		add(pan2);
		JPanel pan3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan3.setBackground(Color.BLACK);
		menuButton = new JButton("Main Menu");
		menuButton.setSize(180,60);
		menuButton.setBackground(Color.GREEN);
		menuButton.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		menuButton.addActionListener((ActionListener) this);
		pan3.add(menuButton);
		add(pan3);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == music)
			sc.musicOnChanged(music.isSelected());
		if(e.getSource() == menuButton)
			frame.getLayout().show(frame.getCards(), "main");
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent((java.awt.Graphics) g);
	}	
	
	public void updateView()
	{
		soundSlider.setValue(settings.getVolume());
		music.setSelected(settings.isMusicOn());
	}

	private class SlideListener implements ChangeListener{
		public void stateChanged(ChangeEvent e){
				sc.volumeChanged(soundSlider.getValue());
		}
	}
} 