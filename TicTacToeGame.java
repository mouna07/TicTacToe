import java.util.Scanner;

public class TicTacToeGame {

	static char[] board = new char[10];
	private static char cross = 'X';
	private static char zero = 'O';
	private static char playerSymbol;
	private static char computerSymbol;
	private static int toss = 0;
	private static int turn = 0;

	// creating board char array and intialising it//
	public static char[] creatingBoard() {
		for (int i = 1; i < board.length; i++) {
			board[i] = ' ';
		}
		return board;
	}

	// allowing player to choose symbol method
	private static void allowPlayerToChoose() {
		System.out.println("Enter the symbol X or O you want to choose");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		playerSymbol = input.charAt(0);
		if (playerSymbol == cross) {
			playerSymbol = cross;
			computerSymbol = zero;
			System.out.println("player symbol is : " + playerSymbol);

		} else if (playerSymbol == zero) {
			playerSymbol = zero;
			computerSymbol = cross;
			System.out.println("player symbol is : " + playerSymbol);

		} else {
			System.out.println("Invalid input");
		}
	}

	// displaying board and values at specific positions
	public static void showBoard() {
		System.out.println(" board looks like :");
		System.out.println("");
		System.out.println("   " + board[1] + "   " + "|" + "   " + board[2] + "   " + "|" + "   " + board[3] + "   ");
		System.out.println("  -------------------");
		System.out.println("   " + board[4] + "   " + "|" + "   " + board[5] + "   " + "|" + "   " + board[6] + "   ");
		System.out.println("  -------------------");
		System.out.println("   " + board[7] + "   " + "|" + "   " + board[8] + "   " + "|" + "   " + board[9] + "   ");
	}

	// taking position from player where he wants to put his input
	// UC5 is also satisfied in this
	public static void userMove() {
		System.out.println("Enter the empty position(between 1-9) where you wants to make the move ");
		Scanner sc = new Scanner(System.in);
		int position = sc.nextInt();
		if (position >= 1 && position <= 9) {
			if (board[position] == ' ') {
				System.out.println("position  : " + position + " is empty");
				board[position] = playerSymbol;
				showBoard();
			} else {
				System.out.println("Invalid move, position is not empty");
			}
		} else {
			System.out.println("You entered a invalid position");
		}
		turn = 1;
		System.out.println("Computer's turn");
	}

	// modified method so that computer will play its turn automatically and will block if player is winning
	public static void computerMove() {
		int position;
		int posiToBlock = blockPosition();
		if(posiToBlock == 0)
		{
	      position = (int) Math.floor(Math.random() * 10) % 10;
		while (board[position] != ' ') {
			position = (int) Math.floor(Math.random() * 10) % 10;
		}
		}
		else
		{
		position = posiToBlock;	
		}
		System.out.println("position  : " + position + " is empty");
		board[position] = computerSymbol;
		showBoard();
		turn = 0;
		System.out.println("Player's turn");
	}

	// do toss to check who plays first move
	public static int doToss() {
		int tossResult = (int) Math.floor(Math.random() * 10) % 2;
		if (tossResult == 0) {
			System.out.println("User/Player plays first");
		} else {
			System.out.println("Computer plays first");
		}
		return tossResult;
	}

	// runs until we get a winner
	public static void turnUntilWeGetWinner() {
		char symbol = ' ';
		// for first turn only
		if (toss == 0) {
			userMove();
			turn = 1;
		} else {
			computerMove();
			turn = 0;
		}
		// for turns after first one
		boolean winnerFound = false;
		while (winnerFound != true) {
			if (turn == 0) {
				userMove();
				symbol = playerSymbol;
			} else {
				computerMove();
				symbol = computerSymbol;
			}

			winnerFound = checkWinningCondition(symbol);
			if (board[1] != ' ' && board[2] != ' ' && board[3] != ' ' && board[4] != ' ' && board[5] != ' '
					&& board[6] != ' ' && board[7] != ' ' && board[8] != ' ' && board[9] != ' '
					&& winnerFound != true) {
				System.out.println("It is a tie , no one won");
				break;
			}
		}
		if (symbol == playerSymbol) {
			System.out.println("Player won");
		} else {
			System.out.println("Computer won");
		}
	}

