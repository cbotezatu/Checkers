
//Course:  02160 Agile object-oriented Software Development 
//Task:    Assignment 3
//Authors: Cristian Botezatu (s164571), Alexander Bøgely Holstrup (s164566), Lasse Starklit (s165498)

import java.util.Scanner;

class BoardCheckers extends Position {
	// Board Definition
	public static String[][] CheckersBoard = { { "   ", " 1 ", "   ", " 1 ", "   ", " 1 ", "   ", " 1 " },
			{ " 1 ", "   ", " 1 ", "   ", " 1 ", "   ", " 1 ", "   " },
			{ "   ", " 1 ", "   ", " 1 ", "   ", " 1 ", "   ", " 1 " },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ " 2 ", "   ", " 2 ", "   ", " 2 ", "   ", " 2 ", "   " },
			{ "   ", " 2 ", "   ", " 2 ", "   ", " 2 ", "   ", " 2 " },
			{ " 2 ", "   ", " 2 ", "   ", " 2 ", "   ", " 2 ", "   " } };

	public String PostionInfo(int X, int Y) {
		return CheckersBoard[Y][X];
	}

	// Move Piece Method
	public void MovePiece(int X, int Y, int NewX, int NewY) {
		CheckersBoard[NewY][NewX] = CheckersBoard[Y][X];
		CheckersBoard[Y][X] = "   ";
	}

	// Print Board method
	public void BoardPrint() {
		System.out.println("    0  1  2  3  4  5  6  7   <- X axis" + "\n  +------------------------+");
		int i = 0;
		for (String[] column : CheckersBoard) {
			System.out.print(i + " " + "|");
			for (String row : column) {
				System.out.printf(row);
			}
			System.out.println("|");
			i += 1;
		}
		System.out.println("  +------------------------+" + "\n    0  1  2  3  4  5  6  7");
	}
}

class Position {

	// Checks whether an input position is inside the bounds of the board
	public boolean InsideBounds(int X, int Y, int NewX, int NewY) {
		return ((X >= 0 && X <= 7) && (Y >= 0 && Y <= 7) && (NewX >= 0 && NewX <= 7) && (NewY >= 0 && NewY <= 7));
	}

	// Checks if it is a valid move made by the player
	public boolean MoveValidity(int X, int Y, int NewX, int NewY, int turn) {
		boolean result = true;
			if (turn == 1 && NewY == 7) {
				
				result = ((Math.abs(NewX - X) == 1) && NewY - Y == 1);
			} else if (turn == 2 && NewY == 1){
				result = ((Math.abs(NewX - X) == 1) && NewY - Y == -1);
			}
		return result;
	}
}

class PlayerCheckers {
	// PlayerNo
	int PlayerNo;

	public void SetPlayerNo(int n) {
		PlayerNo = n;
	}
}

interface Piece {
	public boolean Piece(int X, int Y, int NewX, int NewY, int turn);
}

class Pawn extends Position implements Piece {

	@Override
	public boolean Piece(int X, int Y, int NewX, int NewY, int turn) {
		if (turn == 1) {
			return ((Math.abs(NewX - X) == 1) && NewY - Y == 1);
		} else {
			return ((Math.abs(NewX - X) == 1) && NewY - Y == -1);
		}
	}
}


/*
class Queen extends Position implements Piece {
	public boolean Piece(int X, int Y, int NewX, int NewY, int turn) {
		boolean result = true;
			if (turn == 1) {
				for (int i = 1; i <= 7; i++) {
					result = ((Math.abs(NewX - X) == i) || NewY - Y == i);
				}
			} else {
				for (int i = 1; i <= 7; i++) {
					result = ((Math.abs(NewX - X) == i) || NewY - Y == -i);
				}
			}
		return result;
	}
}
*/

public class Checkers {

	public static void main(String[] args) {
		// Initialize board
		BoardCheckers CBoard = new BoardCheckers();

		// Initialize players
		PlayerCheckers P1 = new PlayerCheckers();
		P1.SetPlayerNo(1);

		PlayerCheckers P2 = new PlayerCheckers();
		P2.SetPlayerNo(2);

		// Initialize Scanner
		int Turn = 0;
		System.out.println("Game is starting. Get ready \n");

		boolean GameOn = true;
		while (GameOn == true) {

			Turn = (Turn % 2) + 1;

			// Print Board
			CBoard.BoardPrint();

			// Check Player Turn
			System.out.println("Player " + Turn + " you're up!");

			// Getting User Input
			String[] InputOptions = { "X", "Y", "New X", "New Y" };
			int[] in = new int[4];
			for (int i = 0; i <= 3; i++) {
				try {
					System.out.println("Enter " + InputOptions[i] + " position ");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					in[i] = sc.nextInt();
				} catch (java.util.InputMismatchException e) {
					System.out.println("Player " + Turn + " please give a valid input.");
					i = -1;
				}
			}

			// Checking Validity of Input
			if (CBoard.InsideBounds(in[0], in[1], in[2], in[3]) && CBoard.MoveValidity(in[0], in[1], in[2], in[3], Turn)
					&& ((CBoard.PostionInfo(in[0], in[1])).equals(" " + Integer.toString(Turn) + " "))
					&& (CBoard.PostionInfo(in[2], in[3]) == "   ")) {
				// Move Position
				CBoard.MovePiece(in[0], in[1], in[2], in[3]);
			} else {
				System.out.println("Player " + Turn + " you gave an invalid input. Please try again");
				Turn -= 1;
			}
		}
	}
}
