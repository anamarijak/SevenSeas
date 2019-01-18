import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String in;
		game.init();
		System.out.println("Level: " + game.getLevel());
		while (game.getLevel() < 6) {
			if (game.end()) {
				System.out.println("Game over!");
				while (true) {
					System.out.println("To continue/exit enter (Y/N): ");
					in = input.nextLine();
					if (in.equals("Y")) {
						game.init();
						break;
					} else if (in.equals("N")) {
						System.exit(1);
					} else {
						System.out.println("You entered invalid key! Try again!");
					}
				}
			} else if (game.nextLevel()) {
				game.moveToNextLevel();
				game.init();
			}
			System.out.println("Enter your next move: ");
			in = input.nextLine();
			if (in.equals("W"))
				game.ship.changeDirection(Ship.UP);
			else if (in.equals("E"))
				game.ship.changeDirection(Ship.RIGHT_UP);
			else if (in.equals("D"))
				game.ship.changeDirection(Ship.RIGHT);
			else if (in.equals("C"))
				game.ship.changeDirection(Ship.RIGHT_DOWN);
			else if (in.equals("S"))
				game.ship.changeDirection(Ship.DOWN);
			else if (in.equals("Y"))
				game.ship.changeDirection(Ship.LEFT_DOWN);
			else if (in.equals("A"))
				game.ship.changeDirection(Ship.LEFT);
			else if (in.equals("Q"))
				game.ship.changeDirection(Ship.LEFT_UP);
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
	}
}