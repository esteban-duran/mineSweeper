package view;

import java.util.Observable;

import javax.swing.JButton;

public abstract class SmileyDecorator implements SmileyStyle{

	protected SmileyStyle reference;
	
	public SmileyDecorator(SmileyStyle reference) {
		this.reference = reference;
	}

	@Override
	public JButton getSmyleyButton() {
		return reference.getSmyleyButton();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		reference.update(o, arg);
	}
}
