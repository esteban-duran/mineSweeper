package view;

import java.util.Observer;

import javax.swing.JButton;

public interface SmileyStyle extends Observer{

	public void setSmileyStyle();
	public JButton getSmyleyButton();
}
