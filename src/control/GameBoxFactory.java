package control;

import model.BombBox;
import model.GameBox;
import model.NumberBox;

public class GameBoxFactory {

	public GameBox createGameBox(GameBoxType type) {
		if (type == GameBoxType.BOMB_BOX)
			return new BombBox();
		if (type == GameBoxType.NUMBER_BOX)
			return new NumberBox();
		return null;
	}
}
