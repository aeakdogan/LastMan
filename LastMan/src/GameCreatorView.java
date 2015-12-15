import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import LMGraphics.LMButton;
import LMGraphics.LMComboBox;
import LMGraphics.LMLabel;
import LMGraphics.LMPanel;
import LMGraphics.LMTextArea;
import LMGraphics.LMTextField;

/**
 * @author Burcu Canakci
 *
 * GameCreatorView class initialize the views between main menu and gameview
 * in the GameCreatorView user determines number of bots, difficulty of them, select the hero and map, and set the time of game. 
 *
 */
public class GameCreatorView extends JPanel implements ActionListener, LastManView
{
	//Constants
	private static final long serialVersionUID = -8845130704051284821L;
	private final String[] levs = {"EASY","MEDIUM","HARD"};
	
	//Properties
	private GameController gController;
	private CardLayout cl;
	private JPanel botSelectionPanel, heroSelectionPanel, mapSelectionPanel, timerSelectionPanel, gameReadyPanel; 
	private LMButton botSaveButton, heroSaveButton, mapSaveButton, timerSaveButton, startGameButton, mainMenuButton;
	private LMComboBox botList, levList, heroList, mapList;
	private LMTextField timer;
	private LMTextArea game;
	private LMLabel timerMessage;
	private GameFrame frame;
	private String[] heroes, maps;
	
	//Constructor
	/**
	 * @param frame
	 * Constructor of the GameCreatorView class takes frame parameter and initialize the views into to the frame
	 * this constructor initialize Bot Selection Panel, Hero Selection Panel, Map Selection Panel, Time Selection Panel and Game Ready Panel
	 */
	public GameCreatorView(GameFrame frame)
	{
		this.frame = frame;
		gController = new GameController();
		
		cl = new CardLayout();
		setLayout(cl);
		initializeLists();
		
		Color defCol = Color.YELLOW;
				
		//Bot Selection Panel
		botSelectionPanel = new JPanel();
		botSelectionPanel.setLayout(new BoxLayout(botSelectionPanel, BoxLayout.PAGE_AXIS));
		botSelectionPanel.setBackground(Color.BLACK);
		LMPanel firstB = new LMPanel();
		LMLabel noOfBots = new LMLabel("Select number of bots: ", defCol);
		String[] nos = {"1","2","3"};
		botList = new LMComboBox(nos, defCol);
		firstB.add(noOfBots);
		firstB.add(botList);
		botSelectionPanel.add(firstB);
		LMPanel secondB = new LMPanel();
		LMLabel level = new LMLabel("Select bot level: ", defCol);
		levList = new LMComboBox(levs, defCol);
		secondB.add(level);
		secondB.add(levList);
		botSelectionPanel.add(secondB);
		LMPanel thirdB = new LMPanel();		
		botSaveButton = new LMButton("Next", defCol);
		botSaveButton.addActionListener(this);
		thirdB.add(botSaveButton);
		botSelectionPanel.add(thirdB);
		add(botSelectionPanel, "bot");
		
		//Hero Selection Panel
		heroSelectionPanel = new JPanel();
		heroSelectionPanel.setLayout(new BoxLayout(heroSelectionPanel, BoxLayout.PAGE_AXIS));
		heroSelectionPanel.setBackground(Color.BLACK);
		LMPanel firstH = new LMPanel();
		LMLabel selHero = new LMLabel("Select hero: ", defCol);
		heroList = new LMComboBox(heroes, defCol);
		firstH.add(selHero);
		firstH.add(heroList);
		heroSelectionPanel.add(firstH);
		LMPanel secondH = new LMPanel();
		heroSaveButton = new LMButton("Next", defCol);
		heroSaveButton.addActionListener(this);
		secondH.add(heroSaveButton);
		heroSelectionPanel.add(secondH);
		add(heroSelectionPanel, "hero");	
		
		//Map Selection Panel
		mapSelectionPanel = new JPanel();
		mapSelectionPanel.setLayout(new BoxLayout(mapSelectionPanel, BoxLayout.PAGE_AXIS));
		mapSelectionPanel.setBackground(Color.BLACK);
		LMPanel firstM = new LMPanel();
		LMLabel selMap = new LMLabel("Select map: ", defCol);
		mapList = new LMComboBox(maps, defCol);
		firstM.add(selMap);
		firstM.add(mapList);
		mapSelectionPanel.add(firstM);
		LMPanel secondM = new LMPanel();
		mapSaveButton = new LMButton("Next", defCol);
		mapSaveButton.addActionListener(this);
		secondM.add(mapSaveButton);
		mapSelectionPanel.add(secondM);
		add(mapSelectionPanel, "map");			
		
		//Timer Selection Panel
		timerSelectionPanel = new JPanel();
		timerSelectionPanel.setLayout(new BoxLayout(timerSelectionPanel, BoxLayout.PAGE_AXIS));
		timerSelectionPanel.setBackground(Color.BLACK);
		LMPanel firstT = new LMPanel();
		LMLabel selT = new LMLabel("Time in seconds: ", defCol);
	    timer = new LMTextField(defCol);
	    timer.setText("60");
		firstT.add(selT);
		firstT.add(timer);
		timerSelectionPanel.add(firstT);
		LMPanel secondT = new LMPanel();
		secondT.setBackground(Color.BLACK);
		timerSaveButton = new LMButton("Next", defCol);
		timerSaveButton.addActionListener(this);
		secondT.add(timerSaveButton);
		timerSelectionPanel.add(secondT);
		timerMessage = new LMLabel("Please enter a valid value, i.e. (30-300)", defCol);
		timerMessage.setAlignmentX(CENTER_ALIGNMENT);
		timerSelectionPanel.add(timerMessage);
		add(timerSelectionPanel, "time");	
		
		//game ready panel
		gameReadyPanel = new JPanel();
		gameReadyPanel.setLayout(new BoxLayout(gameReadyPanel, BoxLayout.PAGE_AXIS));
		gameReadyPanel.setBackground(Color.BLACK);
		LMPanel firstG = new LMPanel();
		game = new LMTextArea("", defCol);
		firstG.add(game);
		gameReadyPanel.add(firstG);
		LMPanel secondG = new LMPanel();
		startGameButton = new LMButton("Start", defCol);
		startGameButton.addActionListener(this);
		secondG.add(startGameButton);
		gameReadyPanel.add(secondG);
		LMPanel thirdG = new LMPanel();
		mainMenuButton = new LMButton("Main Menu", defCol);
		mainMenuButton.addActionListener(this);
		thirdG.add(mainMenuButton);
		gameReadyPanel.add(thirdG);	
		add(gameReadyPanel, "start");
		
		cl.show(this, "bot");
	}

	/**
	 * initializeList method is used for reading the Hero specifications from the file, reading map 
	 * information from the file, and throws an IO exception if the input file is not found.  
	 * 
	 */
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

	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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

	/* 
	 * @see LastManView#updateView()
	 */
	@Override
	public void updateView() 
	{
		repaint();
	}			
}
