package control;

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
		if (SwingUtilities.isRightMouseButton(e)) {
			if (!gameBox.isFlagActive()) {
				gameBox.getButton().setIcon(scaleImageIcon(GameBox.URL_IMG_FLAG));
				gameBox.getButton().setText(null);
				gameBox.setFlagActive(true);
			} else {
				gameBox.getButton().setIcon(null);
				gameBox.setFlagActive(false);
			}
		} else {
			if (!gameBox.isEmpty()) {
				gameBox.getButton().setText("" + gameBox.getNumber());
				gameBox.getButton().setEnabled(false);
				if (gameBox.isFlagActive())
					gameBox.getButton().setIcon(null);
			} else {
				if (gameBox instanceof BombBox) {
					gameBox.getButton().setIcon(scaleImageIcon(GameBox.URL_IMG_BOMB));
					gameBox.getButton().setText(null);
					gameBox.getButton().setEnabled(false);
					
				} else {
					gameBox.getButton().setEnabled(false);
					if (gameBox.isFlagActive())
						gameBox.getButton().setIcon(null);
				}
				gameBox.changed();
				gameBox.notifyObservers();
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
