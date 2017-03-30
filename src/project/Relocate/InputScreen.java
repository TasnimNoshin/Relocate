package project.Relocate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author Tasnim Bari Noshin
 *
 */

public class InputScreen {
	JFrame frame = new JFrame("Relocate");
	JPanel panel = new JPanel();
	JLabel label = new JLabel("ReLocate", SwingConstants.CENTER);
	JTextField textName = new JTextField("Job Name");
	JTextField textProvince = new JTextField("Province Code");
	JTextField textIncome = new JTextField("Annual Income");
	final JTextArea textArea = new JTextArea(5, 20);
	JButton button = new JButton("Search");
	//FileWriter output;

	public InputScreen() throws IOException {
		PrintWriter output = new PrintWriter("data/a3_out.txt");
		textArea.setEditable(false);
		button.setPreferredSize(new Dimension(200, 50));
		panel.setLayout(new BorderLayout());
		label.setFont(new Font("Consolas", Font.HANGING_BASELINE, 30));
		panel.setPreferredSize(new Dimension(400, 300));
		textName.setBounds(70, 70, 200, 30);
		textProvince.setBounds(70, 100, 200, 30);
		textIncome.setBounds(70, 130, 200, 30);
		textArea.setBounds(1, 1, 2, 3);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jn = textName.getText();
				String pc = textProvince.getText();
				String ai = textIncome.getText();
				try {
					Searcher search = new Searcher();
					if (textName.getText().equals("Job Name")) {
						System.out.println("Try again");
					} else if (textProvince.getText().equals("Province Code")
							&& textIncome.getText().equals("Annual Income") || textIncome.getText().equals("")
							|| textProvince.getText().equals("")) {
						output.println(search.searchCity(textName.getText()));
					} else if (textIncome.getText().equals("Annual Income")) {
						output.println(search.searchProvinceCity(textName.getText(), textProvince.getText()));
					} else {
						output.println(search.searchIncomeCity(textName.getText(), textProvince.getText(),
								textIncome.getText()));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		output.close();
		panel.add(label, BorderLayout.NORTH);
		panel.add(textName, BorderLayout.CENTER);
		panel.add(textProvince, BorderLayout.CENTER);
		panel.add(textIncome, BorderLayout.CENTER);
		panel.add(textArea, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// close button
		frame.setSize(700, 700);
		frame.pack();
		panel.setVisible(true);
		frame.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		InputScreen screen = new InputScreen();

	}
}