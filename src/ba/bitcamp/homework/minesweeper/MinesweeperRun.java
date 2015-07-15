package ba.bitcamp.homework.minesweeper;

import java.util.Random;
/**
 * Class representing logic behind the game of minesweeper.
 * @author Ognjen
 *
 */
public class MinesweeperRun {

	
	private static int numberOfRows; 
	private static int numberOfColumns; 
	private static int numberOfMines; 
	private static int leftMines = numberOfMines; 
	
	private int[][] field;
	
	/**
	 * Default constructor
	 */
	public MinesweeperRun() {
		super();
		generateField();
	}

	private void generateField() {
		field = new int[MinesweeperRun.numberOfRows][MinesweeperRun.numberOfColumns];
		generateMines();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(field[i][j] == -1){
					increaseFields(i, j);
				}
			}
		}
	}

	private void generateMines() {
		for (int i = 0; i < numberOfMines; i++) {
			while (true) {
				Random rand = new Random();
				int row = rand.nextInt(numberOfRows);
				int column = rand.nextInt(numberOfColumns);
				if(field[row][column] == 0){
					field[row][column] = -1;
					break;
				}
			}
		}
	}
	
	private void increaseFields(int row, int column) {
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				try {
					if(field[i][j] != -1){
						field[i][j]++;						
					}
				} catch (IndexOutOfBoundsException e) { }
			}
		}
	}

	/**
	 * @return the numberOfRows
	 */
	public static int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * @return the numberOfColumns
	 */
	public static int getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * @return the numberOfMines
	 */
	public static int getNumberOfMines() {
		return numberOfMines;
	}

	/**
	 * @param numberOfRows the numberOfRows to set
	 */
	public static void setNumberOfRows(int numberOfRows) {
		MinesweeperRun.numberOfRows = numberOfRows;
	}

	/**
	 * @param numberOfColumns the numberOfColumns to set
	 */
	public static void setNumberOfColumns(int numberOfColumns) {
		MinesweeperRun.numberOfColumns = numberOfColumns;
	}

	/**
	 * @param numberOfMines the numberOfMines to set
	 */
	public static void setNumberOfMines(int numberOfMines) {
		MinesweeperRun.numberOfMines = numberOfMines;
	}

	/**
	 * @return the leftMines
	 */
	public static int getLeftMines() {
		return leftMines;
	}

	/**
	 * @param leftMines the leftMines to set
	 */
	public static void setLeftMines(int leftMines) {
		MinesweeperRun.leftMines = leftMines;
	}

	/**
	 * Returns element from matrix
	 * @param row Index of row
	 * @param column Index of column
	 * @return Element at given position
	 */
	public int elementAt(int row, int column) {
		return field[row][column];
	}
	
}
