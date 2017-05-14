package control;

import java.awt.Color;

import javax.swing.BorderFactory;

import model.GameBox;

public class CrossStyle implements GameStyle {

	@Override
	public void setBoardStyle(GameBox[][] gameBoard) {
		GameStyle.super.setBoardStyle(gameBoard);
		
		for (int i = 0; i < gameBoard.length; i++) {
			gameBoard[gameBoard.length / 2][i].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.RED, Color.RED));
			gameBoard[(gameBoard.length / 2) - 1][i].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.RED, Color.RED));
			gameBoard[i][gameBoard.length / 2].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.RED, Color.RED));
			gameBoard[i][(gameBoard.length / 2) - 1].getButton().setBorder(BorderFactory.createBevelBorder(1,Color.RED, Color.RED));
		}
	}

}
