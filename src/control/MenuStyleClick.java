package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MenuStyleClick implements ActionListener {

	private GameStyleType style;

	public MenuStyleClick(GameStyleType style) {
		this.style = style;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			GameBoard gameBoard = GameBoard.getInstance();
			gameBoard.setGameStyle(style);
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "You must select a level first", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
