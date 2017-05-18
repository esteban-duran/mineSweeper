package control;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import view.ISmileyMouse;

public class SmileyMouseAdapter extends MouseAdapter implements ISmileyMouse{
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		button.setBackground(Color.RED);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		button.setBackground(null);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(button.isEnabled())
			button.setText("Smiling");
		else
			button.setText("Sad");
	}
}
