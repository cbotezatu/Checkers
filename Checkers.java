import java.util.Scanner;

class Board {
	//Board Definition
	public String[][] CheckersBoard = { { " ", "  0 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", "<- X axis" },
			{ " ", "+", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "+" },
			{ "0", "|", " ", " ", "1", " ", " ", " ", "1", " ", " ", " ", "1", " ", " ", " ", "1", " ", "|" },
			{ "1", "|", "1", " ", " ", " ", "1", " ", " ", " ", "1", " ", " ", " ", "1", " ", " ", " ", "|" },
			{ "2", "|", " ", " ", "1", " ", " ", " ", "1", " ", " ", " ", "1", " ", " ", " ", "1", " ", "|" },
			{ "3", "|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|" },
			{ "4", "|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|" },
			{ "5", "|", "2", " ", " ", " ", "2", " ", " ", " ", "2", " ", " ", " ", "2", " ", " ", " ", "|" },
			{ "6", "|", " ", " ", "2", " ", " ", " ", "2", " ", " ", " ", "2", " ", " ", " ", "2", " ", "|" },
			{ "7", "|", "2", " ", " ", " ", "2", " ", " ", " ", "2", " ", " ", " ", "2", " ", " ", " ", "|" },
			{ " ", "+", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "+" },
			{ " ", "  0 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 " } };
	
	public String PostionInfo(int X, int Y) {
		return CheckersBoard[Y+2][X*2+2];
	}
	
	//Move Piece Method
	public void MovePiece(int X, int Y, int NewX, int NewY) {
		CheckersBoard[NewY + 2][NewX*2 + 2] = CheckersBoard[Y + 2][X*2 + 2];
		CheckersBoard[Y + 2][X*2 + 2] = " ";
	}
	
	//Print Board method
	public void BoardPrint() {
		for (int i = 0; i < CheckersBoard.length; i++) {
			for (int j = 0; j < CheckersBoard[i].length; j++) {
				// prints column
				System.out.print(CheckersBoard[i][j] + " ");
			}
			// prints new line
			System.out.println();
		}
	}
	
	//Checks whether an input position is inside the bounds of the board
	public boolean InsideBounds(int X, int Y, int NewX, int NewY) {
		return ((X >= 0 && X <= 7) && (Y >= 0 && Y <= 7) && (NewX >= 0 && NewX <= 7) && (NewY >= 0 && NewY <= 7));
	}
	
	//Checks if it is a valid move made by the player
	public boolean MoveValidity(int X, int Y, int NewX, int NewY, int turn) {
		if (turn == 1) {return ((Math.abs(NewX - X) == 1) && NewY - Y == 1);}
		else {return ((Math.abs(NewX - X) == 1) && NewY - Y == -1);}
	}
}


class Player {
	//PlayerNo
	int PlayerNo;
	
	public void SetPlayerNo(int n) {
		PlayerNo = n;
	}
	
}



public class Checkers {

	public static void main(String[] args) {
		//Initialize board
		Board CBoard = new Board();
		
		//Initialize players
		Player P1 = new Player();
		P1.SetPlayerNo(1);
		
		Player P2 = new Player();
		P2.SetPlayerNo(2);
		
		//Initialize Scanner
		
		int Turn = 0;
		System.out.println("Game is starting. Get ready \n");
		
		
		boolean GameOn = true;
		while(GameOn == true) {
		
			Turn = (Turn % 2) + 1;
			
			//Print Board
			CBoard.BoardPrint();
			
			//Check Player Turn
			System.out.println("Player " + Turn + " you're up!");
			
			//Getting User Input
			String[] InputOptions = {"X", "Y", "New X", "New Y"};
			int[] in = new int[4];
			for (int i = 0; i <= 3; i++) {
				try {
					System.out.println("Enter " + InputOptions[i] + " position ");
					Scanner sc = new Scanner(System.in);
					in[i] = sc.nextInt();
				} catch (java.util.InputMismatchException e) {
					System.out.println("Player " + Turn + " please give a valid input.");
					i = -1;
				}
			}
			System.out.println("Test " + (CBoard.PostionInfo(in[0], in[1])).equals(Integer.toString(Turn)));
			
			//Checking Validity of Input
			if (CBoard.InsideBounds(in[0], in[1], in[2], in[3]) && CBoard.MoveValidity(in[0], in[1], in[2], in[3], Turn)
					&& ((CBoard.PostionInfo(in[0], in[1])).equals(Integer.toString(Turn))) && (CBoard.PostionInfo(in[2], in[3]) == " ") ) {
				//Move Position
				CBoard.MovePiece(in[0], in[1], in[2], in[3]);
			} else {
				System.out.println("Player " + Turn + " you gave an invalid input. Please try again");
				Turn -= 1;
			}
		}
}

}
