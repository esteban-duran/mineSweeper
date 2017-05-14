package control;

import java.awt.Color;

import javax.swing.BorderFactory;

import model.GameBox;

public class XStyle implements GameStyle{

	@Override
	public void setBoardStyle(GameBox[][] gameBoard) {
		GameStyle.super.setBoardStyle(gameBoard);
		
		for (int i = 0; i < gameBoard.length; i++) {
			gameBoard[i][i].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.ORANGE, Color.ORANGE));
		}
		for (int i = gameBoard.length-1, j = 0; i >= 0; i--, j++) {
			gameBoard[j][i].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.ORANGE, Color.ORANGE));
		}
	}

}
