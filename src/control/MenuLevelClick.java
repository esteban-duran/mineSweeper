package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLevelClick implements ActionListener{

	private int level;
	
	public MenuLevelClick(int level){
		this.level = level;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameBoard board = GameBoard.getInstance();
		board.setGameSize(level);
		board.startGame();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
