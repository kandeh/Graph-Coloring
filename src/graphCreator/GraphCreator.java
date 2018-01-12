package graphCreator;

import graph.Graph;
import java.awt.Graphics;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Alireza
 */
public class GraphCreator extends JFrame {

    public GraphCreator() throws HeadlessException {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(new GraphDrawerPanel(new Graph()));
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
    }

}
