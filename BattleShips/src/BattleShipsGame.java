import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {

	static OceanMap board = new OceanMap();
	static int playerShips = 5, computerShips = 5;

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
		while (true){
			playersTurn();
			computersTurn();
			board.printMap();
			System.out.format("**** Your ships: %d | Computer ships: %d ****\n", playerShips, computerShips);
			if (playerShips == 0 || computerShips == 0)
				break;
		}
		if (playerShips < 1){
			System.out.println("**** Ooops You LOST! ****");
		}else
			System.out.println("**** Hooray! You win the battle :) ****");
	}
	private static void computersTurn() {
		// TODO Auto-generated method stub
		System.out.println("**** Computer's Turn ****");
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int x= 0, y= 0;
		do{
			x = rand.nextInt(10);
			y = rand.nextInt(10);
		}while(board.attackMove(x, y) == '*');


		char event = board.attackMove(x, y);

		switch (event){
		case '2':
			System.out.println("The computer sunk one of its own ships");
			board.mark(x, y, '!');
			computerShips--;
			break;

		case '@':
			System.out.println("The Computer sunk one of your ships");
			board.mark(x, y, 'x');
			playerShips--;
			break;
		case ' ':
			System.out.println("The Computer");
			board.mark(x, y, '*');
			break;
		}
		board.printMap();
	}
	private static void playersTurn() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);

		System.out.println("YOUR TURN");
		int x = -1, y = -1;

		do{
			while(!isValid(x, y)){
				try{
				System.out.print("Enter X coordinate: ");
				x = input.nextInt();
				System.out.print("Enter Y coordinate: ");
				y = input.nextInt();
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("Please enter numeric numbers only");
					
					input = new Scanner(System.in);
				}
			}


		}while (board.attackMove(x,y) == '-');

		char event = board.attackMove(x, y);

		switch (event){
		case '2':
			System.out.println("Boom! You sunk the ship!");
			board.mark(x, y, '!');
			computerShips--;
			break;

		case '@':
			System.out.println("Oh no, you sunk your own ship :(");
			board.mark(x, y, 'x');
			playerShips--;
			break;
		case ' ':
			System.out.println("Sorry, you missed");
			board.mark(x, y, '-');
			break;
		}
		board.printMap();

	}
	private static boolean isValid(int x, int y) {
		if ((x >= 0 & x < 10) && (y >= 0 & y < 10)){
			return true;
		}
		return false;
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
			int x = -1, y = -1;
			while(!valid){
				try{
				System.out.format("Enter X coordinate for your ship %d: ", i);
				x = input.nextInt();
				System.out.format("Enter Y coordinate for your ship %d: ", i);
				y = input.nextInt();
				}catch (Exception e) {
					System.out.println("Please enter numeric data only");
					x = y = -1;
					input = new Scanner(System.in);
					}
				valid = board.setPos(x, y, '@');
				if (!valid)
					System.out.println("Please enter valid coordinates");
			}

		}


	}

}
