import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameView extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 4375482311151482280L;
	private Game game;
	private GameController gameC;
	private PlayerController playerC;
	private ArrayList<BotController> botCs;
	private GameFrame frame;	
	private Timer timer;	
	private GamePanel gamePanel;	
	private JTextArea info;
	private JButton endGameButton;	
	private Sound background;
	
	
	public GameView(GameController gC, GameFrame f) 
	{
		frame = f;
		gameC = gC;
		game = gameC.getGame();
		playerC = new PlayerController(game.getPlayer());
		botCs = new ArrayList<BotController>();
		for(Character c : game.getBots())
		{
			if(c.getLevel() == Character.EASY)
				botCs.add(new EasyBotController(c));
			if(c.getLevel() == Character.MEDIUM)
				botCs.add(new MediumBotController(c));
			if(c.getLevel() == Character.HARD)
				botCs.add(new HardBotController(c));			
		}		
		game.setView(this);
		
		if(f.getSettings().isMusicOn())
		{
			background = new Sound("sounds\\background.wav");
			background.musicStart();
		}
		
		setLayout(new BorderLayout());
		setSize(1100, 500);
		setBackground(Color.MAGENTA);
		
		gamePanel = new GamePanel();
		gamePanel.setSize(new Dimension(Location.X_LIMIT, Location.Y_LIMIT));
		gamePanel.setBackground(Color.BLACK);
		gamePanel.addKeyListener(gamePanel);
		
		
		JPanel endG = new JPanel(new FlowLayout(FlowLayout.CENTER));
		endG.setBackground(Color.BLACK);
		endGameButton = new JButton("End Game");
		endGameButton.setSize(180,60);
		endGameButton.setBackground(Color.BLACK);
		endGameButton.setForeground(Color.RED);
		endGameButton.setBorderPainted(false);
		endGameButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		endGameButton.addActionListener(this);
		endG.add(endGameButton);
	
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		infoPanel.setPreferredSize(new Dimension(200, 500));
		infoPanel.setBackground(Color.BLACK);
		String infoText = "Time Left: " + (game.getMaxTime() - game.getCurrentTime()) + "\nYour Hero: " + game.getPlayer().getHero().getId()
				+ "\nYour HP: " + game.getPlayer().gethP()
				+"\nTime Left: " + game.getPlayer().getNoWeaponTwoUsageFor();
		for (Character c : game.getBots())
		{
			infoText += "\nBot Hero: " + c.getHero().getId() + "\nHP: " + c.gethP() + "\nTime Left: " + c.getNoWeaponTwoUsageFor();
		}
		info = new JTextArea(infoText);
		info.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		info.setSize(400,400);
		info.setForeground(Color.YELLOW);
		info.setBackground(Color.BLACK);
		infoPanel.add(info);
			
		
		add(endG,BorderLayout.PAGE_START);
		add(gamePanel,BorderLayout.CENTER);
		add(infoPanel,BorderLayout.LINE_END);
				
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run()
			{
				for(BotController bc : botCs)
				{
					bc.makeRandomMove();
				}
				gameC.updateGame(1);
			}
		}, 0, 1000);
		gameC.startGame();		
	}	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);		
	}
	
	public void updateView()
	{	
		String infoText = "Time Left: " + (game.getMaxTime() - game.getCurrentTime()) + "\nYour Hero: " + game.getPlayer().getHero().getId()
				+ "\nYour HP: " + game.getPlayer().gethP()
				+"\nTime Left: " + game.getPlayer().getNoWeaponTwoUsageFor();
		for (Character c : game.getBots())
		{
			infoText += "\nBot Hero: " + c.getHero().getId() + "\nHP: " + c.gethP() + "\nTime Left: " + c.getNoWeaponTwoUsageFor();
		}
		info.setText(infoText);
		repaint();
	}
	
	public class GamePanel extends JPanel implements KeyListener
	{	
		private static final long serialVersionUID = 3834437563918953922L;

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Image softwImage = new ImageIcon("images\\wall1.png").getImage();
			Image hardwImage = new ImageIcon("images\\wall2.png").getImage();
			Image bombImage = new ImageIcon("images\\bomb.png").getImage();
			Image healthpackImage = new ImageIcon("images\\healthpack.png").getImage();
			Image weaponpackImage = new ImageIcon("images\\weaponpack.png").getImage();
			
			int cellSize = Location.CELL;
			
			

			for(Wall wall : game.getGameMap().getMap().getWalls())
			{
				if(wall.getResistance() == Wall.SOFT)
					g.drawImage(softwImage,wall.getLocation().getX(),wall.getLocation().getY(),cellSize,cellSize,null);
				else
					g.drawImage(hardwImage,wall.getLocation().getX(),wall.getLocation().getY(),cellSize,cellSize,null);
			}
			
			for(Location loc : game.getGameMap().getCurrentWeapons().keySet())
			{
				Weapon w = game.getGameMap().getCurrentWeapons().get(loc);
				if(w.isFirstType())
					g.drawImage(bombImage, loc.getX(), loc.getY(), cellSize, cellSize, null);
				else
				{
					String imagePath = "images\\" + w.getCharacter().getHero().getId() + "weap.png";
					Image im = new ImageIcon(imagePath).getImage();
					g.drawImage(im, loc.getX() + Location.CELL, loc.getY(), cellSize, cellSize, null);
				}
			}
			
			for(Location loc : game.getGameMap().getCurrentPacks().keySet())
			{
				Pack p = game.getGameMap().getPackAt(loc);
				if(p.getId().equals("weaponBoost"))
					g.drawImage(weaponpackImage, loc.getX(), loc.getY(), cellSize, cellSize, null);
				else
					g.drawImage(healthpackImage, loc.getX(), loc.getY(), cellSize, cellSize, null);
			}
			
			for(Character c : game.getAliveCharacters())
			{
				Image charImage = new ImageIcon("images\\" + c.getHero().getId() + ".png").getImage();
				g.drawImage(charImage, c.getLocation().getX(), c.getLocation().getY(), cellSize, cellSize, null);
			}
			
		}
		
		public void addNotify() 
		{
	        super.addNotify();
	        requestFocus();
	    }
		
		@Override
		public void keyPressed(KeyEvent e) 
		{
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				playerC.directionKeyPressed(Location.DIRECTION_UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				playerC.directionKeyPressed(Location.DIRECTION_RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				playerC.directionKeyPressed(Location.DIRECTION_LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				playerC.directionKeyPressed(Location.DIRECTION_DOWN);
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				playerC.weaponOneKeyPressed();
			}
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				playerC.weaponTwoKeyPressed();
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == endGameButton)
			gameC.endGame();
	}
	
	public void endGame(String resultScreenInfo) 
	{	
		timer.cancel();
		if(background != null)
			background.musicStop();
		ResultsView rv = new ResultsView(resultScreenInfo, frame);
		frame.getCards().add(rv, "result");
		frame.getLayout().show(frame.getCards(), "result");
	}
	
	public void playSound(String filename)
	{
		Sound sound;
		sound = new Sound(filename);
		sound.musicStart();		
	}
	
	//http://stackoverflow.com/questions/23986793/java-playing-a-sound-on-top-of-background-music
	private class Sound
	{
	    private AudioInputStream ais = null;
	    private Clip clip = null;

	    public Sound(String fileName)
	    {
            try 
            {
				clip = AudioSystem.getClip();
				ais = AudioSystem.getAudioInputStream(new File(fileName));
	            clip.open(ais);
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) 
            {
				e.printStackTrace();
			}
	    }
	    public void musicStart ()
	    {
	        clip.start();
	    }

	    public void musicStop ()
	    {
	        clip.stop();
	    }
	}
}