package view;

import java.awt.Color;

import control.GameBoxClick;

public class SmileyBlackWhiteDecorator extends SmileyDecorator{

	public SmileyBlackWhiteDecorator(SmileyStyle reference) {
		super(reference);
	}

	@Override
	public void setSmileyStyle() {
		reference.setSmileyStyle();
		getSmyleyButton().setBackground(Color.BLACK);
		getSmyleyButton().setIcon(GameBoxClick.scaleImageIcon(SmileyButton.URL_SMILEY_BW_IMG));
	}
}