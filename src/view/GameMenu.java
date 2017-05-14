package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.GameBoard;
import control.GameStyleType;
import control.MenuLevelClick;
import control.MenuStyleClick;

public class GameMenu {

	private JMenuBar menuBar;
	
	public GameMenu(){
		menuBar = new JMenuBar();
		//-----------Levels-----------------
		JMenu gameLevels = new JMenu("Levels");		
		JMenuItem menuLevelEasy = new JMenuItem("Easy");
		menuLevelEasy.addActionListener(new MenuLevelClick(GameBoard.LEVEL_EASY));
		
		JMenuItem menuLevelMedium = new JMenuItem("Medium");
		menuLevelMedium.addActionListener(new MenuLevelClick(GameBoard.LEVEL_MEDIUM));
		
		JMenuItem menuLevelAdvanced = new JMenuItem("Advanced");
		menuLevelAdvanced.addActionListener(new MenuLevelClick(GameBoard.LEVEL_ADVANCED));
		
		JMenuItem menuLevelPro = new JMenuItem("Pro");
		menuLevelPro.addActionListener(new MenuLevelClick(GameBoard.LEVEL_PRO));
		
		gameLevels.add(menuLevelEasy);
		gameLevels.add(menuLevelMedium);
		gameLevels.add(menuLevelAdvanced);
		gameLevels.add(menuLevelPro);
		
		//------------Styles---------------
		JMenu gameStyles = new JMenu("Styles");
		JMenuItem menuStyleSquare = new JMenuItem("Square");
		menuStyleSquare.addActionListener(new MenuStyleClick(GameStyleType.SQUARE_TYPE));
		
		JMenuItem menuStyleCross = new JMenuItem("Cross");
		menuStyleCross.addActionListener(new MenuStyleClick(GameStyleType.CROSS_TYPE));
		
		JMenuItem menuStyleX = new JMenuItem("X");
		menuStyleX.addActionListener(new MenuStyleClick(GameStyleType.X_STYLE));
		
		gameStyles.add(menuStyleSquare);
		gameStyles.add(menuStyleCross);
		gameStyles.add(menuStyleX);
		
		menuBar.add(gameLevels);
		menuBar.add(gameStyles);
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}
}
