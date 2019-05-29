import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {

	static OceanMap board = new OceanMap();

	static{
		System.out.println("**** Welcome to Battle Ships Game ****");
		board.initialise();
		board.printMap();
	}

	public static void main(String[] args) {
		System.out.println("****You can only deploy 5 ships, make your choices wisely****");
		deployPlayerShips();
		board.printMap();
		deployComputerShips();
		System.out.println("**** Begin the battle ****");
		playersTurn();
		computersTurn();
	}
	private static void computersTurn() {
		// TODO Auto-generated method stub
		System.out.println("**** Computer's Turn ****");
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int x,y;
		do{
		x = rand.nextInt(10);
		y = rand.nextInt(10);
		}while(board.attackMove(x, y) == '*');
		

		char event = board.attackMove(x, y);

		switch (event){
		case '2':
			System.out.println("The computer sunk one of its own ships");
			board.mark(x, y, '!');
			break;

		case '@':
			System.out.println("The Computer sunk one of your ships");
			board.mark(x, y, 'x');
			break;
		case ' ':
			System.out.println("Sorry, you missed");
			board.mark(x, y, '*');
			break;
		}
		board.printMap();
	}
	private static void playersTurn() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);

		System.out.println("YOUR TURN");
		int x = 0, y = 0;
		do{
			System.out.print("Enter X coordinate: ");
			x = input.nextInt();
			System.out.print("Enter Y coordinate: ");
			y = input.nextInt();
		}while (board.attackMove(x,y) == '-');

		char event = board.attackMove(x, y);

		switch (event){
		case '2':
			System.out.println("Boom! You sunk the ship!");
			board.mark(x, y, '!');
			break;

		case '@':
			System.out.println("Oh no, you sunk your own ship :(");
			board.mark(x, y, 'x');
			break;
		case ' ':
			System.out.println("Sorry, you missed");
			board.mark(x, y, '-');
			break;
		}
		board.printMap();

	}
	private static void deployComputerShips() {
		// TODO Auto-generated method stub
		System.out.println("Computer is deploying ships");
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i = 1; i < 6; i++) {
			boolean valid = false;
			while(!valid){	
			int x = rand.nextInt(10);
			int y = rand.nextInt(10);
				
				valid = board.setPos(x, y, '2');
			}
			System.out.format("\n%d. ship DEPLOYED", i);
		}
	}
	private static void deployPlayerShips(){

		Scanner input = new Scanner(System.in); //This line creates a Scanner for you to use
		for (int i = 1; i < 6; i++){
			boolean valid = false;
			while(!valid){
				System.out.format("Enter X coordinate for your ship %d: ", i);
				int x = input.nextInt();
				System.out.format("Enter Y coordinate for your ship %d: ", i);
				int y = input.nextInt();
				valid = board.setPos(x, y, '@');
				if (!valid)
					System.out.println("Please enter valid coordinates");
			}
			
		}
		

	}

}
