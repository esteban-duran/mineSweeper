package control;

import javax.swing.JButton;
import javax.swing.border.Border;

import model.GameBox;

public interface GameStyle {

	public default void setBoardStyle(GameBox[][] gameBoard) {
		Border defaultBorder = new JButton().getBorder();
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				gameBoard[i][j].getButton().setBorder(defaultBorder);
			}
		}
	}
}
