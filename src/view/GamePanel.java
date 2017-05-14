package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.GameBox;

public class GamePanel extends JPanel {

	public GamePanel(GameBox[][] board) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				gbc.gridx = x;
				gbc.gridy = y;
				add(board[x][y].getButton(), gbc);
			}
		}
		setVisible(true);
	}
}
