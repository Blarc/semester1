package treeDrawing;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Controls {
	private Frame frame;
	private JButton add;
	private JTextField tf;
	
	public Controls(Frame frame) {
		this.frame = frame;
		
		add = new JButton();
		add.setText("addNode");
		add.setName("addNode");
		//WHAT IS THIS
		add.setFocusable(false);
		add.addActionListener(frame);
		add.setVisible(true);
		
		tf = new JTextField(10);
		tf.setName("tf");
		tf.setFocusable(true);
		tf.setVisible(true);
		
	}
	
	public void addAll() {
		frame.add(add);
		frame.add(tf);
	}
	
	public void position() {
		
		// Set button bounds
		add.setBounds(frame.getWidth()/2 - 100, frame.getHeight()-90, 100, 50);
		
		// Set textfield bounds
		tf.setBounds(frame.getWidth()/2 + 10, frame.getHeight()-90, 100, 50);
	}
	
	public String getTfText() {
		return tf.getText();
	}
}
