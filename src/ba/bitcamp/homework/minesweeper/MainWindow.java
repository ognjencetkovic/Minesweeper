package ba.bitcamp.homework.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * Main window of the game
 * @author Ognjen
 *
 */
public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 6673697974312831509L;
	
	private static MainWindow game;
	private static Timer tmrGame;
	
	private int gameTimer;
	private JLabel lblGameTimer = new JLabel(String.valueOf(gameTimer), JLabel.CENTER);
	private JLabel lblMinesLeft = new JLabel(MinesweeperRun.getLeftMines() + "", JLabel.CENTER);;
	private JPanel pnlNorth = new JPanel();
	
	/**
	 * Default constructor
	 */
	private MainWindow() {
		
		game = this;
		
		setLayout(new BorderLayout());
		setSize(MinesweeperRun.getNumberOfColumns() * 30, MinesweeperRun.getNumberOfRows() * 30 + 80);
		
		pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, getWidth() / 6, 10));
		pnlNorth.setBackground(new Color(113, 137, 255));
		initNorthPanel(lblGameTimer);
		initNorthPanel(lblMinesLeft);
		tmrGame = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblGameTimer.setText(String.valueOf(++gameTimer));
			}
		});
		
		Timer tmrMinesLeft = new Timer(20, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblMinesLeft.setText(MinesweeperRun.getLeftMines() + "");
			}
		});
		
		tmrMinesLeft.start();
		
		add(pnlNorth, BorderLayout.NORTH);
		
		add(new GamePanel());
		
		setTitle("Minesweeper");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initNorthPanel(JLabel lbl) {
		lbl.setPreferredSize(new Dimension(80, 30));
		lbl.setOpaque(true);
		lbl.setForeground(Color.RED);
		lbl.setBackground(new Color(19, 27, 35));
		lbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		lbl.setBorder(BorderFactory.createEtchedBorder());
		
		
		pnlNorth.add(lbl);
	}

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		new OptionFrame(null);
	}

	/**
	 * Starts the game
	 */
	public static void startGame() {
		game = new MainWindow();
	}

	/**
	 * Displays option window
	 */
	public static void gameOver() {
		MainWindow.tmrGame.stop();
		new OptionFrame(game);
	}

	/**
	 * Starts the timer
	 */
	public static void startTimer(){
		tmrGame.start();
	}
	
}
