package graphCreator;

import algorithm.EvolutionaryAlgorithmFrame;
import graph.Graph;
import java.awt.Dimension;
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

    private GraphDrawerPanel graphPanel = null;
    
    public GraphCreator() {
        this(new Graph());
    }
    
    public GraphCreator(Graph graph) {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Graph Creator");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        graphPanel = new GraphDrawerPanel(graph, true, false);
        graphPanel.setPreferredSize(new Dimension(600, 600));
        panel.add(graphPanel);
        
        JPanel buttonPanel = new JPanel();
        JButton btn = new JButton("Algorithm");
        
        JButton loadGraphBtn = new JButton("Load Graph");
        JButton saveGraphBtn = new JButton("Save Graph");
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new EvolutionaryAlgorithmFrame(graphPanel.getGraph());
                GraphCreator.this.setVisible(false);
                GraphCreator.this.dispose();
            }
        });
        
        
        loadGraphBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.setGraph(Util.loadGraph(graphCreator.GraphCreator.this));
            }
        });
        
        saveGraphBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Util.saveGraph(graphCreator.GraphCreator.this, graphPanel.getGraph());
            }
        });
        
        buttonPanel.add(loadGraphBtn);
        buttonPanel.add(saveGraphBtn);
        
        buttonPanel.add(btn);
        
        panel.add(buttonPanel);
        
        this.add(panel);
        
        this.pack();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        this.setVisible(true);
    }


}
