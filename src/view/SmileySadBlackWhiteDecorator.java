package view;

import java.awt.Color;
import java.util.Observable;

import javax.swing.JButton;

import control.GameBoxClick;

public class SmileySadBlackWhiteDecorator extends SmileyDecorator {

	public SmileySadBlackWhiteDecorator(SmileyStyle reference) {
		super(reference);
	}

	@Override
	public void setSmileyStyle() {
		reference.setSmileyStyle();
		getSmyleyButton().setBackground(Color.BLACK);
		getSmyleyButton().setIcon(GameBoxClick.scaleImageIcon(SmileyButton.URL_SAD_IMG));
	}
}
