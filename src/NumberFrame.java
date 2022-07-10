import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

enum SORTTYPE {BUBBLE_SORT, SELECTION_SORT};
public class NumberFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static SORTTYPE SortType = SORTTYPE.BUBBLE_SORT;
	///////////
	NumberPanel pnCenter =null;
	JTextField txtNumber = null;
	JTextField txtNumber1 = null;
	
	public void drawControl() {
		//add numbers 
		int num = Integer.parseInt(txtNumber.getText());
		Number.COLOR1 = Color.black;
		pnCenter.addNumbers(num);
		pnCenter.repaint();
		
		//add speed into panel
		int num1 = Integer.parseInt(txtNumber1.getText());
		Number.COLOR1 = Color.black;
		pnCenter.createSpeed(num1);
		pnCenter.repaint();
	}
	
	//add control
	public void addControl() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnNorth = new JPanel();
		con.add(pnNorth, BorderLayout.NORTH);
		
		JPanel pnNorthBorder = new JPanel();
		pnNorthBorder.setLayout(new BoxLayout(pnNorthBorder,BoxLayout.Y_AXIS));

		JPanel pnNorthBorderLine1 = new JPanel();
		JLabel lblNumber = new JLabel("Size of array: ");
		txtNumber = new JTextField(3);
		JButton btnDraw = new JButton("Draw");
		pnNorthBorderLine1.add(lblNumber);
		pnNorthBorderLine1.add(txtNumber);
		pnNorthBorderLine1.add(btnDraw);

		
		JLabel lblSpeed = new JLabel("Speed (ms):");
		txtNumber1 = new JTextField(4);
		JButton btnSet = new JButton("Set");
		pnNorthBorderLine1.add(lblSpeed);
		pnNorthBorderLine1.add(txtNumber1);
		pnNorthBorderLine1.add(btnSet);

		
		JPanel pnNorthBorderLine2 = new JPanel();
		pnNorthBorderLine2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnNorthBorderLine1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblAlgorithm = new JLabel("Algorithm: ");
		JComboBox jComboBox = new JComboBox(new String[]{"Bubble sort","Selection sort"});
		//final JComboBox cboAlgorithm = new JComboBox(new String[]{"Bubble sort","Selection sort"});
		final JComboBox cboAlgorithm = jComboBox;
		JButton btnSort = new JButton("Start Sort!");
		pnNorthBorderLine2.add(lblAlgorithm);
		pnNorthBorderLine2.add(cboAlgorithm);
		pnNorthBorderLine2.add(btnSort);
		
		pnNorthBorder.add(pnNorthBorderLine1);
		pnNorthBorder.add(pnNorthBorderLine2);
		pnNorthBorderLine1.setBackground(Color.LIGHT_GRAY);
		pnNorthBorderLine2.setBackground(Color.LIGHT_GRAY);
		
		con.add(pnNorthBorder, BorderLayout.NORTH);
		pnCenter = new NumberPanel();
		con.add(pnCenter, BorderLayout.CENTER);
		
		//Them su kien cho button
		btnDraw.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				drawControl();
			}
		});
		
		btnSort.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = cboAlgorithm.getSelectedIndex();
				switch(n) {
				case 0:
					SortType = SORTTYPE.BUBBLE_SORT;
					break;
				case 1:
					SortType = SORTTYPE.SELECTION_SORT;
					break;
				}
				System.out.println(n);
				NumberPanelRunable run = new NumberPanelRunable(pnCenter);
				Thread th = new Thread(run);
				th.start();
			}
		} );
		
	}

	public NumberFrame() {
		addControl();
		setSize(1000,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}