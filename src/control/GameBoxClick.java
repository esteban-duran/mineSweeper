package control;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import model.BombBox;
import model.GameBox;

public class GameBoxClick extends MouseAdapter {

	private GameBox gameBox;

	public GameBoxClick(GameBox gameBox) {
		this.gameBox = gameBox;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (gameBox.getButton().isEnabled()) {
			if (!gameBox.isFlagActive()) {

				if (SwingUtilities.isRightMouseButton(e)) {
					if (GameBoard.getInstance().flagsRemaining()) {
						gameBox.getButton().setIcon(scaleImageIcon(GameBox.URL_IMG_FLAG));
						gameBox.getButton().setText(null);
						gameBox.setFlagActive(true);
						GameBoard.getInstance().addFlagAmount();
					}
				} else {
					if (!gameBox.isEmpty()) {
						gameBox.getButton().setText("" + gameBox.getNumber());
						gameBox.getButton().setEnabled(false);
					} else {
						if (gameBox instanceof BombBox) {
							gameBox.getButton().setIcon(scaleImageIcon(GameBox.URL_IMG_BOMB));
							gameBox.getButton().setBackground(Color.RED);
							gameBox.getButton().setText(null);
							gameBox.getButton().setEnabled(false);
							GameBoard.getInstance().lostGame();
						} else {

							gameBox.getButton().setEnabled(false);
						}
						gameBox.changed();
						gameBox.notifyObservers();
					}
				}
			} else if (SwingUtilities.isRightMouseButton(e)){
				gameBox.getButton().setIcon(null);
				gameBox.setFlagActive(false);
				GameBoard.getInstance().subFlagAmount();
			}
		}
	}

	public static ImageIcon scaleImageIcon(String url) {
		ImageIcon imageIcon = new ImageIcon(url);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(GameBox.IMG_SIZE, GameBox.IMG_SIZE, Image.SCALE_DEFAULT);
		return new ImageIcon(newimg);
	}

}
