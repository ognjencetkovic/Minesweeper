package ba.bitcamp.homework.minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
/**
 * Class representing one field in minesweeper game.
 * @author Ognjen
 *
 */
public class Field extends JLabel{
	
	private static final long serialVersionUID = -6421369856223564017L;
	
	private ImageIcon imgFlag = new ImageIcon(getClass().getClassLoader().getResource("images/flag.png"));
	private ImageIcon imgMine = new ImageIcon(getClass().getClassLoader().getResource("images/boom1.png"));

	private int indexOfRow;
	private int indexOfColumn;
	private boolean opened;
	private boolean flagged;
	
	/**
	 * Default constructor
	 * @param indexOfRow Index of row  
	 * @param indexOfColumn Index of column
	 */
	public Field(int indexOfRow, int indexOfColumn) {
		
		this.indexOfRow = indexOfRow;
		this.indexOfColumn = indexOfColumn;
		
		setHorizontalAlignment(JLabel.CENTER);
		setOpaque(true);
		setIcon(null);
		setInitialColor();
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, ColorPalette.ROYAL_BLUE, ColorPalette.DIM_BLACK));
		setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		
		addMouseListener(new MouseHandler());
		
	}
	
	private void setInitialColor() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, ColorPalette.ROYAL_BLUE, ColorPalette.DIM_BLACK));
		int r = 164;
		int g = 162;
		int b = 208;
		int step = 100 / MinesweeperRun.getNumberOfRows();
		setBackground(new Color(r - indexOfRow * step, g - indexOfRow * step, b));
	}

	/**
	 * Changes text of field
	 * @param num Number to be displayed
	 */
	public void setText(int num) {
		if(flagged){
			setFlag(false);
		}
		setBackground(ColorPalette.POWDER_BLUE);
		setBorder(BorderFactory.createLineBorder(ColorPalette.ROYAL_BLUE, 1, true));
		setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.WHITE));
		opened = true;
		switch (num) {
		case 0:
			setText("");
			break;
		case 1:
			setText(String.valueOf(num));
			setForeground(ColorPalette.ROYAL_BLUE);
			break;
		case 2:
			setText(String.valueOf(num));
			setForeground(Color.GREEN);
			break;
		case 3:
			setText(String.valueOf(num));
			setForeground(Color.RED);
			break;
		case 4:
			setText(String.valueOf(num));
			setForeground(Color.CYAN);
			break;
		case 5:
			setText(String.valueOf(num));
			setForeground(Color.MAGENTA);
			break;
		case 6:
			setText(String.valueOf(num));
			setForeground(Color.ORANGE);
			break;
		case 7:
			setText(String.valueOf(num));
			setForeground(Color.GRAY);
			break;
		case 8:
			setText(String.valueOf(num));
			setForeground(Color.YELLOW);
			break;
		}
	}
	
	private class MouseHandler extends MouseAdapter {
		
		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			Field tmp = (Field) e.getSource();
			if(!tmp.isOpened()){
				tmp.mouseOnField();
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			Field tmp = (Field) e.getSource();
			if(!tmp.isOpened()){
				tmp.setInitialColor();
			}
		}
	}

	/**
	 * @return the opened
	 */
	public boolean isOpened() {
		return opened;
	}

	/**
	 * @return the flaged
	 */
	public boolean isFlaged() {
		return flagged;
	}

	/**
	 * @return the indexOfRow
	 */
	public int getIndexOfRow() {
		return indexOfRow;
	}

	/**
	 * @return the indexOfColumn
	 */
	public int getIndexOfColumn() {
		return indexOfColumn;
	}

	private void mouseOnField() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, ColorPalette.ROYAL_BLUE, ColorPalette.DIM_BLACK));
		int r = 54;
		int g = 72;
		int b = 208;
		int step = 100 / MinesweeperRun.getNumberOfRows();
		setBackground(new Color(r + indexOfRow * step, g + indexOfRow * step, b));
	}
	
	/**
	 * Sets flag if passed parameter is true
	 * @param flaged Is field flagged
	 */
	public void setFlag(boolean flagged){
		if(flagged){
			setIcon(imgFlag);
			MinesweeperRun.setLeftMines(MinesweeperRun.getLeftMines() - 1);
		} else {
			setIcon(null);
			setText("?");
			setForeground(Color.WHITE);
			MinesweeperRun.setLeftMines(MinesweeperRun.getLeftMines() + 1);
		}
		this.flagged = flagged;
	}

	/**
	 * Sets mine icon
	 */
	public void explode() {
		setIcon(imgMine);
	}
	
}
