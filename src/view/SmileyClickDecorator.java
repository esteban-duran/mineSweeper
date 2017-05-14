package view;

import control.SmileyMouseAdapter;

public class SmileyClickDecorator extends SmileyDecorator{

	
	public SmileyClickDecorator(SmileyStyle reference) {
		super(reference);
	}

	@Override
	public void setSmileyStyle() {
		reference.setSmileyStyle();
		getSmyleyButton().addMouseListener(new SmileyMouseAdapter());
	}
}
