package ba.bitcamp.homework.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Option frame. Gets information about game (size of table, number of mines).
 * @author Ognjen
 *
 */
public class OptionFrame extends JDialog{

	private static final long serialVersionUID = 2089052214901974566L;

	public static final int OPTION_FRAME_WIDTH = 400;
	public static final int OPTION_FRAME_HEIGHT = 180;
	public static final int BUTTON_WIDTH = 100;
	public static final int BUTTON_HEIGHT = 25;
	
	private JLabel lblNumOfRows = new JLabel("Number of rows");
	private JLabel lblNumOfColumns = new JLabel("Number of columns");
	private JLabel lblNumOfMines = new JLabel("Number of mines");
	private JTextField txtNumOfRows = new JTextField("20");
	private JTextField txtNumOfColumns = new JTextField("20");
	private JTextField txtNumOfMines = new JTextField("50");
	private JButton btnStart = new JButton("Start");
	private JButton btnGit = new JButton("Git");
	private JPanel pnlCenter = new JPanel();
	private JPanel pnlSouth = new JPanel();
	private Dimension btnDimension = new Dimension(OptionFrame.BUTTON_WIDTH, OptionFrame.BUTTON_HEIGHT);
	
	/**
	 * Default constructor.
	 * @param game Main window
	 */
	public OptionFrame(JFrame game) {
		
		pnlCenter.setLayout(new FlowLayout());
		initField(lblNumOfRows, txtNumOfRows);
		initField(lblNumOfColumns, txtNumOfColumns);
		initField(lblNumOfMines, txtNumOfMines);
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MinesweeperRun.setNumberOfRows(Integer.parseInt(txtNumOfRows.getText()));
					MinesweeperRun.setNumberOfColumns(Integer.parseInt(txtNumOfColumns.getText()));
					MinesweeperRun.setNumberOfMines(Integer.parseInt(txtNumOfMines.getText()));
					MinesweeperRun.setLeftMines(MinesweeperRun.getNumberOfMines());
					if(MinesweeperRun.getNumberOfMines() >= MinesweeperRun.getNumberOfRows() * MinesweeperRun.getNumberOfColumns())
						throw new IllegalArgumentException();
					if(MinesweeperRun.getNumberOfRows() > 50 || MinesweeperRun.getNumberOfColumns() > 50)
						throw new ArithmeticException();
					setVisible(false);
					if(game != null)
						game.dispose();
					MainWindow.startGame();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Wrong input!");
				} catch (IllegalArgumentException e2) {
					JOptionPane.showMessageDialog(null, "Too many mines!");
				} catch (ArithmeticException e2) {
					JOptionPane.showMessageDialog(null, "Too many fields (max 50x50)!");
				}
				
			}
		});
		
		btnGit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String url = "https://github.com/ognjencetkovic/Minesweeper";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			       }
			       catch (java.io.IOException e1) {
			           System.out.println(e1.getMessage());
			       }
				
			}
		});
		
		pnlSouth.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.GRAY));
		pnlSouth.setLayout(new FlowLayout());
		btnStart.setPreferredSize(btnDimension);
		btnGit.setPreferredSize(btnDimension);
		pnlSouth.add(btnStart);
		pnlSouth.add(btnGit);
		
		add(pnlCenter);
		add(pnlSouth, BorderLayout.SOUTH);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		setTitle("Minesweeper");
		setSize(OptionFrame.OPTION_FRAME_WIDTH, OptionFrame.OPTION_FRAME_HEIGHT);
		setLocationRelativeTo(game);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		System.exit(0);
	}

	private void initField(JLabel lbl, JTextField txt) {
		initLabel(lbl);
		initTextField(txt);
		pnlCenter.add(lbl);
		pnlCenter.add(txt);		
	}


	private void initTextField(JTextField txt) {
		txt.setPreferredSize(new Dimension(50, 20));
		txt.setHorizontalAlignment(JTextField.RIGHT);
	}


	private void initLabel(JLabel lbl) {
		lbl.setPreferredSize(new Dimension(OptionFrame.OPTION_FRAME_WIDTH / 2, 30));
		lbl.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
	}
	
}
