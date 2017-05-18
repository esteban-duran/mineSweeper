package view;

import control.SmileyMouseAdapter;

public class SmileyMouseDecorator extends SmileyDecorator {

	private SmileyMouseAdapter smileyMouseAdapter;
	
	public SmileyMouseDecorator(SmileyStyle reference) {
		super(reference);
		smileyMouseAdapter = new SmileyMouseAdapter();
	}

	@Override
	public void setSmileyStyle() {
		reference.setSmileyStyle();
		getSmyleyButton().addMouseListener(smileyMouseAdapter);
	}
}
