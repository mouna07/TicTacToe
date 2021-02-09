import java.util.Scanner;

public class TicTacToeGame {

	static  char[] board = new char[10];
	private static char cross ='X';
	private static char zero ='O';
	private static char playerSymbol;
	private static char computerSymbol;
	

	
	
	 //creating board char array and intialising it//
	 public static char[]  creatingBoard() {


		for (int i = 1; i < board.length; i++) {
			board[i] = ' ';
		}
		return board;

	}
	 
	 //allowing player to choose symbol method 
	 private static void allowPlayerToChoose()
	 {
		 System.out.println("Enter the symbol X or O you want to choose");
		 Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		playerSymbol = input.charAt(0);
		
		if(playerSymbol == cross)
		{
			playerSymbol = cross;
			computerSymbol = zero;
			System.out.println("player symbol is : " + playerSymbol);

		}
		else if(playerSymbol == zero)
		{
			playerSymbol = zero;
			computerSymbol = cross;
			System.out.println("player symbol is : " + playerSymbol);

		}
		else
		{
			System.out.println("Invalid input");
		}
		
		
		 
	 }
	 

	public static void main(String args[]) {

		System.out.println("Welcome to tic tac board");
		board = creatingBoard();
		
		allowPlayerToChoose();
	}
}
