package lz77;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;


public class Welcome {

	private JFrame frame;
	JTextArea datatextArea;
	JTextArea tagstextArea_1;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLUE);
		frame.setBounds(100, 100, 489, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDecompress = new JButton("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = lz7.deCompress(tagstextArea_1.getText());
				datatextArea.setText(result);
				
			}
		});
		btnDecompress.setBounds(236, 336, 164, 23);
		frame.getContentPane().add(btnDecompress);
		
	    datatextArea = new JTextArea();
	    datatextArea.setLineWrap(true);
		datatextArea.setBounds(10, 42, 445, 107);
		frame.getContentPane().add(datatextArea);
		
		tagstextArea_1 = new JTextArea();
		tagstextArea_1.setLineWrap(true);
		tagstextArea_1.setBounds(10, 218, 445, 107);
		frame.getContentPane().add(tagstextArea_1);
		
		JLabel lblNewLabel = new JLabel("Data");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 8, 117, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Compressed Data");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 194, 170, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JButton btnNewButton = new JButton("Compress");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = lz7.Compress(datatextArea.getText());
				tagstextArea_1.setText(result);
			}
		});
		btnNewButton.setBounds(236, 160, 164, 23);
		frame.getContentPane().add(btnNewButton);

	}
}

