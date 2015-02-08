package lzw;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;


public class wlcome1 {

	private JFrame frame;
	private JTextField compresstextField;
	private JTextField decompresstextField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wlcome1 window = new wlcome1();
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
	public wlcome1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		compresstextField = new JTextField();
		compresstextField.setBounds(70, 11, 339, 73);
		frame.getContentPane().add(compresstextField);
		compresstextField.setColumns(10);
		
		decompresstextField_1 = new JTextField();
		decompresstextField_1.setBounds(85, 144, 339, 73);
		frame.getContentPane().add(decompresstextField_1);
		decompresstextField_1.setColumns(10);
		
		JButton btnCom = new JButton("Compress Text");
		btnCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = LZW.compression(compresstextField.getText());
				decompresstextField_1.setText(result);
			}
		});
		btnCom.setBounds(68, 99, 165, 23);
		frame.getContentPane().add(btnCom);
		
		JButton btnDecomp = new JButton("DeCompress Text");
		btnDecomp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = LZW.Decompression(decompresstextField_1.getText());
				compresstextField.setText(result);
			}
		});
		btnDecomp.setBounds(70, 228, 163, 23);
		frame.getContentPane().add(btnDecomp);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 40, 46, 14);
		frame.getContentPane().add(lblData);
		
		JLabel lblEncoding = new JLabel("EnCoding");
		lblEncoding.setBounds(10, 173, 65, 14);
		frame.getContentPane().add(lblEncoding);
		
		JButton btnCompressFile = new JButton("Compress File");
		btnCompressFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String source = compresstextField.getText();
				String dest = decompresstextField_1.getText();
				
				File sourceFile = new File(source);
				File destFile = new File(source);
				
				if(!sourceFile.exists())
				{
					JOptionPane.showMessageDialog(null, "Source file not found");
					return;
				}
				
				if(!destFile.exists())
				{
					JOptionPane.showMessageDialog(null, "Destination file not found");
					return;
				}
				
				try {
					StringBuilder inFile = LZW.readFromFile(sourceFile);
					String compResult = LZW.compression(inFile.toString());
					try {
						LZW.printOnFile(destFile, compResult);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnCompressFile.setBounds(259, 99, 150, 23);
		frame.getContentPane().add(btnCompressFile);
		
		JButton btnDecompressFile = new JButton("DeCompress File");
		btnDecompressFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String source = decompresstextField_1.getText();
				String dest = compresstextField.getText();
				
				File sourceFile = new File(source);
				File destFile = new File(source);
				
				if(!sourceFile.exists())
				{
					JOptionPane.showMessageDialog(null, "Source file not found");
					return;
				}
				
				if(!destFile.exists())
				{
					JOptionPane.showMessageDialog(null, "Destination file not found");
					return;
				}
				try {
					StringBuilder inFile = LZW.readFromFile(sourceFile);
					String decompResult = LZW.Decompression(inFile.toString());
					try {
						LZW.printOnFile(destFile, decompResult);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
			}
		});
		btnDecompressFile.setBounds(259, 228, 150, 23);
		frame.getContentPane().add(btnDecompressFile);
	}
}
