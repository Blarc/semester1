import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



@SuppressWarnings("serial")
public class Day1 extends JFrame {
	public static final int VERTEX_SIZE = 50;
	public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 600;
    public static final Color LINE_COLOR = Color.BLACK;
    public static final Color CANVAS_BACKGROUND = Color.CYAN;
	
    private DrawCanvas canvas;
    
    public Day1() {
    	Tree myTree = new Tree();
    	
    	JPanel panel = new JPanel(new FlowLayout());
    	JTextField tf = new JTextField(10);
    	JButton btn = new JButton("BUTTON");
    	btn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			try {
    				int number = Integer.parseInt(tf.getText());
    				myTree.insert(number);
    				canvas.setRoot(myTree.root);
    				repaint();
    				System.out.println(number);
    			}
    			catch(NumberFormatException nfe)
    			{
    				System.out.println("Not a number!");
    			}
    		}
    	});
    	
    	
    	
    	
    	panel.add(btn);
    	panel.add(tf);
    	
    	canvas = new DrawCanvas();
    	canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    	
    	Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(panel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
        setTitle("Tree");
        pack();           // pack all the components in the JFrame
        setVisible(true); // show it
        requestFocus();   // set the focus to JFrame to receive KeyEvent
    	
    }
    
    
    
    class DrawCanvas extends JPanel {
    	private Node root = null;
    	private Font monoFont = new Font("Monospaced", Font.BOLD, 20);
    	
    	public void setRoot(Node node) {
    		this.root = node;
    	}
    	
    	public void drawTree(Graphics g, int x, int y, Node node) {
    		if (node != null) {
    			drawVertex(g, x, y, (int)node.val);
    			drawTree(g, x - 150, y+75, node.left);
    			drawTree(g, x + 150, y+75, node.right);
    		}
    	}
    	
    	public void drawVertex(Graphics g, int x, int y, int num) {
    		g.setColor(Color.WHITE);
    		g.fillOval(x, y, VERTEX_SIZE, VERTEX_SIZE);
    		g.setFont(monoFont);
    		FontMetrics fm = g.getFontMetrics();
    		int w = fm.stringWidth(Integer.toString(num));
    		int h = fm.getAscent();
    		
    		g.setColor(Color.BLACK);
    		g.drawString(Integer.toString(num), x + VERTEX_SIZE/2 - w/2, y + VERTEX_SIZE/2 + h/2);
    	}
    	
	    @Override   
	    public void paintComponent(Graphics g) {
		    super.paintComponent(g);
	        setBackground(CANVAS_BACKGROUND);
	        drawTree(g, CANVAS_WIDTH/2 - VERTEX_SIZE/2, 30, root);
	    }
	}
    
    
    
    
    public static void main(String[] args) {
        // Run GUI codes on the Event-Dispatcher Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
              new Day1(); // Let the constructor do the job
           }
        });
     }
}
