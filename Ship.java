
public class Ship {
	private int row;
	private int col;
	private int direction;

	//konstruktor, postavlja brod na poziciju (red, kolona) i postavlja mu sjer na 0
	Ship(int row, int col) {
		this.row = row;
		this.col = col;
		this.direction = 0;
	}
	
	//getteri i setteri

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	//smjer kretanja odredjuju brojevi 1, 2, 3, 4, 6, 7, 8
	//na sljedeci nacin
	
	static final int LEFT_UP = 7;
	static final int DOWN = 2;
	static final int RIGHT_UP = 9;
	static final int LEFT = 4;
	static final int RIGHT = 6;
	static final int LEFT_DOWN = 1;
	static final int UP = 8;
	static final int RIGHT_DOWN = 3;
	
	//metoda koja pomjera brod na osnovu zadanog smjera

	void move() {
		if (this.direction == Ship.LEFT_UP) {
			this.row--;
			this.col--;
		}
		if (this.direction == Ship.UP) {
			this.row--;
		}
		if (this.direction == Ship.RIGHT_UP) {
			this.row--;
			this.col++;
		}
		if (this.direction == Ship.RIGHT) {
			this.col++;
		}
		if (this.direction == Ship.LEFT) {
			this.col--;
		}
		if (this.direction == Ship.LEFT_DOWN) {
			this.row++;
			this.col--;
		}
		if (this.direction == Ship.DOWN) {
			this.row++;
		}
		if (this.direction == Ship.RIGHT_DOWN) {
			this.row++;
			this.col++;
		}
	}
	
	//metoda za promjenu smjera
	
	void changeDirection(int dir) {
		this.direction = dir;
	}

}