package model;

import control.GameBoard;

public class NumberBox extends GameBox {

	@Override
	protected int getNumberOfBombNeighbors() {
		int topLeftCords[] = { getPositionX() - 1, getPositionY() - 1 };
		int topCords[] = { getPositionX() - 1, getPositionY() };
		int topRightCords[] = { getPositionX() - 1, getPositionY() + 1 };
		int leftCords[] = { getPositionX(), getPositionY() - 1 };
		int rightCords[] = { getPositionX(), getPositionY() + 1 };
		int bottomLeftCords[] = { getPositionX() + 1, getPositionY() - 1 };
		int bottomCords[] = { getPositionX() + 1, getPositionY() };
		int bottomRightCords[] = { getPositionX() + 1, getPositionY() + 1 };
		
		GameBoard board = GameBoard.getInstance();
		GameBox topLeft = board.getGameBoxByCords(topLeftCords[0], topLeftCords[1]);
		GameBox top = board.getGameBoxByCords(topCords[0], topCords[1]);
		GameBox topRight = board.getGameBoxByCords(topRightCords[0], topRightCords[1]);
		GameBox left = board.getGameBoxByCords(leftCords[0], leftCords[1]);
		GameBox right = board.getGameBoxByCords(rightCords[0], rightCords[1]);
		GameBox bottomLeft = board.getGameBoxByCords(bottomLeftCords[0], bottomLeftCords[1]);
		GameBox bottom = board.getGameBoxByCords(bottomCords[0], bottomCords[1]);
		GameBox bottomRight = board.getGameBoxByCords(bottomRightCords[0], bottomRightCords[1]);
		
		int numberOfBombNeighbors = 0;
		if(topLeft != null && topLeft instanceof BombBox)
			numberOfBombNeighbors++;
		if(top != null && top instanceof BombBox)
			numberOfBombNeighbors++;
		if(topRight != null && topRight instanceof BombBox)
			numberOfBombNeighbors++;
		if(left != null && left instanceof BombBox)
			numberOfBombNeighbors++;
		if(right != null && right instanceof BombBox)
			numberOfBombNeighbors++;
		if(bottomLeft != null && bottomLeft instanceof BombBox)
			numberOfBombNeighbors++;
		if(bottom != null && bottom instanceof BombBox)
			numberOfBombNeighbors++;
		if(bottomRight != null && bottomRight instanceof BombBox)
			numberOfBombNeighbors++;
		
		return numberOfBombNeighbors;
	}

}
