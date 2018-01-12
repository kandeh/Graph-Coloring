package graphCreator;

import algorithm.EvolutionaryAlgorithmFrame;
import graph.Graph;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alireza
 */
public class GraphCreator extends JFrame {

    
    private Graph graph;

    public GraphCreator() {
        this(new Graph());
    }
    
    public GraphCreator(Graph graph) {
        this.graph = graph;
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Graph Creator");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        GraphDrawerPanel dp = new GraphDrawerPanel(graph, true, false);
        dp.setPreferredSize(new Dimension(600, 600));
        panel.add(dp);
        
        JPanel buttonPanel = new JPanel();
        JButton btn = new JButton("Algorithm");
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new EvolutionaryAlgorithmFrame(graph);
                GraphCreator.this.setVisible(false);
                GraphCreator.this.dispose();
            }
        });
        
        buttonPanel.add(btn);
        panel.add(buttonPanel);
        
        this.add(panel);
        
        this.pack();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        this.setVisible(true);
    }


}
