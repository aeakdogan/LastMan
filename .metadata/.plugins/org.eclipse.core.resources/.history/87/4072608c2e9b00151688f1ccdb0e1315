import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.sun.prism.Graphics;
import javax.swing.JLabel;

public class SettingsView extends JPanel implements ActionListener, ChangeListener
{
	private static final long serialVersionUID = 5786938794922429875L;
	
	//Properties
	private JLabel volumeLabel;
	private JSpinner increaseSpinner;
	private SettingsController sc;
	private Settings settings;
	
	//Constructor
	public SettingsView(Settings settings)
	{
		this.settings = settings;
		settings.setView(this);
		sc = new SettingsController(settings);
		
		volumeLabel = new JLabel();
		volumeLabel.setText( "Volume: " + settings.getVolume() );
		this.add( volumeLabel );
		
		SpinnerModel increaseSpinnerModel = new SpinnerNumberModel(settings.getVolume(), 0, 10, 1);
		increaseSpinner = new JSpinner(increaseSpinnerModel);
		increaseSpinner.setEditor(new JSpinner.DefaultEditor(increaseSpinner));
		increaseSpinner.addChangeListener(this);
		this.add(increaseSpinner);
	
		setVisible( true );			
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent((java.awt.Graphics) g);
	}	
	
	public void updateView()
	{
		volumeLabel.setText( "Volume: " + settings.getVolume() );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == increaseSpinner)
			sc.setVolume((int)increaseSpinner.getValue());			
	}	

} 

	
