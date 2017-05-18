package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.JButton;

import control.GameBoxClick;

public class SmileyButton implements SmileyStyle{

	public static final String URL_SMILEY_IMG = "img/smiley.png";
	public static final String URL_SAD_IMG = "img/sad.png";
	public static final String URL_SMILEY_BW_IMG = "img/smiley-bw.png";
	public static final String URL_SAD_BW_IMG = "img/sad-bw.png";
	private JButton smileyButton;
	
	public SmileyButton() {
		smileyButton = new JButton();
		smileyButton.setPreferredSize(new Dimension(100,45));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		smileyButton.setBackground(Color.BLACK);
		smileyButton.setIcon(GameBoxClick.scaleImageIcon(SmileyButton.URL_SAD_IMG));
		smileyButton.setText(null);
		smileyButton.setEnabled(false);
	}

	@Override
	public void setSmileyStyle() {
		smileyButton.setIcon(GameBoxClick.scaleImageIcon(URL_SMILEY_IMG));
	}

	@Override
	public JButton getSmyleyButton() {
		return smileyButton;
	}

}