	// public winning condition check
	public static boolean checkWinningCondition(char symbol) {
		boolean gotWinner = false;
		if (board[1] == symbol && board[2] == symbol && board[3] == symbol) {
			gotWinner = true;
		}
		if (board[4] == symbol && board[5] == symbol && board[6] == symbol) {
			gotWinner = true;
		}
		if (board[7] == symbol && board[8] == symbol && board[9] == symbol) {
			gotWinner = true;
		}
		if (board[1] == symbol && board[4] == symbol && board[7] == symbol) {
			gotWinner = true;
		}
		if (board[2] == symbol && board[5] == symbol && board[8] == symbol) {
			gotWinner = true;
		}
		if (board[3] == symbol && board[6] == symbol && board[9] == symbol) {
			gotWinner = true;
		}
		if (board[1] == symbol && board[5] == symbol && board[9] == symbol) {
			gotWinner = true;
		}
		if (board[3] == symbol && board[5] == symbol && board[7] == symbol) {
			gotWinner = true;
		}

		return gotWinner;
	}
	
	public static int blockPosition()
	{
		int positionToBlock = 0 ;
		// block position in row 1
		if (board[1] == playerSymbol && board[2] == playerSymbol && board[3] == ' ') 
		positionToBlock = 3;
		if (board[2] == playerSymbol && board[3] == playerSymbol && board[1] == ' ' ) 
			positionToBlock = 1;
		if (board[1] == playerSymbol && board[3] == playerSymbol && board[2] == ' ' ) 
			positionToBlock = 2;
		// block position in row 2
		if (board[4] == playerSymbol && board[5] == playerSymbol && board[6] == ' ' ) 
		positionToBlock = 6;
		if (board[4] == playerSymbol && board[6] == playerSymbol && board[5] == ' ' ) 
			positionToBlock = 5;
		if (board[5] == playerSymbol && board[6] == playerSymbol  && board[4] == ' ') 
			positionToBlock = 4;
		// block position in row 3
		if (board[7] == playerSymbol && board[8] == playerSymbol && board[9] == ' ' ) 
		  positionToBlock = 9;
		if (board[7] == playerSymbol && board[9] == playerSymbol  && board[8] == ' ') 
			  positionToBlock = 8;
		if (board[8] == playerSymbol && board[9] == playerSymbol  && board[7] == ' ') 
			  positionToBlock = 7;
		// block position in column 1
		if (board[1] == playerSymbol && board[4] == playerSymbol && board[7] == ' ' ) 
		positionToBlock = 7;
		if (board[1] == playerSymbol && board[7] == playerSymbol  && board[4] == ' ') 
			positionToBlock = 4;
		if (board[4] == playerSymbol && board[7] == playerSymbol  && board[1] == ' ') 
			positionToBlock = 1;
		// block position in column 2
		if (board[2] == playerSymbol && board[5] == playerSymbol && board[8] == ' ') 
		positionToBlock =8;
		if (board[2] == playerSymbol && board[8] == playerSymbol  && board[5] == ' ') 
			positionToBlock =5;
		if (board[5] == playerSymbol && board[8] == playerSymbol  && board[2] == ' ') 
			positionToBlock =8;
		// block position in column 3
		if (board[3] == playerSymbol && board[6] == playerSymbol && board[9] == ' ' ) 
		  positionToBlock =9;
		if (board[3] == playerSymbol && board[9] == playerSymbol && board[6] == ' ') 
			  positionToBlock =6;
		if (board[6] == playerSymbol && board[9] == playerSymbol && board[3] == ' ' ) 
			  positionToBlock =3;
		// block position in diagnol 1
		if (board[1] == playerSymbol && board[5] == playerSymbol && board[9] == ' ') 
		positionToBlock = 9;
		if (board[5] == playerSymbol && board[9] == playerSymbol && board[1] == ' ') 
			positionToBlock = 1;
		if (board[1] == playerSymbol && board[9] == playerSymbol && board[5] == ' ') 
			positionToBlock = 5;
		// block position in diagnol 2
		if (board[3] == playerSymbol && board[5] == playerSymbol && board[7] == ' ') 
		positionToBlock = 7;
		if (board[3] == playerSymbol && board[7] == playerSymbol && board[5] == ' ') 
			positionToBlock = 5;
		if (board[5] == playerSymbol && board[7] == playerSymbol && board[3] == ' ') 
			positionToBlock = 3;

		return positionToBlock;
	}

	public static void main(String args[]) {
		System.out.println("Welcome to tic tac board");
		board = creatingBoard();
		allowPlayerToChoose();
		showBoard();
		toss = doToss();
		turnUntilWeGetWinner();
	}
}
