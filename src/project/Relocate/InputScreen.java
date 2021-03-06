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
	FileWriter output;

	public InputScreen() throws IOException {
		output = new FileWriter("data/output.txt");
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
						//if the search is empty
						System.out.println("Job not found.");
					} else if (textProvince.getText().equals("Province Code")
							&& textIncome.getText().equals("Annual Income") || textIncome.getText().equals("")
							|| textProvince.getText().equals("")) {
						//if only the job name is specified
						System.out.println(search.searchCity(textName.getText()));
						output.write(search.searchCity(textName.getText()));
					}else if (textProvince.getText().equals("Province Code")
									|| textProvince.getText().equals("")) {
						//if only the province is not entered (cannot search a job and an income together without a province)
							System.out.println("Invalid Province.");
					} else if (textIncome.getText().equals("Annual Income")) {
						//if a job and province are searched together
						System.out.println(search.searchProvinceCity(textName.getText(), textProvince.getText()));
						output.write(search.searchProvinceCity(textName.getText(), textProvince.getText()));
					} else {
						//if all three are specified by the user
						System.out.println(search.searchIncomeCity(textName.getText(), textProvince.getText(),
								textIncome.getText()));
						output.write(search.searchIncomeCity(textName.getText(), textProvince.getText(),
								textIncome.getText()));
					}
					output.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		
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