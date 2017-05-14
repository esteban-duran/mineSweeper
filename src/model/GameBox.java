package model;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import control.GameBoxClick;

public abstract class GameBox extends Observable implements Observer {

	public static final String URL_IMG_BOMB = "img/mine.png";
	public static final String URL_IMG_FLAG = "img/flag.png";
	public static final int IMG_SIZE = 20;
	public static final int BUTTON_SIZE = 50;
	private int id;
	private int positionX;
	private int positionY;
	private boolean empty;
	private boolean flagActive;
	private int number;
	private JButton button;

	private void setCordsById(int gameSize) {
		positionX = id / gameSize;
		positionY = id - (gameSize * positionX);
	}

	private void initButton() {
		if (this.button == null) {
			this.button = new JButton(" ");
			this.button.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		}

		button.addMouseListener(new GameBoxClick(this));
	}

	public void config(int id, int gameSize) {
		this.id = id;
		this.flagActive = false;
		initButton();
		setCordsById(gameSize);
		number = getNumberOfBombNeighbors();
		if (number > 0) {
			empty = false;
		} else {
			empty = true;
		}
	}

	protected abstract int getNumberOfBombNeighbors();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public boolean isFlagActive() {
		return flagActive;
	}

	public void setFlagActive(boolean flagActive) {
		this.flagActive = flagActive;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(this instanceof BombBox) && !this.isFlagActive()) {
			if (empty)
				button.setEnabled(false);
			else {
				button.setText("" + number);
				button.setEnabled(false);
			}
		}
	}

	public void changed() {
		setChanged();
	}
}
