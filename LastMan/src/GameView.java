import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameView extends JPanel{

	Game game;
	GameController gameController;
	Timer timer;
	
	JPanel mainPanel;
	JPanel gamePanel;
	
	JLabel infoLabel;
	
	JButton endGameButton;
	
	
	public GameView(Game game) {
		// TODO Auto-generated constructor stub
		
		this.game = game;
		
		
		setLayout(new BorderLayout());
		setSize(1000,500);
		setBackground(Color.GREEN);
		
		gamePanel = new JPanel();
		gamePanel.setBackground(Color.GREEN);
		
		
		endGameButton = new JButton("End Game");
		endGameButton.setBackground(Color.RED);
	
		infoLabel = new JLabel("LastMan");
		infoLabel.setBackground(Color.CYAN);

		//add(endGameButton,BorderLayout.PAGE_START);
		//add(gamePanel,BorderLayout.CENTER);
		//add(infoLabel,BorderLayout.PAGE_END);
				
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Image wall1Image = new ImageIcon("assets\\wall1.png").getImage();
		Image wall2Image = new ImageIcon("assets\\wall2.png").getImage();
		
		int cellSize = 45;
		

		for(Wall wall : game.getGameMap().getMap().getWalls()){
			
			//g.drawImage(wallImage,0,0,30,30,null);
			if(wall.getResistance() == 1)
				g.drawImage(wall1Image,wall.getLocation().y * cellSize,wall.getLocation().x * cellSize,cellSize,cellSize,null);
			else
				g.drawImage(wall2Image,wall.getLocation().y * cellSize,wall.getLocation().x * cellSize,cellSize,cellSize,null);
		}
		
	}
}
