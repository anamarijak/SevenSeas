import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {
	static JFrame frame;
	static JPanel sea;

	public static void main(String[] args) {
		
		Game game = new Game();
		game.init();
		JOptionPane.showMessageDialog(frame, "Start?");
		int rowNum = game.board.getDimRow();
		int colNum = game.board.getDimCol();

		frame = new JFrame("SEVEN SEAS");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		sea = new JPanel();
		sea.setLayout(new GridLayout(rowNum, colNum));
		sea.setBackground(Color.getColor(null, new Color(1, 141, 192)));

		JButton[][] field = new JButton[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				field[i][j] = new JButton();
				field[i][j].setSize(50, 50);
				field[i][j].setEnabled(false);

				sea.add(field[i][j]);
				field[i][j].setBorder(BorderFactory.createLineBorder(Color.getColor(null, new Color(1, 131, 178))));
			}
		}
		
		frame.add(sea);
		frame.setResizable(false);
		frame.setVisible(true);

		KeyListener kl = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 49 || e.getKeyCode() == 97 || e.getKeyCode() == 89) {
					game.ship.changeDirection(Ship.LEFT_DOWN);
					System.out.println("IDEMO GORE-LIJEVO");
				}

				if (e.getKeyCode() == 55 || e.getKeyCode() == 103 || e.getKeyCode() == 81) {
					game.ship.changeDirection(Ship.LEFT_UP);
					System.out.println("IDEMO GORE-LIJEVO");
				}

				if (e.getKeyCode() == 56 || e.getKeyCode() == 104 || e.getKeyCode() == 87) {
					game.ship.changeDirection(Ship.UP);
					System.out.println("IDEMO GORE");
				}
				if (e.getKeyCode() == 57 || e.getKeyCode() == 105 || e.getKeyCode() == 69) {
					game.ship.changeDirection(Ship.RIGHT_UP);
					System.out.println("IDEMO GORE-DESNO");
				}

				if (e.getKeyCode() == 54 || e.getKeyCode() == 102 || e.getKeyCode() == 68) {
					game.ship.changeDirection(Ship.RIGHT);
					System.out.println("IDEMO DESNO");
				}
				if (e.getKeyCode() == 51 || e.getKeyCode() == 99 || e.getKeyCode() == 67) {
					game.ship.changeDirection(Ship.RIGHT_DOWN);
					System.out.println("IDEMO DOLJE- DESNO");
				}

				if (e.getKeyCode() == 50 || e.getKeyCode() == 98 || e.getKeyCode() == 83) {
					game.ship.changeDirection(Ship.DOWN);
					System.out.println("IDEMO DOLJE");
				}
				if (e.getKeyCode() == 52 || e.getKeyCode() == 100 || e.getKeyCode() == 65) {
					game.ship.changeDirection(Ship.LEFT);
					System.out.println("IDEMO LIJEVO");
				}
				game.moveShip();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				game.movePirates();
				game.board.toConsole();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		};

		frame.addKeyListener(kl);

		while (game.getLevel() < 10) {
			if (game.end()) {
				game.restart();
				game.setGameOver(false);
				game.init();

				JOptionPane.showMessageDialog(frame, "Play again?");
				// frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

			};
			if (game.nextLevel()) {
				game.moveToNextLevel();
				game.init();
			}
			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < colNum; j++) {
					int v = game.board.board[i][j];
					Color c;
					String name = null;

					if (v == Board.SHIP) {
						c = Color.YELLOW;
						name = "S";
					} else if (v == Board.PIRATES) {
						c = Color.BLACK;
						name = "P";
					} else if (v == Board.PORTAL) {
						c = Color.getColor(null, new Color(249, 86, 86));
						name = "DOOR";
					} else if (v == Board.ISLAND) {
						c = Color.getColor(null, new Color(220, 182, 107));
						name = "I";
					} else if (v == Board.WRECK) {
						c = Color.getColor(null, new Color(224, 12, 107));
						name = "W"; 
					}
					else {
						c = Color.getColor(null, new Color(1, 149, 203));
						name = "~~";
					}

					field[i][j].setBackground(c);
					field[i][j].setText(name);
					field[i][j].setForeground(Color.WHITE);
				}
			}

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}