package ba.bitcamp.homework.minesweeper;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
/**
 * Game panel
 * @author Ognjen
 *
 */
public class GamePanel extends JPanel{
	
	private static final long serialVersionUID = 9166512269339678343L;

	private Field[][] fields;
	private MinesweeperRun game;
	
	/**
	 * Default constructor
	 */
	public GamePanel() {
		
		game = new MinesweeperRun();
		
		setLayout(new GridLayout(MinesweeperRun.getNumberOfRows(), MinesweeperRun.getNumberOfColumns()));
		fields = new Field[MinesweeperRun.getNumberOfRows()][MinesweeperRun.getNumberOfColumns()];
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				fields[i][j] = new Field(i, j);
				fields[i][j].addMouseListener(new MouseHandler());
				add(fields[i][j]);
			}
		}
		
		setOpaque(true);
		setBackground(ColorPalette.LIGHT_BLUE);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, ColorPalette.LIGHT_BLUE, ColorPalette.DARK_BLUE));
	}
	
	private boolean isGameOver(){
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				if(!fields[i][j].isOpened() && game.elementAt(i, j) != -1){
					return false;
				}
			}
		}
		return true;
	}
	
	private class MouseHandler extends MouseAdapter{
	
		@Override
		public void mousePressed(MouseEvent e) {
			
			MainWindow.startTimer();
			
			Field tmp = (Field) e.getSource();
			
			if (e.getButton() == MouseEvent.BUTTON1){
				if(tmp.getText().equals("") && !tmp.isOpened() && !tmp.isFlaged()){
					if(game.elementAt(tmp.getIndexOfRow(), tmp.getIndexOfColumn()) != -1){
						openField(tmp.getIndexOfRow(), tmp.getIndexOfColumn());
					} else {
						drawGameOverPanel();
						MainWindow.gameOver();
					}
				}
				if(isGameOver()){
					MainWindow.gameOver();
				}
			} else if (e.getButton() == MouseEvent.BUTTON3){
				if(!tmp.isOpened()){
					if(tmp.getText().equals("") && !tmp.isFlaged()){
						tmp.setFlag(true);
					} else if(tmp.isFlaged()){
						tmp.setFlag(false);
					}
					else if(tmp.getText().equals("?"))
						tmp.setText("");
				}
			}
			
			repaint();
			
		}
		
		private void drawGameOverPanel() {
			for (int i = 0; i < fields.length; i++) {
				for (int j = 0; j < fields[i].length; j++) {
					if(game.elementAt(i, j) == -1){
						fields[i][j].explode();
					}
				}
			}
		}

		private void openField(int row, int column) {
			if(row < 0 || row >= MinesweeperRun.getNumberOfRows() || column < 0 || column >= MinesweeperRun.getNumberOfColumns())
				return;
			Field label = fields[row][column];
			if(game.elementAt(row, column) == 0 && !label.isOpened()){
				label.setText(game.elementAt(row, column));
				openField(row - 1, column - 1);
				openField(row - 1, column);
				openField(row - 1, column + 1);
				openField(row, column - 1);
				openField(row, column + 1);
				openField(row + 1, column - 1);
				openField(row + 1, column);
				openField(row + 1, column + 1);
			} else if(game.elementAt(row, column) != -1 && !label.isOpened()){
				label.setText(game.elementAt(row, column));
			}
		}
	}	
}
