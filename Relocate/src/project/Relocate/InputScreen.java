package project.Relocate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InputScreen {
	JFrame frame = new JFrame("Relocate");
	JPanel panel = new JPanel();
	JLabel label = new JLabel("ReLocate", SwingConstants.CENTER);
	JTextField textName = new JTextField("");
	JTextField textProvince = new JTextField("");
	JTextField textIncome = new JTextField("");
	final JTextArea textArea = new JTextArea(5, 20);
	JButton button = new JButton("Search");

	public InputScreen() {
		textArea.setEditable(false);
		button.setPreferredSize(new Dimension(200, 50));
		panel.setLayout(new BorderLayout());
		label.setFont(new Font("Consolas", Font.HANGING_BASELINE, 30));
		panel.setPreferredSize(new Dimension(400, 300));
		textName.setBounds(70, 70, 200, 30);
		textProvince.setBounds(70, 100, 200, 30);
		textIncome.setBounds(70, 130, 200, 30);
		textArea.setBounds(1, 1, 2, 3);
		// button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jn = textName.getText();
				String pc = textProvince.getText();
				String ai = textIncome.getText();
				// if (e.getSource() == button) {
				// // button.getModel().isSelected() = true;
				// textName.setText(jn);
				// textProvince.setText(pc);
				// textIncome.setText(ai);
				// }
				//
				// System.out.println("Action:");
				// System.out.println(button.getModel().isSelected());
				// System.out.println(textName.getText());
				// System.out.println(textProvince.getText());
				// System.out.println(textIncome.getText());
				try {
					Searcher search = new Searcher();
					if (textName.getText().equals("")) {
						System.out.println("Try again");
					} else if (textProvince.getText().equals("") && textIncome.getText().equals("")) {
						System.out.println(search.searchCity(textName.getText()));
					} else if (textIncome.getText().equals("")) {
						System.out.println(search.searchProvinceCity(textName.getText(), textProvince.getText()));
					}
					// System.out.println(search.searchCity("Legislators"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// button.setM;;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
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

	public String getJobName() {
		return this.textName.getText();
	}

	public String getProvinceCode() {
		return this.textProvince.getText();
	}

	public String getAnnualIncome() {
		return this.textIncome.getText();
	}

	public boolean buttonPressed() {
		return button.getModel().isSelected();
	}

	// public void setJobName(String jn) {
	// this.textName.setText(jn);;
	// }
	//
	// public void setProvinceCode(String pc) {
	// this.textProvince.setText(pc);
	// }
	//
	// public void setAnnualIncome(String ai) {
	// this.textIncome.setText(ai);
	// }
	public static void main(String[] args) {
		InputScreen screen = new InputScreen();
	}
}