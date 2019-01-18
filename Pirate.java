
public class Pirate {
	private int row;
	private int col;
	private int direction;
	private boolean destroyed;

	
	//konstruktor koji postavlja pirata na poziciju (red, kolona), te mu postavlja smjer kretanja
	Pirate(int row, int col, int dir) {
		this.row = row;
		this.col = col;
		this.direction = dir; // TODO random generisati pravac
		this.destroyed = false;
	}
	
	//getter i setteri za red, kolonu, smjer i destroyed

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

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean getDestroyed() {
		return this.destroyed;
	}
	
	//smjer kretanja odredjuju brojevi 1, 2, 3, 4, 6, 7, 8
	//na sljedeci nacin

	static final int UP = 8;
	static final int RIGHT_UP = 9;
	static final int RIGHT = 6;
	static final int RIGHT_DOWN = 3;
	static final int DOWN = 2;
	static final int LEFT_DOWN = 1;
	static final int LEFT = 4;
	static final int LEFT_UP = 7;
	
	//metoda koja pomjera pirata na osnovu zadanog smjera

	void move() {
		if (this.direction == Pirate.LEFT_UP) {
			this.row--;
			this.col--;
		}
		if (this.direction == Pirate.UP) {
			this.row--;
		}
		if (this.direction == Pirate.RIGHT_UP) {
			this.row--;
			this.col++;
		}
		if (this.direction == Pirate.RIGHT) {
			this.col++;
		}
		if (this.direction == Pirate.LEFT) {
			this.col--;
		}
		if (this.direction == Pirate.LEFT_DOWN) {
			this.row++;
			this.col--;
		}
		if (this.direction == Pirate.DOWN) {
			this.row++;
		}
		if (this.direction == Pirate.RIGHT_DOWN) {
			this.row++;
			this.col++;
		}
	}
	
	//metoda za promjenu smjera

	void changeDirection(int dir) {
		this.direction = dir;
	}

	// Manhattan udaljenost izmedju dvije tacke na 2d grid-u
	// vraca sumu njihovih horiznostalnih i vertikalnih razlika
	
	private int returnDistance(int xP, int yP, int yD, int xD) {
		return Math.abs(xP - xD) + Math.abs(yP - yD);
	}

	
	//Nova pozicija pirata je minimalna od 8 mogucih koraka
	//Ako postoji vise od jednog minimuma, biramo nasumicno jedan
	void newPosition(int x1, int y1) {
		int[] niz = new int[8]; // up, right, down, left
		niz[0] = returnDistance(x1, y1, this.row - 1, this.col);
		niz[1] = returnDistance(x1, y1, this.row - 1, this.col + 1);
		niz[2] = returnDistance(x1, y1, this.row, this.col + 1);
		niz[3] = returnDistance(x1, y1, this.row + 1, this.col + 1);
		niz[4] = returnDistance(x1, y1, this.row + 1, this.col);
		niz[5] = returnDistance(x1, y1, this.row + 1, this.col - 1);
		niz[6] = returnDistance(x1, y1, this.row, this.col - 1);
		niz[7] = returnDistance(x1, y1, this.row - 1, this.col - 1);
		int min = niz[0];

		for (int i = 0; i < 8; i++) {
			if (niz[i] < min) {
				min = niz[i];
			}
		}
		int random = randomIndex(niz, min);
		if (random == 0) {
			this.direction = Pirate.UP;
		} else if (random == 1) {
			this.direction = Pirate.RIGHT_UP;
		} else if (random == 2) {
			this.direction = Pirate.RIGHT;
		} else if (random == 3) {
			this.direction = Pirate.RIGHT_DOWN;
		} else if (random == 4) {
			this.direction = Pirate.DOWN;
		} else if (random == 5) {
			this.direction = Pirate.LEFT_DOWN;
		} else if (random == 6) {
			this.direction = Pirate.LEFT;
		} else {
			this.direction = Pirate.LEFT_UP;
		}
	}
	
	//biramo random index od 1-8 koji je razlicit od minimuma;
	
	private int randomIndex(int[] niz, int min) {
		int ran = (int) (Math.random() * 8);
		while (niz[ran] != min) {
			ran = (int) (Math.random() * 8);
		}
		return (Math.abs(ran - getRandom())) % 8;
	}

	int getRandom() {
		if (Math.random() < 0.75) {
			return 0;
		}
		return 1;
	}
}