package view;

import control.SmileyMouseAdapter;

public class SmileyMouseDecorator extends SmileyDecorator {

	public SmileyMouseDecorator(SmileyStyle reference) {
		super(reference);
	}

	@Override
	public void setSmileyStyle() {
		reference.setSmileyStyle();
		getSmyleyButton().addMouseListener(new SmileyMouseAdapter());
	}
}
