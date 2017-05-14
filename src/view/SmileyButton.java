package view;

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
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
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
