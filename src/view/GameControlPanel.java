package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class GameControlPanel extends JPanel{

	public GameControlPanel(SmileyStyle smiley) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
	    add(smiley.getSmyleyButton(), gbc);
	    smiley.setSmileyStyle();
	    setVisible(true);
	}
}
