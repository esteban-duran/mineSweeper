package control;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.BombBox;
import model.GameBox;
import view.GameControlPanel;
import view.GameMenu;
import view.GamePanel;
import view.SmileyBlackWhiteDecorator;
import view.SmileyButton;
import view.SmileyDecorator;
import view.SmileyMouseDecorator;

@SuppressWarnings("serial")
public class GameBoard extends JFrame {

	public static final int LEVEL_EASY = 8;
	public static final int LEVEL_MEDIUM = 10;
	public static final int LEVEL_ADVANCED = 14;
	public static final int LEVEL_PRO = 16;

	private GameMenu gameMenu;
	private static GameBoard instance;
	private GameBox[][] board;
	private int gameSize;
	private GameBoxFactory gameBoxFactory;
	private HashMap<GameStyleType, GameStyle> gameStyles;
	private SmileyDecorator smileyButton;

	// ------- BOMB COUNTER
	private int bombsAmount;
	private int flagAmount;
	private JLabel lblBombs;
	private String bombText;

	// ------- TIMER
	private TimeCounter timeCounter;
	private JLabel lblTimer;
	private GridBagConstraints gbc;

	// ----- STATUS
	private GameStatus gameStatus;

	private GameBoard() {
		gameBoxFactory = new GameBoxFactory();
		gameMenu = new GameMenu();
		gameStyles = new HashMap<>();

		// ------- BOMB COUNTER
		lblBombs = new JLabel();

		// ------- TIMER
		lblTimer = new JLabel();

	}

	public static GameBoard getInstance() {
		if (instance == null)
			instance = new GameBoard();
		return instance;
	}

	private boolean isValidCord(int x, int y) {
		if (x < 0 || y < 0 || x > gameSize - 1 || y > gameSize - 1)
			return false;
		else
			return true;
	}

	public GameBox getGameBoxByCords(int x, int y) {
		if (!isValidCord(x, y))
			return null;
		else
			return board[x][y];
	}

	public void observerNeighbors(int x, int y) {
		if (isValidCord(x - 1, y - 1))
			board[x][y].addObserver(board[x - 1][y - 1]);
		if (isValidCord(x - 1, y))
			board[x][y].addObserver(board[x - 1][y]);
		if (isValidCord(x - 1, y + 1))
			board[x][y].addObserver(board[x - 1][y + 1]);
		if (isValidCord(x, y - 1))
			board[x][y].addObserver(board[x][y - 1]);
		if (isValidCord(x, y + 1))
			board[x][y].addObserver(board[x][y + 1]);
		if (isValidCord(x + 1, y - 1))
			board[x][y].addObserver(board[x + 1][y - 1]);
		if (isValidCord(x + 1, y))
			board[x][y].addObserver(board[x + 1][y]);
		if (isValidCord(x + 1, y + 1))
			board[x][y].addObserver(board[x + 1][y + 1]);
	}

	private void initBoard() {
		board = new GameBox[gameSize][gameSize];
		smileyButton = new SmileyMouseDecorator(new SmileyBlackWhiteDecorator(new SmileyButton()));

		int numberOfBombs = (int) Math.round((new Double(gameSize) / new Double(64)) * Math.pow(gameSize, 2));
		bombsAmount = numberOfBombs;
		flagAmount = 0;
		Random random = new Random();
		HashMap<Integer, Integer> bombs = new HashMap<>();

		while (bombs.size() < numberOfBombs) {
			int bombId = random.nextInt((int) Math.pow(gameSize, 2));
			if (bombs.get(bombId) == null) {
				bombs.put(bombId, bombId);
			}
		}

		int id = 0;
		for (int x = 0; x < gameSize; x++) {
			for (int y = 0; y < gameSize; y++, id++) {
				boolean isBomb = bombs.get(id) != null;
				if (isBomb)
					board[x][y] = gameBoxFactory.createGameBox(GameBoxType.BOMB_BOX);
				else
					board[x][y] = gameBoxFactory.createGameBox(GameBoxType.NUMBER_BOX);
			}
		}

		// config method needs all game boxes to be initialized prior its
		// invocation
		id = 0;
		for (int x = 0; x < gameSize; x++) {
			for (int y = 0; y < gameSize; y++, id++) {
				board[x][y].config(id, gameSize);
				if (!(board[x][y] instanceof BombBox)) {
					observerNeighbors(x, y);
				} else {
					board[x][y].addObserver(smileyButton);
				}
			}
		}
	}

	private GameStyle getGameStyle(GameStyleType type) {
		GameStyle gameStyle = gameStyles.get(type);
		if (gameStyle == null) {
			if (type.equals(GameStyleType.SQUARE_TYPE))
				gameStyle = new SquareStyle();
			if (type.equals(GameStyleType.CROSS_TYPE))
				gameStyle = new CrossStyle();
			if (type.equals(GameStyleType.X_STYLE))
				gameStyle = new XStyle();

			gameStyles.put(type, gameStyle);
		}
		return gameStyle;
	}

	public void setGameStyle(GameStyleType type) {
		getGameStyle(type).setBoardStyle(board);
	}

	public void setGameSize(int gameSize) {
		this.gameSize = gameSize;
	}

