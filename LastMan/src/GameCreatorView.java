import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameCreatorView extends JPanel implements ActionListener
{

	private static final long serialVersionUID = -8845130704051284821L;
	private final String[] levs = {"EASY","MEDIUM","HARD"};
	
	private GameController gController;
	private CardLayout cl;
	private JPanel botSelectionPanel, heroSelectionPanel, mapSelectionPanel, timerSelectionPanel, gameReadyPanel; 
	private JButton botSaveButton, heroSaveButton, mapSaveButton, timerSaveButton, startGameButton, mainMenuButton;
	private JComboBox<String> botList, levList, heroList, mapList;
	private JTextField timer;
	private JTextArea game;
	private JLabel timerMessage;
	private GameFrame frame;
	private String[] heroes, maps;
	
	public GameCreatorView(GameFrame frame)
	{
		this.frame = frame;
		gController = new GameController();
		
		cl = new CardLayout();
		setLayout(cl);
		initializeLists();
		//bot selection panel
		botSelectionPanel = new JPanel();
		botSelectionPanel.setLayout(new BoxLayout(botSelectionPanel, BoxLayout.PAGE_AXIS));
		botSelectionPanel.setBackground(Color.BLACK);
		JPanel firstB = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstB.setBackground(Color.BLACK);
		JLabel noOfBots = new JLabel("Select number of bots: ");
		noOfBots.setForeground(Color.YELLOW);
		noOfBots.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		String[] nos = {"1","2","3"};
		botList = new JComboBox<String>(nos);
		botList.setSelectedIndex(0);
		botList.setBackground(Color.BLACK);
		botList.setForeground(Color.YELLOW);
		botList.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		firstB.add(noOfBots);
		firstB.add(botList);
		botSelectionPanel.add(firstB);
		JPanel secondB = new JPanel(new FlowLayout(FlowLayout.CENTER));
		secondB.setBackground(Color.BLACK);
		JLabel level = new JLabel("Select bot level: ");
		level.setForeground(Color.YELLOW);
		level.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		levList = new JComboBox<String>(levs);
		levList.setSelectedIndex(0);
		levList.setBackground(Color.BLACK);
		levList.setForeground(Color.YELLOW);
		levList.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		secondB.add(level);
		secondB.add(levList);
		JPanel thirdB = new JPanel(new FlowLayout(FlowLayout.CENTER));
		thirdB.setBackground(Color.BLACK);
		botSelectionPanel.add(secondB);
		botSaveButton = new JButton("Next");
		botSaveButton.setBackground(Color.BLACK);
		botSaveButton.setForeground(Color.YELLOW);
		botSaveButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		botSaveButton.setSize( new Dimension( 500, 50 ) );
		botSaveButton.setBorderPainted(false);
		botSaveButton.addActionListener(this);
		thirdB.add(botSaveButton);
		botSelectionPanel.add(thirdB);
		add(botSelectionPanel, "bot");
		
		//hero selection panel
		heroSelectionPanel = new JPanel();
		heroSelectionPanel.setLayout(new BoxLayout(heroSelectionPanel, BoxLayout.PAGE_AXIS));
		heroSelectionPanel.setBackground(Color.BLACK);
		JPanel firstH = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstH.setBackground(Color.BLACK);
		JLabel selHero = new JLabel("Select hero: ");
		selHero.setForeground(Color.YELLOW);
		selHero.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		heroList = new JComboBox<String>(heroes);
		heroList.setSelectedIndex(0);
		heroList.setBackground(Color.BLACK);
		heroList.setForeground(Color.YELLOW);
		heroList.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		firstH.add(selHero);
		firstH.add(heroList);
		heroSelectionPanel.add(firstH);
		JPanel secondH = new JPanel(new FlowLayout(FlowLayout.CENTER));
		secondH.setBackground(Color.BLACK);
		heroSaveButton = new JButton("Next");
		heroSaveButton.setBackground(Color.BLACK);
		heroSaveButton.setForeground(Color.YELLOW);
		heroSaveButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		heroSaveButton.setSize( new Dimension( 500, 50 ) );
		heroSaveButton.setBorderPainted(false);
		heroSaveButton.addActionListener(this);
		secondH.add(heroSaveButton);
		heroSelectionPanel.add(secondH);
		add(heroSelectionPanel, "hero");	
		
		//map selection panel
		mapSelectionPanel = new JPanel();
		mapSelectionPanel.setLayout(new BoxLayout(mapSelectionPanel, BoxLayout.PAGE_AXIS));
		mapSelectionPanel.setBackground(Color.BLACK);
		JPanel firstM = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstM.setBackground(Color.BLACK);
		JLabel selMap = new JLabel("Select map: ");
		selMap.setForeground(Color.YELLOW);
		selMap.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		mapList = new JComboBox<String>(maps);
		mapList.setSelectedIndex(0);
		mapList.setBackground(Color.BLACK);
		mapList.setForeground(Color.YELLOW);
		mapList.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		firstM.add(selMap);
		firstM.add(mapList);
		mapSelectionPanel.add(firstM);
		JPanel secondM = new JPanel(new FlowLayout(FlowLayout.CENTER));
		secondM.setBackground(Color.BLACK);
		mapSaveButton = new JButton("Next");
		mapSaveButton.setBackground(Color.BLACK);
		mapSaveButton.setForeground(Color.YELLOW);
		mapSaveButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		mapSaveButton.setSize( new Dimension( 500, 50 ) );
		mapSaveButton.setBorderPainted(false);
		mapSaveButton.addActionListener(this);
		secondM.add(mapSaveButton);
		mapSelectionPanel.add(secondM);
		add(mapSelectionPanel, "map");			
		
		//timer selection panel
		timerSelectionPanel = new JPanel();
		timerSelectionPanel.setLayout(new BoxLayout(timerSelectionPanel, BoxLayout.PAGE_AXIS));
		timerSelectionPanel.setBackground(Color.BLACK);
		JPanel firstT = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstT.setBackground(Color.BLACK);
		JLabel selT = new JLabel("Time in seconds: ");
		selT.setForeground(Color.YELLOW);
		selT.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
	    timer = new JTextField();
	    timer.setText("60");
		timer.setBackground(Color.BLACK);
		timer.setForeground(Color.YELLOW);
		timer.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		timer.setPreferredSize( new Dimension( 500, 50 ));
		firstT.add(selT);
		firstT.add(timer);
		timerSelectionPanel.add(firstT);
		JPanel secondT = new JPanel(new FlowLayout(FlowLayout.CENTER));
		secondT.setBackground(Color.BLACK);
		timerSaveButton = new JButton("Next");
		timerSaveButton.setBackground(Color.BLACK);
		timerSaveButton.setForeground(Color.YELLOW);
		timerSaveButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		timerSaveButton.setSize( new Dimension( 500, 50 ) );
		timerSaveButton.setBorderPainted(false);
		timerSaveButton.addActionListener(this);
		secondT.add(timerSaveButton);
		timerSelectionPanel.add(secondT);
		timerMessage = new JLabel("Please enter a valid value, i.e. (30-300)");
		timerMessage.setForeground(Color.YELLOW);
		timerMessage.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		timerMessage.setAlignmentX(CENTER_ALIGNMENT);
		timerSelectionPanel.add(timerMessage);
		add(timerSelectionPanel, "time");	
		
		//game ready panel
		gameReadyPanel = new JPanel();
		gameReadyPanel.setLayout(new BoxLayout(gameReadyPanel, BoxLayout.PAGE_AXIS));
		gameReadyPanel.setBackground(Color.BLACK);
		JPanel firstG = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstG.setBackground(Color.BLACK);
		game = new JTextArea();
		game.setBackground(Color.BLACK);
		game.setForeground(Color.YELLOW);
		game.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		firstG.add(game);
		gameReadyPanel.add(firstG);
		JPanel secondG = new JPanel(new FlowLayout(FlowLayout.CENTER));
		secondG.setBackground(Color.BLACK);
		startGameButton = new JButton("Start");
		startGameButton.setBackground(Color.BLACK);
		startGameButton.setForeground(Color.YELLOW);
		startGameButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		startGameButton.setSize( new Dimension( 500, 50 ) );
		startGameButton.setBorderPainted(false);
		startGameButton.addActionListener(this);
		secondG.add(startGameButton);
		gameReadyPanel.add(secondG);
		JPanel thirdG = new JPanel(new FlowLayout(FlowLayout.CENTER));
		thirdG.setBackground(Color.BLACK);
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setBackground(Color.BLACK);
		mainMenuButton.setForeground(Color.YELLOW);
		mainMenuButton.setFont(new Font("Book Antiqua", Font.BOLD|Font.BOLD, 20));
		mainMenuButton.setSize( new Dimension( 500, 50 ) );
		mainMenuButton.setBorderPainted(false);
		mainMenuButton.addActionListener(this);
		thirdG.add(mainMenuButton);
		gameReadyPanel.add(thirdG);
	
		add(gameReadyPanel, "start");	
		cl.show(this, "bot");
	}

	private void initializeLists() 
	{
		try 
		{
			Scanner heroInfo = new Scanner(new FileReader("docs//herolist.txt"));
			int heroSize = heroInfo.nextInt();
			heroes = new String[heroSize];
			int index = 0;
			while (heroInfo.hasNext())
			{
				heroes[index++] = heroInfo.next();
			}
			heroInfo.close();
			
			Scanner mapInfo = new Scanner(new FileReader("docs//maplist.txt"));
			int mapSize = mapInfo.nextInt();
			maps = new String[mapSize];
			index = 0;
			while (mapInfo.hasNext())
			{
				maps[index++] = mapInfo.next();
			}
			mapInfo.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == botSaveButton)
		{
			int botNo = botList.getSelectedIndex() + 1;
			int botLevel = levList.getSelectedIndex() - 3;
			gController.charactersSelected(botNo, botLevel);
			cl.show(this, "hero");
		}
		if(e.getSource() == heroSaveButton)
		{
			String heroId = heroes[heroList.getSelectedIndex()];
			gController.heroSelected(heroId);
			cl.show(this, "map");
		}
		if(e.getSource() == mapSaveButton)
		{
			String mapId = maps[mapList.getSelectedIndex()];
			gController.mapSelected(mapId);
			cl.show(this, "time");
		}
		if(e.getSource() == timerSaveButton)
		{
			try
			{
				int timeValue = Integer.parseInt(timer.getText());
				boolean done = gController.timerSelected(timeValue);
				if(!done)
				{
					timerMessage.setText("I said please :) (30 - 300)");
					cl.show(this, "time");
				}
				else
				{
					String gameInfo = "GAME INFORMATION:\nNo of Bots: " + (gController.getGame().getAliveCharacters().size() - 1) + "\nBot Level: " 
							+ levs[(gController.getGame().getAliveCharacters().get(0).getLevel())+3] + "\nYour Hero: " + gController.getGame().getPlayer().getHero().getId() +
							"\nGame Map: " + gController.getGame().getGameMap().getMap().getId() + "\nMaximum Time: " + gController.getGame().getMaxTime()
							+ "\nSTART?";
					game.setText(gameInfo);
					cl.show(this, "start");
				}
			}
			catch(NumberFormatException ex)
			{
				timerMessage.setText("Time tends to be in numbers :) (30 - 300)");
				cl.show(this, "time");
			}			
		}
		
		if(e.getSource() == startGameButton)
		{
			GameView gv = new GameView(gController, frame);
			frame.getCards().add(gv, "game");
			frame.getLayout().show(frame.getCards(), "game");
		}
		
		if(e.getSource() == mainMenuButton)
		{
			frame.getLayout().show(frame.getCards(), "main");
		}
	}
	
	
	
}
