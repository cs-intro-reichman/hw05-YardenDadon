/** 
 *  Game of Life.
 *  Usage: "java GameOfLife fileName"
 *  The file represents the initial board.
 *  The file format is described in the homework document.
 */

public class GameOfLife {

	public static void main(String[] args) {
		String fileName = args[0];
		//// Uncomment the test that you want to execute, and re-compile.
		//// (Run one test at a time).
		//test1(fileName);
		//// test2(fileName);
		//// test3(fileName, 3);
		play(fileName);
		
	}
	
	// Reads the data file and prints the initial board.
	private static void test1(String fileName) {
		int[][] board = read(fileName);
		print(board);
	}
		
	// Reads the data file, and runs a test that checks 
	// the count and cellValue functions.
	private static void test2(String fileName) {
		int[][] board = read(fileName);
		print(board);
		//// Write here code that tests that the count and cellValue functions
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[0].length - 1; j++) {
				System.out.println(i + " " + j + " - status: " + board[i][j] + " - count: " + count(board, i, j) + " - next gen: " + cellValue(board, i, j));
			}
		}
	}
		
	// Reads the data file, plays the game for Ngen generations, 
	// and prints the board at the beginning of each generation.
	private static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);
		}
	}
		
	// Reads the data file and plays the game, for ever.
	public static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show(board);
			board = evolve(board);
		}
	}
	
	// Reads the initial board configuration from the file whose name is fileName, uses the data
	// to construct and populate a 2D array that represents the game board, and returns this array.
	// Live and dead cells are represented by 1 and 0, respectively. The constructed board has 2 extra
	// rows and 2 extra columns, containing zeros. These are the top and the bottom row, and the leftmost
	// and the rightmost columns. Thus the actual board is surrounded by a "frame" of zeros. You can think
	// of this frame as representing the infinite number of dead cells that exist in every direction.
	// This function assumes that the input file contains valid data, and does no input testing.
	public static int[][] read(String fileName) {
		In in = new In(fileName); // Constructs an In object for reading the input file (Puts a "pointer" on line zero of the given file)
		int rows = Integer.parseInt(in.readLine());
		int cols = Integer.parseInt(in.readLine());
		int[][] board = new int[rows + 2][cols + 2];
		for (int outerLoop = 0; outerLoop < rows; outerLoop++){
			String line = in.readLine();
			for (int innerLoop = 0; innerLoop < line.length(); innerLoop++){
				if (line.charAt(innerLoop) == 'x'){
					board[outerLoop + 1][innerLoop + 1] = 1;
				}
			}
		}
		return board;
	}
	
	// Creates a new board from the given board, using the rules of the game.
	// Uses the cellValue(board,i,j) function to compute the value of each 
	// cell in the new board. Returns the new board.
	public static int[][] evolve(int[][] board) {
		int[][] newBoard = new int[board.length][board[0].length];
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[0].length - 1; j++) {

				newBoard[i][j] = cellValue(board, i, j);
			}
		}
		return newBoard;
	}

		public static int cellValue(int[][] board, int i, int j) {
		int count = count(board, i, j);
		if ((board[i][j] == 1) && ((count < 2) || (count > 3))){return 0;}
		if ((board[i][j] == 0) && ((count == 3) || (count == 2))){return 1;}
		return 0;
	}
	
		public static int count(int[][] board, int i, int j) {
		int counter = 0;
		if (board[i+1][j] == 1) {counter++;}
		if (board[i-1][j] == 1) {counter++;}
		if (board[i][j+1] == 1) {counter++;}
		if (board[i][j-1] == 1) {counter++;}
		if (board[i-1][j-1] == 1) {counter++;}
		if (board[i-1][j+1] == 1) {counter++;}
		if (board[i+1][j-1] == 1) {counter++;}
		if (board[i+1][j+1] == 1) {counter++;}
		return counter;
	}
	
	public static void print(int[][] arr) {
		for (int i = 1; i < arr.length - 1; i++)  {
            for (int j = 1; j < arr[0].length - 1; j++) {
                System.out.printf("%3s", arr[i][j]);
            }
            System.out.println();
        }
	}
		
	public static void show(int[][] board) {
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);

		
		StdDraw.enableDoubleBuffering();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(1000); 
	}
}
