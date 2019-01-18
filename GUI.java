import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/* GUI klasa se koristi za prikaz grafickog interfejsa za igraca.
 * Generiramo ga pomocu prozorcica na kojem se nalazi panel s nizom
 * dugmica koji predstavljaju polja igre. Na dugmice je zakacen key 
 * listener. Za igranje je moguce koristiti brojeve od 1-9 kao i slova
 * na tipkovnici koja se obicno koriste za drugog igraca u igrama (w-a-s-d)
 *
 * @author Ana-Marija Knezevic
 * @version 1.0.0 (alfa)
 * @since 18/1/2019
 * 
 */
public class GUI {
	static JFrame frame;
	static JPanel sea;

	public static void main(String[] args) {
		// inicijaliziramo novu igru na pocetku
		Game game = new Game();
		game.init();
		// na pocetku izbacimo poruku za pocetak igre
		JOptionPane.showMessageDialog(frame, "Start?");

		// dobavljamo broj redova i kolona
		int rowNum = game.board.getDimRow();
		int colNum = game.board.getDimCol();

		// postavljanje okvira i panela na njega
		frame = new JFrame("PIRATE HUNT");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		sea = new JPanel();
		sea.setLayout(new GridLayout(rowNum, colNum));
		sea.setBackground(Color.getColor(null, new Color(1, 141, 192)));

		// postavljanje dugmica koji predstavljaju nasa polja
		JButton[][] field = new JButton[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				field[i][j] = new JButton();
				field[i][j].setSize(60, 60);
				field[i][j].setEnabled(false);

				sea.add(field[i][j]);
				field[i][j].setBorder(BorderFactory.createLineBorder(Color.getColor(null, new Color(1, 131, 178))));
			}
		}

		// dodajemo panel na frame
		frame.add(sea);
		frame.setResizable(false);
		frame.setVisible(true);

		/*
		 * key listener za nasu igru //igra je omogucena na brojevima i slovima
		 * 
		 * 1 ili z - left-down 
		 * 2 ili s - down 
		 * 3 ili c - right-down 
		 * 4 ili a - left
		 * 5 ili d - right
		 * 7 ili q - left-up 
		 * 8 ili w - up 
		 * 9 ili e - right-up
		 * 
		 */
		KeyListener kl = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

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
				// pozivamo metodu za kretanje brodica
				game.moveShip();
				// "pauziramo thread na kratko, kako bi dobili osjecaj da igrac igra poslije nas
				// inace bi se kretanje vrsila istovremeno (nasem oku vidljivo)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// pozivamo metodu za kretanje piratea
				game.movePirates();
				// game.board.toConsole();
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
		// dodajemo key listener na okvir
		frame.addKeyListener(kl);

		// while petlja koja traje sve dok ima level-a

		while (game.getLevel() < game.getNumOfLevels()) {

			// ako je igra gotova restartujemo je pomocu metode restart
			// te je ponovno inicijaliziramo
			if (game.end()) {
				game.setGameOver(false);
				game.restart();
				game.init();
				JOptionPane.showMessageDialog(frame, "Play again?");
			}
			// provjeravmao da li je brodic dosao do prolaza za novi level
			// ako jest onda postavljamo novi level i ponovno pokrecemo igru

			if (game.nextLevel()) {
				game.moveToNextLevel();
				game.init();
			}

			// postavljamo komponente igre da budu vidljivi tako sto im mjenjamo boju ovisno
			// od broja koji zauzimaju, takodjer postavljamo slovo na ta ista polja

			// tako ce brod biti bijele boje s slovom S
			// pirati crne boje s slovom P
			// otoci bež boje s slovom I
			// portal koraljne boje bez slova
			// a more plave boje s ilustracijom valova korištenjem znaka ~
			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < colNum; j++) {
					int v = game.board.board[i][j];
					Color c;
					String name = null;

					if (v == Board.SHIP) {
						c = Color.WHITE;
						name = "S";
					} else if (v == Board.PIRATES) {
						c = Color.BLACK;
						name = "P";
					} else if (v == Board.PORTAL) {
						c = Color.getColor(null, new Color(249, 86, 86));
						name = "";
					} else if (v == Board.ISLAND) {
						c = Color.getColor(null, new Color(220, 182, 107));
						name = "I";
					} else if (v == Board.WRECK) {
						c = Color.getColor(null, new Color(220, 182, 107));
						name = "W";
					} else {
						c = Color.getColor(null, new Color(1, 149, 203));
						name = "~~";
					}

					field[i][j].setBackground(c);
					field[i][j].setText(name);
					field[i][j].setForeground(Color.WHITE);
				}
			}
		}
	}
}