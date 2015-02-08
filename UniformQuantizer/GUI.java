package UniformQuantizer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField comp_in;
	private JTextField comp_out;
	private JTextField decomp_in;
	private JTextField decomp_out;
	private JTextField QuantTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
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
		
		JLabel lblCompInput = new JLabel("comp input");
		lblCompInput.setBounds(10, 11, 72, 14);
		frame.getContentPane().add(lblCompInput);
		
		JLabel lblCompOutput = new JLabel("comp output");
		lblCompOutput.setBounds(10, 77, 72, 14);
		frame.getContentPane().add(lblCompOutput);
		
		JLabel lblDecompInput = new JLabel("decomp input");
		lblDecompInput.setBounds(10, 152, 72, 14);
		frame.getContentPane().add(lblDecompInput);
		
		JLabel lblDecompOutput = new JLabel("decomp output");
		lblDecompOutput.setBounds(10, 205, 72, 14);
		frame.getContentPane().add(lblDecompOutput);
		
		comp_in = new JTextField();
		comp_in.setBounds(10, 41, 213, 25);
		frame.getContentPane().add(comp_in);
		comp_in.setColumns(10);
		
		comp_out = new JTextField();
		comp_out.setColumns(10);
		comp_out.setBounds(10, 102, 213, 25);
		frame.getContentPane().add(comp_out);
		
		decomp_in = new JTextField();
		decomp_in.setColumns(10);
		decomp_in.setBounds(10, 172, 213, 25);
		frame.getContentPane().add(decomp_in);
		
		decomp_out = new JTextField();
		decomp_out.setColumns(10);
		decomp_out.setBounds(10, 227, 213, 25);
		frame.getContentPane().add(decomp_out);
		
		QuantTF = new JTextField("3");
		QuantTF.setColumns(10);
		QuantTF.setBounds(299, 41, 107, 25);
		frame.getContentPane().add(QuantTF);
		
		JLabel lblQuantizer = new JLabel("Quantizer");
		lblQuantizer.setBounds(299, 11, 83, 14);
		frame.getContentPane().add(lblQuantizer);
		
		JButton compressB = new JButton("compress");
		compressB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageRW.compress(comp_in.getText(), comp_out.getText(), Integer.parseInt(QuantTF.getText()));
			}
		});
		compressB.setBounds(281, 100, 125, 29);
		frame.getContentPane().add(compressB);
		
		JButton decompressB = new JButton("Decompress");
		decompressB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageRW.deCompress(decomp_in.getText(), decomp_out.getText());
			}
		});
		decompressB.setBounds(281, 172, 125, 29);
		frame.getContentPane().add(decompressB);
	}
}
