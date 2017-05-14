package model;

public class BombBox extends GameBox{

	@Override
	protected int getNumberOfBombNeighbors() {
		return -1;
	}

}
