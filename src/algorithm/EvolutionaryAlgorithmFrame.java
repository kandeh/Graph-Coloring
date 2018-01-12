package algorithm;

import graph.Graph;
import graphCreator.GraphCreator;
import graphCreator.GraphDrawerPanel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Alireza
 */

public class EvolutionaryAlgorithmFrame extends JFrame {

    DefaultListModel<String> populationDataModel = new DefaultListModel<>();
    JList<String> populationList = new JList(populationDataModel);
    private Graph graph = null;
    
    public EvolutionaryAlgorithmFrame(Graph graph) {
        this.graph = graph;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        JPanel pupulationPanel = new JPanel();
        
        populationList.setPreferredSize(new Dimension(200, 600));
        pupulationPanel.add(populationList);
        
        JScrollPane pupulationScrollPane = new JScrollPane();
        pupulationScrollPane.setViewportView(populationList);

        JPanel buttonsPanel = new JPanel();
        JButton editGraphBtn = new JButton("Edit Graph");
        JButton initialPopulation = new JButton("Generate Initial Population");
        
        editGraphBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new GraphCreator(graph);
                EvolutionaryAlgorithmFrame.this.setVisible(false);
                EvolutionaryAlgorithmFrame.this.dispose();
            }
        });
        
        initialPopulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populationDataModel.clear();
                for(int i = 0; i < 50; i++) {
                    populationDataModel.addElement("##################### " + i);
                }
            }
        });
        
        buttonsPanel.add(editGraphBtn);
        buttonsPanel.add(initialPopulation);
        
        pupulationPanel.setPreferredSize(populationList.getPreferredSize());
        panel.add(pupulationScrollPane);
        
        buttonsPanel.setPreferredSize(new Dimension(200, 600));
        panel.add(buttonsPanel);
        
        JPanel gdp = new GraphDrawerPanel(graph, false);
        gdp.setPreferredSize(new Dimension(600, 600));
        panel.add(gdp);

        
        this.add(panel);
        this.pack();
        this.setResizable(false);
        //this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}
