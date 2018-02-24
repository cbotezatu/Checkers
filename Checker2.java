//Course:  02160 Agile object-oriented Software Development 
//Task:    Assignment 3
//Authors: Cristian Botezatu (s164571), Alexander Bøgely Holstrup (s164566), Lasse Starklit (s165498)

//import the module scanner
import java.util.Scanner;

@SuppressWarnings("serial")
class NoPointException extends Exception {
	public NoPointException(String message) {
		super(message);
	}
}

interface PieceChecker {

	public void setArbitraryPosition(PositionChecker currentPosition); // on abstract class

	public boolean isValidPosition(PositionChecker newPosition); // on lowest type

	public boolean move(PositionChecker newPosition); // on abstract class

	public void setArbitraryPosition(int xold, int yold);

}

class PositionChecker {
	private int x, y;

	public PositionChecker(int x, int y) {
		this.x = x - 1;
		this.y = y - 1;
	}

	public boolean isOnBoard() {
		return (x >= 0 && x <= 7 && y >= 0 && y <= 7);
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

class PlayerChecker {
	private boolean isWhite;

	public PlayerChecker(String n) {
	}

	public void setColorWhite(boolean w) {
		this.isWhite = w;
	}

	public boolean isWhite() {
		return isWhite;
	}
}

abstract class AbstractPieceChecker implements PieceChecker {
	protected PositionChecker current;
	protected PlayerChecker owner;

	public AbstractPieceChecker(PlayerChecker owner) {
		this.owner = owner;
	}

	public void setArbitraryPosition(int x, int y) {
		setArbitraryPosition(new PositionChecker(x, y));
	}

	public void setArbitraryPosition(PositionChecker currentPosition) {
		if (currentPosition.isOnBoard()) {
			this.current = currentPosition;
		}
	}

	public boolean move(PositionChecker newPosition) {
		if (isValidPosition(newPosition)) {
			this.current = newPosition;
			return true;
		}
		return false;
	}
}

class SimpleChecker extends AbstractPieceChecker {
	public SimpleChecker(PlayerChecker owner) {
		super(owner);
	}

	public boolean isValidPosition(PositionChecker newPosition) {
		if (newPosition.isOnBoard()) {
			int diffX = current.x() - newPosition.x();
			int diffY = current.y() - newPosition.y();
			if (diffX != 0) {
				return false;
			} else {
				if (owner.isWhite()) {
					if (diffY == -1 && ((diffX == -1) || (diffX == 1))) {
						return true;
					} else {
						return false;
					}
				} else {
					if (diffY == 1 && ((diffX == -1) || (diffX == 1))) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}
}

/*
 * Develop the game a bit
 * 
 * class QueenChecker extends AbstractPieceChecker { public
 * QueenChecker(PlayerChecker owner) { super(owner); }
 * 
 * @Override public boolean isValidPosition(PositionChecker newPosition) { if
 * (newPosition.isOnBoard()) { int diffX = Math.abs(current.x() -
 * newPosition.x()); int diffY = Math.abs(current.y() - newPosition.y()); if
 * (((diffX > 0) && (diffY == 0)) && ((diffX == 0) && (diffY > 0))) { return
 * true; } } return false; } }
 */
class CheckerBoard {

	public static String[][] checker = { { "   ", " 1 ", "   ", " 1 ", "   ", " 1 ", "   ", " 1 " },
			{ " 1 ", "   ", " 1 ", "   ", " 1 ", "   ", " 1 ", "   " },
			{ "   ", " 1 ", "   ", " 1 ", "   ", " 1 ", "   ", " 1 " },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ " 2 ", "   ", " 2 ", "   ", " 2 ", "   ", " 2 ", "   " },
			{ "   ", " 2 ", "   ", " 2 ", "   ", " 2 ", "   ", " 2 " },
			{ " 2 ", "   ", " 2 ", "   ", " 2 ", "   ", " 2 ", "   " } };

	public void printBoard() {
		System.out.println("    0  1  2  3  4  5  6  7   <- X axis" + "\n  +------------------------+");
		int i = 0;
		for (String[] column : checker) {
			System.out.print(i + " " + "|");
			for (String row : column) {
				System.out.printf(row);
			}
			System.out.println("|");
			i += 1;
		}
		System.out.println("  +------------------------+" + "\n    0  1  2  3  4  5  6  7");
	}

	public void changeboard(int x, int y, String[][] checker) {
		if (!(checker[y][x] == " 1 ")) {
			System.err.println("You do not have a piece in that position");

		} else {
			CheckerBoard.checker[y][x] = "   ";
			printBoard();

		}

	}

}

public class Checker2 {

	public static void main(String[] args) {
		PlayerChecker p1 = new PlayerChecker("White player");
		p1.setColorWhite(true);
		PlayerChecker p2 = new PlayerChecker("Black player");
		p2.setColorWhite(false);
		boolean No = p1.isWhite();
		while (true) {

			SimpleChecker whiteSimpleChecker = new SimpleChecker(p1);
			CheckerBoard board = new CheckerBoard();
			board.printBoard();

			if (No == true) {
				System.out.print("Turn of player no. 1");
			} else {
				System.out.print("Turn of player no. 2");
			}

			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in); // Reading from System.in
			System.out.println("\nCoordinate of piece to move \n");
			System.out.print("   Enter X: ");
			int Xold = s.nextInt(); // Scans the next token of the input as an int.
			System.out.print("   Enter Y: ");
			int Yold = s.nextInt();
/*			
			try {
				whiteSimpleChecker.isValidPosition(Xold, Yold);}
			catch (NoPointException e) {
				System.out.println(e.getMessage());
			}
*/
			
			System.out.println("\nCoordinate of piece to move \n");
			System.out.print("   Enter X: ");
			int Xnew = s.nextInt();
			System.out.print("   Enter Y: ");
			int Ynew = s.nextInt();

/*			
			try {
				whiteSimpleChecker.isValidPosition(Xnew, Ynew);}
			catch (NoPointException e) {
				System.out.println(e.getMessage());
			}
*/
			
			//Here we should substitute the relations with whiteSimpleChecker.isValidPosition(Xnew, Ynew)
			if (No == p1.isWhite() && (Xnew - Xold == -1 || Xnew - Xold == 1) && (Ynew - Yold == 1) && (Xnew <= 7) && (Xnew >= 0)
					&& (Xold <= 7) && (Xold >= 0) && (Ynew <= 7) && (Ynew >= 0) && (Yold <= 7) && (Yold >= 0) && (CheckerBoard.checker[Yold][Xold] == " 1 ") && (CheckerBoard.checker[Ynew][Xnew] == "   ")) {
				// Empty the old position of the piece we want to move by replacing with a space
				CheckerBoard.checker[Ynew][Xnew] = CheckerBoard.checker[Yold][Xold];
				CheckerBoard.checker[Yold][Xold] = "   ";
				System.out.println("\nPiece moved!");
				No = p2.isWhite();
			} else if (No == p1.isWhite()) {
				System.out.print("Wrong numbers for No 1\n");
			} else if (No == p2.isWhite() && (Xnew - Xold == -1 || Xnew - Xold == 1) && (Ynew - Yold == -1) && (Xnew <= 7)
					&& (Xnew >= 0) && (Xold <= 7) && (Xold >= 0) && (Ynew <= 7) && (Ynew >= 0) && (Yold <= 7)
					&& (Yold >= 0) && (CheckerBoard.checker[Yold][Xold] == " 2 ") && (CheckerBoard.checker[Ynew][Xnew] == "   ")) {
				CheckerBoard.checker[Ynew][Xnew] = CheckerBoard.checker[Yold][Xold];
				CheckerBoard.checker[Yold][Xold] = "   ";
				System.out.println("\nPiece moved!");
				No = p1.isWhite();
			} else if (No == p2.isWhite()) {
				System.out.print("Wrong numbers for No 2\n");
			}

		}
	}
}