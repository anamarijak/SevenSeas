import java.util.Scanner;

/* Klasu Main koristimo za igranje u konzoli
 * 
 * @author Ana-Marija Knezevic
 * @version 1.0.0 (alfa)
 * @since 18/1/2019
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String in;
		game.init();
		System.out.println("Your board looks like this: \n");
		game.board.toConsole();
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
			if (in.equals("8"))
				game.ship.changeDirection(Ship.UP);
			else if (in.equals("9"))
				game.ship.changeDirection(Ship.RIGHT_UP);
			else if (in.equals("6"))
				game.ship.changeDirection(Ship.RIGHT);
			else if (in.equals("3"))
				game.ship.changeDirection(Ship.RIGHT_DOWN);
			else if (in.equals("2"))
				game.ship.changeDirection(Ship.DOWN);
			else if (in.equals("1"))
				game.ship.changeDirection(Ship.LEFT_DOWN);
			else if (in.equals("4"))
				game.ship.changeDirection(Ship.LEFT);
			else if (in.equals("7"))
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