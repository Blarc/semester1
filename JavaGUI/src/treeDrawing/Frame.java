package treeDrawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import treeDrawing.BSTTree;

@SuppressWarnings("serial")
public class Frame extends JPanel implements ActionListener {
	public static final int WIDTH = 700;
	public static final int HEIGTH = 600;
	
	int vertexSize;
	Font monoFont;
	JFrame window;
	Controls controls;
	BSTTree tree;
	
	public static void main(String[] args) {
		new Frame();
	}
	
	public Frame() {
		vertexSize = 50;
		monoFont = new Font("Monospaced", Font.BOLD, vertexSize/3);
		 
		setLayout(null);
		setFocusable(true);
		
		// Set up controls
		controls = new Controls(this);
		 
		// Set up tree
		tree = new BSTTree();
		
		// Set up window
		window = new JFrame();
		window.setContentPane(this);
		window.setTitle("Tree");
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGTH));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// Add controls
		controls.addAll();
		
		this.revalidate();
		this.repaint();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		g.clearRect(0, 0, WIDTH, HEIGTH);
		drawTree(g);
		
		// Position controls
		controls.position();
		
	}
	
	public void drawTree(Graphics g) {	
		drawTree(g, WIDTH/2 - vertexSize/2, 50, tree.root, WIDTH/2 - vertexSize/2, 50, 1);
	}
	
	public void drawTree(Graphics g, int x, int y, Node node, int prevX, int prevY, int h) {
		if (node == null) {
			return;
		}
		
		drawTree(g, x - ((WIDTH/2)/h)/2+(6-h)*6, y + 50, node.left, x, y, h+1);
		drawTree(g, x + ((WIDTH/2)/h)/2-(6-h)*6, y + 50, node.right, x, y, h+1);
		drawVertex(g, x, y, node, prevX, prevY, h);
	}
	
	public void drawVertex(Graphics g, int x, int y, Node node, int prevX, int prevY, int h) {
		
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.draw(new Line2D.Float(x+vertexSize/2, y+vertexSize/2, prevX+vertexSize/2, prevY+vertexSize/2));
		
		g.setColor(Color.darkGray);
		g.fillOval(x, y, vertexSize, vertexSize);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, vertexSize, vertexSize);
		
		g.setFont(monoFont);
		FontMetrics fm = g.getFontMetrics();
		int w = fm.stringWidth(Integer.toString((int)node.val));
		int hi = fm.getAscent();
		
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString((int)node.val), x + vertexSize/2 - w/2, y + vertexSize/2 + hi/2);

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() != null) {
			if(e.getActionCommand().equals("addNode")) {

				try {		
					int num = Integer.parseInt(controls.getTfText());
					tree.insert(num);
					repaint();
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("Not a number!");
				}
			}
		}
	}
	
}
