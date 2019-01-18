
public class Board {
	static final int EMPTY = 0;
	static final int ISLAND = 1;
	static final int WRECK = 2;
	static final int SHIP = 3;
	static final int PIRATES = 4;
	static final int PORTAL = 5;

	static final int dimRow = 20;
	static final int dimCol = 20;

	int[][] board;

	// konstruktor
	Board() {
		this.board = new int[dimRow][dimCol];
		// portal koji nas vodi u naredni nivo je pozicioniran u gornji desni ugao
		this.board[0][dimCol - 1] = Board.PORTAL;
	}

	// getteri i setteri
	public int getDimRow() {
		return dimRow;
	}

	public int getDimCol() {
		return dimCol;
	}
	//postatvlja brod
	boolean setShip(int row, int col) {
		if (this.board[row][col] == Board.WRECK || this.board[row][col] == Board.ISLAND
				|| this.board[row][col] == Board.PIRATES) {
			this.board[row][col] = Board.EMPTY;
			return true;
		}
		this.board[row][col] = Board.SHIP;
		return false;
	}
	//postavlja pirate
	boolean setPirate(int row, int col) {
		if (this.board[row][col] == Board.ISLAND) {
			this.board[row][col] = Board.EMPTY;
			return true;
		} else if (this.board[row][col] == Board.PIRATES || this.board[row][col] == Board.WRECK) {
			this.board[row][col] = Board.WRECK;
			return true;
		} else if (this.board[row][col] == Board.SHIP) {
			this.board[row][col] = Board.WRECK;
			return true;
		}
		this.board[row][col] = Board.PIRATES;
		return false;
	}

	void setWreck(int row, int col) {
		this.board[row][col] = Board.WRECK;
	}

	void setIsland(int row, int col) {
		this.board[row][col] = Board.ISLAND;
	}

	void removeShip(int row, int col) {
		this.board[row][col] = Board.EMPTY;
	}

	void removePirate(int row, int col) {
		this.board[row][col] = Board.EMPTY;
	}

	// da li je pozicija moguca
	
	boolean checkInitPirates(int row, int col) {
		if (this.board[row][col] == 0)
			return true;
		return false;
	}
// ispis u konzolu
	void toConsole() {
		for (int i = 0; i < Board.dimRow; i++) {
			for (int j = 0; j < Board.dimCol; j++) {
				System.out.print(this.board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