	public void startGame() {
		initBoard();
		getContentPane().removeAll();
		gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		getContentPane().add(new GamePanel(board), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		getContentPane().add(new GameControlPanel(smileyButton), gbc);

		// -----------------------------------------------
		// ------------- BOMB LABEL SET UP ---------------
		// -----------------------------------------------

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		lblBombs.setBackground(Color.BLACK);
		lblBombs.setForeground(Color.WHITE);
		lblBombs.setOpaque(true);
		updateFlagCounter();
		lblBombs.setText(bombText);
		lblBombs.setFont(lblBombs.getFont().deriveFont(35.0f));
		getContentPane().add(lblBombs, gbc);

		// -----------------------------------------------
		// ------------- TIME LABEL SET UP ---------------
		// -----------------------------------------------

		// ------------ TIMER INITIALIZATION -------------
		if (timeCounter == null) {
			timeCounter = new TimeCounter(lblTimer);
			timeCounter.start();
		} else if (timeCounter.isAlive())
			timeCounter.reboot();

		// ------------ TIMER INITIALIZATION -------------

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		lblTimer.setBackground(Color.BLACK);
		lblTimer.setForeground(Color.RED);
		lblTimer.setOpaque(true);
		lblTimer.setText("00:00");
		lblTimer.setFont(lblTimer.getFont().deriveFont(35.0f));
		getContentPane().add(lblTimer, gbc);
		// -----------------------------------------------

		pack();
	}

	public boolean flagsRemaining() {
		return (bombsAmount > flagAmount) ? true : false;
	}

	public void addFlagAmount() {
		flagAmount++;
		updateFlagCounter();
	}

	public void subFlagAmount() {
		flagAmount--;
		updateFlagCounter();
	}

	public void updateFlagCounter() {
		bombText = "         " + (bombsAmount - flagAmount);
		lblBombs.setText(bombText);
	}

	public void lostGame() {

		// ------------- REPLACE TIMER LABEL TEMPORARILY
		String timeOfLoss = lblTimer.getText();
		JLabel newTimer = new JLabel(timeOfLoss);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		newTimer.setBackground(Color.BLACK);
		newTimer.setForeground(Color.RED);
		newTimer.setOpaque(true);
		newTimer.setFont(newTimer.getFont().deriveFont(35.0f));
		getContentPane().remove(lblTimer);
		getContentPane().add(newTimer, gbc);
		// ------------- REPLACE TIMER LABEL TEMPORARILY

		// ------------- DISCOVER EVERY BOMB ON THE BOARD
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				GameBox gameBox = board[i][j];
				gameBox.getButton().setEnabled(false);
				JButton btn = gameBox.getButton();
				if (gameBox instanceof BombBox) {
					btn.setIcon(GameBoxClick.scaleImageIcon(GameBox.URL_IMG_BOMB));
					btn.setText(null);
				}
			}
		}
	}

	public static void main(String[] args) {
		GameBoard gameBoard = GameBoard.getInstance();
		gameBoard.setTitle("Minesweeper");
		gameBoard.setJMenuBar(gameBoard.gameMenu.getMenuBar());
		gameBoard.setVisible(true);
		gameBoard.setLayout(new GridBagLayout());
		gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameBoard.setLocationRelativeTo(null);
		gameBoard.setResizable(false);
		gameBoard.setSize(500, 500);
		gameBoard.setGameSize(LEVEL_EASY);
		gameBoard.startGame();

		/*
		 * UNCOMMENT FOR STATUS WINDOW 
		 * gameBoard.gameStatus = new
		 * GameStatus(LEVEL_EASY); gameBoard.gameStatus.start();
		 */

	}
	// -----------------------------------------------
	// ------------ GETTERS & SETTERS ----------------
	// -----------------------------------------------

	public GameMenu getGameMenu() {
		return gameMenu;
	}

	public void setGameMenu(GameMenu gameMenu) {
		this.gameMenu = gameMenu;
	}

	public GameBox[][] getBoard() {
		return board;
	}

	public void setBoard(GameBox[][] board) {
		this.board = board;
	}

	public GameBoxFactory getGameBoxFactory() {
		return gameBoxFactory;
	}

	public void setGameBoxFactory(GameBoxFactory gameBoxFactory) {
		this.gameBoxFactory = gameBoxFactory;
	}

	public HashMap<GameStyleType, GameStyle> getGameStyles() {
		return gameStyles;
	}

	public void setGameStyles(HashMap<GameStyleType, GameStyle> gameStyles) {
		this.gameStyles = gameStyles;
	}

	public SmileyDecorator getSmileyButton() {
		return smileyButton;
	}

	public void setSmileyButton(SmileyDecorator smileyButton) {
		this.smileyButton = smileyButton;
	}

	public int getBombsAmount() {
		return bombsAmount;
	}

	public void setBombsAmount(int bombsAmount) {
		this.bombsAmount = bombsAmount;
	}

	public int getFlagAmount() {
		return flagAmount;
	}

	public void setFlagAmount(int flagAmount) {
		this.flagAmount = flagAmount;
	}

	public JLabel getLblBombs() {
		return lblBombs;
	}

	public void setLblBombs(JLabel lblBombs) {
		this.lblBombs = lblBombs;
	}

	public String getBombText() {
		return bombText;
	}

	public void setBombText(String bombText) {
		this.bombText = bombText;
	}

	public TimeCounter getTimeCounter() {
		return timeCounter;
	}

	public void setTimeCounter(TimeCounter timeCounter) {
		this.timeCounter = timeCounter;
	}

	public JLabel getLblTimer() {
		return lblTimer;
	}

	public void setLblTimer(JLabel lblTimer) {
		this.lblTimer = lblTimer;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}

	public int getGameSize() {
		return gameSize;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
}
