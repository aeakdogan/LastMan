import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JPanel implements KeyListener{

	Game game;
	GameController gameController;
	PlayerController playerController;
	
	Timer timer;
	
	JPanel mainPanel;
	JPanel infoPanel;
	GamePanel gamePanel;
	
	JLabel infoLabel;
	
	JButton endGameButton;
	
	JLabel tmpLabel;
	JLabel hpLabel;
	
	public GameView(Game game) {
		// TODO Auto-generated constructor stub
		
		this.game = game;
		game.setView(this);
		playerController = new PlayerController(game.getPlayer());
		
		setLayout(null);
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		//mainPanel.setPreferredSize(new Dimension(1100, 500));
		mainPanel.setSize(1100, 500);
		mainPanel.setBackground(Color.MAGENTA);
		
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(new Dimension(900, 500));
		gamePanel.setBackground(Color.GREEN);
		
		
		endGameButton = new JButton("End Game");
		endGameButton.setBackground(Color.RED);
	
		infoLabel = new JLabel("LastMan");
		infoLabel.setBackground(Color.CYAN);

		
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		infoPanel.setPreferredSize(new Dimension(200, 500));
		infoPanel.setBackground(Color.YELLOW);
		
		
		mainPanel.add(endGameButton,BorderLayout.PAGE_START);
		mainPanel.add(gamePanel,BorderLayout.CENTER);
		mainPanel.add(infoPanel,BorderLayout.LINE_END);
		
		tmpLabel = new JLabel("tmp");
		hpLabel = new JLabel("HP: " + game.getPlayer().gethP());
		
		infoPanel.add(infoLabel);
		infoPanel.add(tmpLabel);
		infoPanel.add(hpLabel);
		add(mainPanel);
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				game.update(1);
			}
		}, 0, 1000);
		
		addKeyListener(this);
	}
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);		
		
	}
	public void updateView(){
		if(game.getCurrentTime() %10 == 0){
			int randX = new Random().nextInt(900)+1;
			int randY = new Random().nextInt(500)+1;
			
			game.getGameMap().addToCurrentPacks(new Location(randX, randY), new Pack("healthBoost"));
		}
		tmpLabel.setText("left: " + game.getPlayer().getNoWeaponOneUsageFor());
		hpLabel.setText("HP: " + game.getPlayer().gethP());
		infoLabel.setText("Time: " + (game.getMaxTime() - game.getCurrentTime()));
		repaint();
	}
	public class GamePanel extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			Image wall1Image = new ImageIcon("assets\\wall1.png").getImage();
			Image wall2Image = new ImageIcon("assets\\wall2.png").getImage();
			Image killImage = new ImageIcon("assets\\killua.png").getImage();
			Image bombImage = new ImageIcon("assets\\bomb.png").getImage();
			Image packImage = new ImageIcon("assets\\pack.png").getImage();
			
			int cellSize = 40;
			int characterSize = 40;
			int packSize = 40;
			int bombSize = 40;
			

			for(Wall wall : game.getGameMap().getMap().getWalls()){
				
				//g.drawImage(wallImage,0,0,30,30,null);
				if(wall.getResistance() == 1)
					g.drawImage(wall1Image,wall.getLocation().getY() * cellSize,wall.getLocation().getX() * cellSize,cellSize,cellSize,null);
				else
					g.drawImage(wall2Image,wall.getLocation().getY() * cellSize,wall.getLocation().getX() * cellSize,cellSize,cellSize,null);
			}
			
			for(Location loc : game.getGameMap().getCurrentWeapons().keySet()){
				g.drawImage(bombImage, loc.getX(), loc.getY(), bombSize, bombSize, null);
			}
			
			for(Location loc : game.getGameMap().getCurrentPacks().keySet()){
				g.drawImage(packImage, loc.getX(), loc.getY(), packSize, packSize, null);
			}
			
			
			for(Character c : game.getAliveCharacters()){
				g.drawImage(killImage, c.getLocation().getX(), c.getLocation().getY(), characterSize, characterSize, null);
			}
			
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP){
			playerController.directionKeyPressed(CharacterController.DIRECTION_UP);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			playerController.directionKeyPressed(CharacterController.DIRECTION_RIGHT);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			playerController.directionKeyPressed(CharacterController.DIRECTION_LEFT);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			playerController.directionKeyPressed(CharacterController.DIRECTION_DOWN);
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			playerController.weaponOneKeyPressed();
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
