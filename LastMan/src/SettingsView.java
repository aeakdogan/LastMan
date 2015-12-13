import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider; 

public class SettingsView extends JPanel implements ActionListener, ChangeListener{
	private static final long serialVersionUID = 5786938794922429875L;
	
	//Properties
	private JLabel volumeLabel;
	private JLabel settingsLabel; 
//	private JSpinner increaseSpinner;
	private JSlider soundSlider; 
	private SettingsController sc;
	private Settings settings;
	private JPanel infoPanel; 
	private JButton menuButton; 
	
	//Constructor
	public SettingsView(Settings settings)
	{
		this.settings = settings;
		settings.setView(this);
		sc = new SettingsController(settings);
		
		infoPanel = new JPanel(); 
		infoPanel.setLayout(null);
		infoPanel.setPreferredSize(new Dimension(1100,600)); 
		infoPanel.setBackground(Color.BLACK);
		
		settingsLabel = new JLabel();
		settingsLabel.setSize(100,100);
		settingsLabel.setText("SETTINGS");
		settingsLabel.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		settingsLabel.setForeground(Color.GREEN);
		settingsLabel.setLocation(400,0);
		infoPanel.add( settingsLabel );
		
		menuButton = new JButton("MAIN MENU");
		menuButton.setSize(180,60);
		menuButton.setLocation(900,500);
		menuButton.setBackground(Color.GREEN);
		menuButton.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		menuButton.addActionListener((ActionListener) this);
		infoPanel.add(menuButton); 
		
		volumeLabel = new JLabel();
		volumeLabel.setSize(400,400);
		volumeLabel.setText( "Volume: " + settings.getVolume() );
		volumeLabel.setFont(new Font("Segoe UI", Font.BOLD|Font.BOLD, 20));
		volumeLabel.setForeground(Color.GREEN);
		volumeLabel.setLocation(300,100);
		infoPanel.add( volumeLabel );
		
		
		soundSlider = new JSlider(JSlider.HORIZONTAL,0,150,0);
		soundSlider.setSize(120,40);
		soundSlider.setLocation(450,280);
		soundSlider.setMinimum(0);
		soundSlider.setMaximum(10);
		soundSlider.setValue(5);
		soundSlider.setVisible(true); 
		
		SlideListener listener = new SlideListener();
		soundSlider.addChangeListener(listener); 
		infoPanel.add(soundSlider); 
		
		
//		SpinnerModel increaseSpinnerModel = new SpinnerNumberModel(settings.getVolume(), 0, 10, 1);
//		increaseSpinner = new JSpinner(increaseSpinnerModel);
//		increaseSpinner.setSize(100,200);
//		increaseSpinner.setLocation(300,200);
//		increaseSpinner.setEditor(new JSpinner.DefaultEditor(increaseSpinner));
//		increaseSpinner.addChangeListener(this);
//		infoPanel.add(increaseSpinner);
	
		this.add(infoPanel); 
		setVisible( true );			
		
		
	}

	public void actionPerformed(ActionEvent e){
		int newFrame = 2;
		if(e.getSource() == menuButton)
			newFrame = Framework.MAIN_MENU_ID;
		Framework.switchMenu(newFrame);
	}
	
	private class SlideListener implements ChangeListener{
		public void stateChanged(ChangeEvent e){
				settings.setVolume(soundSlider.getValue());
		}
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent((java.awt.Graphics) g);
	}	
	
	public void updateView()
	{
		volumeLabel.setText( "Volume: " + settings.getVolume() );
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void stateChanged(ChangeEvent e) {
//		if(e.getSource() == increaseSpinner)
//			sc.setVolume((int)increaseSpinner.getValue());			
//	}	

} 