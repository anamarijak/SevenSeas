import java.util.Random;
/* Klasa Game predstavlja osnovnu logiku igre. 
 * @author Ana-Marija Knezevic
 * @version 1.0.0 (alfa)
 * @since 18/1/2019
 * */

public class Game {
	private int numOfPirates = 1;
	private int numOfIslands = 2;
	private int numOfLevels = 6;
	private int level = 1;
	private boolean gameOver = false;
	Ship ship;
	Board board;
	Pirate[] pirates;

	// getteri i setteri
	int getLevel() {
		return this.level;
	}

	void setLevel(int lvl) {
		this.level = lvl;
	}
	int getNumOfLevels() {
		return this.numOfLevels;
	}
	// restart metoda
	void restart() {
		this.level = 1;
		this.numOfIslands = 2;
		this.numOfPirates = 1;

	}
	//povecaj level metoda
	void moveToNextLevel() {
		this.level++;
		this.numOfIslands += 2;
		this.numOfPirates += 2;
	}
	//inicijalizacija igre
	public void init() {
		board = new Board();
		//brod na fiksnoj poziciji
		ship = new Ship(19, 0);
		board.setShip(ship.getRow(), ship.getCol());
		
		//postavljamo random pirate na matricu
		int randomRow;
		int randomCol;
		Random randomPiratePosition = new Random();
		int i = 0;
		pirates = new Pirate[this.numOfPirates];
		while (i < this.numOfPirates) {
			randomRow = randomPiratePosition.nextInt(12);
			randomCol = randomPiratePosition.nextInt(16) + 4;
			if (board.checkPiratesPosition(randomRow, randomCol)) {
				pirates[i] = new Pirate(randomRow, randomCol, Pirate.UP); 
				board.setPirate(randomRow, randomCol);
				i++;
			}
		}
		i = 0;
		
		//slicno, postavljamo otoke na plocu
		while (i < this.numOfIslands) {
			randomRow = randomPiratePosition.nextInt(12);
			randomCol = randomPiratePosition.nextInt(15) + 4;
			if (board.checkPiratesPosition(randomRow, randomCol)) {
				board.setIsland(randomRow, randomCol);
				i++;
			}
		}
		
		//board.toConsole();

	}
	
	//pomjeranje broda
	public void moveShip() {
		int direction = ship.getDirection();

		if (checkMove(ship.getRow(), ship.getCol(), direction) && checkIsland(ship.getRow(), ship.getCol(), direction)) {
			board.removeShip(ship.getRow(), ship.getCol());
			ship.move();
			if (board.setShip(ship.getRow(), ship.getCol()) == true) {
				this.gameOver = true;
			}
			
			System.out.println("Game over: " + this.gameOver);
		}
	}
	
	
	//pomjeranje pirata
	public void movePirates() {
		AI();
		for (int i = 0; i < this.numOfPirates; i++) {
			board.removePirate(pirates[i].getRow(), pirates[i].getCol());
			if (pirates[i].getDestroyed() == false && checkMove(pirates[i].getRow(), pirates[i].getCol(), pirates[i].getDirection()) == true) {
				pirates[i].move();
				if (board.setPirate(pirates[i].getRow(), pirates[i].getCol())) {
					pirates[i].setDestroyed(true);
					if (board.board[this.ship.getRow()][this.ship.getCol()] != Board.SHIP)
						this.gameOver = true;
				}
			}
		}
	}

	private void AI() {
		for (int i = 0; i < this.numOfPirates; i++) {
			pirates[i].newPosition(this.ship.getCol(), this.ship.getRow());
		}
	}

	//provjerava da li je potez validan
	boolean checkMove(int row, int col, int dir) {
		int dimRow = board.getDimRow();
		int dimCol = board.getDimCol();

		if (row == 0 && dir == Ship.UP) {
			return false;
		}
		if (row == 0 && dir == Ship.LEFT_UP) {
			return false;
		}
		if (row == 0 && dir == Ship.RIGHT_UP) {
			return false;
		}
		if (col == 0 && dir == Ship.LEFT) {
			return false;
		}
		if (col == 0 && dir == Ship.LEFT_UP) {
			return false;
		}
		if (col == 0 && dir == Ship.LEFT_DOWN) {
			return false;
		}
		if (row == dimRow - 1 && dir == Ship.DOWN) {
			return false;
		}
		if (row == dimRow - 1 && dir == Ship.LEFT_DOWN) {
			return false;
		}
		if (row == dimRow - 1 && dir == Ship.RIGHT_DOWN) {
			return false;
		}

		if (col == dimCol - 1 && dir == Ship.RIGHT) {
			return false;
		}
		if (col == dimCol - 1 && dir == Ship.RIGHT_UP) {
			return false;
		}
		if (col == dimCol - 1 && dir == Ship.RIGHT_DOWN) {
			return false;
		}
		return true;

	}
	
	boolean checkIsland(int row, int col, int dir) {
		if (dir == Ship.RIGHT_UP) {
			row = row - 1;
			col = col + 1;
		}
		if (dir == Ship.UP) {
			row = row - 1;
		}
		if (dir == Ship.LEFT_UP) {
			row = row - 1;
			col = col - 1;
		}

		if (dir == Ship.LEFT) {
			col = col - 1;
		}

		if (dir == Ship.LEFT_DOWN) {
			row = row + 1;
			col = col - 1;
		}
		if (dir == Ship.DOWN) {
			row = row + 1;
		}
		if (dir == Ship.RIGHT_DOWN) {
			row = row + 1;
			col = col + 1;
		}

		if (dir == Ship.RIGHT) {
			col = col + 1;
		}
		if (board.board[row][col] == Board.ISLAND) {
			return false;
		}
		return true;
	}

	//provjerava da li je igra gotova
	boolean end() {
		if (this.gameOver == true)
			return true;
		return false;
	}
	
	//provjerava ad li je brod uspio doci do luke
	boolean nextLevel() {
		return this.ship.getRow() == 0 && this.ship.getCol() == this.board.getDimCol() - 1;
	}
	
	//postavlja game over
	void setGameOver(boolean gameOver) { 
		System.out.println("GOVER");
		this.gameOver = gameOver; 
		System.out.println(this.gameOver);
	}

}
