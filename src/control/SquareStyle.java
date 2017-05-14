package control;

import java.awt.Color;

import javax.swing.BorderFactory;

import model.GameBox;

public class SquareStyle implements GameStyle {

	@Override
	public void setBoardStyle(GameBox[][] gameBoard) {
		GameStyle.super.setBoardStyle(gameBoard);
		
		for (int i = 0; i < gameBoard.length; i++) {
			gameBoard[0][i].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.CYAN, Color.CYAN));
		}
		for (int i = 1; i < gameBoard.length; i++) {
			gameBoard[i][0].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.CYAN, Color.CYAN));
		}
		for (int i = 1; i < gameBoard.length; i++) {
			gameBoard[i][gameBoard.length - 1].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.CYAN, Color.CYAN));
		}
		for (int i = 1; i < gameBoard.length - 1; i++) {
			gameBoard[gameBoard.length - 1][i].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.CYAN, Color.CYAN));
		}
	}

}
