package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BombBox;
import model.GameBox;

public class GameStatus extends Thread {

	private GameBoard originalBoard;

	private JLabel lblTimer;
	private JFrame frame;
	private JButton[][] board;
	private JPanel panelBtns;
	private int size;
	private boolean alive;

	private GridBagConstraints gbc;

	public GameStatus(int size) {
		board = new JButton[size][size];
		this.size = size;
		alive = true;
		init();
	}

	public void init() {
		lblTimer = new JLabel();
		lblTimer.setBackground(Color.BLACK);
		lblTimer.setForeground(Color.RED);
		lblTimer.setOpaque(true);
		lblTimer.setFont(lblTimer.getFont().deriveFont(35.0f));
		frame = new JFrame();
		frame.setTitle("Game status");
		frame.setVisible(true);
		initBoard();

		frame.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.add(lblTimer, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		initPanel();
		frame.add(panelBtns, gbc);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setSize(500, 500);
	}

	public void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new JButton();
				board[i][j].setPreferredSize(new Dimension(GameBox.BUTTON_SIZE, GameBox.BUTTON_SIZE));
			}
		}
	}
	
	public void endBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j].setEnabled(false);
			}
		}
	}

	public void initPanel() {
		panelBtns = new JPanel();
		panelBtns.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				gbc.gridx = x;
				gbc.gridy = y;
				panelBtns.add(board[x][y], gbc);
			}
		}
		panelBtns.setVisible(true);
	}

	public void restart(int size) {
		this.size = size;
		frame.dispose();
		alive = true;
		init();
	}

	@Override
	public void run() {
		while (alive) {
			lblTimer.setText(originalBoard.getInstance().getLblTimer().getText());
			for (int i = 0; i < originalBoard.getInstance().getBoard().length; i++) {
				for (int j = 0; j < originalBoard.getInstance().getBoard().length; j++) {
					GameBox gameBox = originalBoard.getInstance().getBoard()[i][j];
					JButton btnLocal = board[i][j];
					if (gameBox != null && btnLocal != null)
						btnLocal.setEnabled(gameBox.getButton().isEnabled());
					if(gameBox instanceof BombBox && !gameBox.getButton().isEnabled())
						stopThread();
				}
			}
		}
	}

	public void stopThread() {
		try {
			alive = false;
			JLabel lblLost = new JLabel(lblTimer.getText());
			lblLost.setBackground(Color.BLACK);
			lblLost.setForeground(Color.RED);
			lblLost.setOpaque(true);
			lblLost.setFont(lblLost.getFont().deriveFont(35.0f));
			gbc.gridx = 0;
			gbc.gridy = 0;
			lblTimer.setVisible(false);
			frame.add(lblLost, gbc);
			endBoard();
			Thread.sleep(1000);
//			frame.setVisible(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
