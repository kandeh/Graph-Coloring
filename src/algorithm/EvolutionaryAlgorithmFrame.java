package algorithm;

import com.sun.javafx.collections.ListListenerHelper;
import graph.Graph;
import graph.Node;
import graphCreator.GraphCreator;
import graphCreator.GraphDrawerPanel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Alireza
 */

public class EvolutionaryAlgorithmFrame extends JFrame {

    DefaultListModel<Chromosome> populationDataModel = new DefaultListModel<>();
    JList<String> populationList = new JList(populationDataModel);
    private Graph graph = null;
    
    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();
    private ArrayList<Chromosome> intermediatePopulation = new ArrayList<Chromosome>();
    
    private final int maxColors = 5;
    
    public void setPopulationDataModel() {
        populationDataModel.clear();
        Util.sort(population);
        for(Chromosome ch : population) {
            populationDataModel.addElement(ch);
        }
    }
    
    public EvolutionaryAlgorithmFrame(Graph graph) {
        this.graph = graph;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        JScrollPane pupulationScrollPane = new JScrollPane(populationList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        populationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        populationList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting() == false) {
                    return;
                }
                Chromosome ch = populationDataModel.get(populationList.getSelectedIndex());
                Util.setColors(graph, ch);
                repaint();
                //System.out.println(ch);
            }
        });

        JPanel buttonsPanel = new JPanel();
        JButton editGraphBtn = new JButton("Edit Graph");
        JButton initialPopulationBtn = new JButton("Generate Initial Population");
        JButton nextGenerationBtn = new JButton("Next Generation");
        
        editGraphBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GraphCreator(graph);
                EvolutionaryAlgorithmFrame.this.setVisible(false);
                EvolutionaryAlgorithmFrame.this.dispose();
            }
        });
        
        initialPopulationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intermediatePopulation.clear();
                population.clear();
                for(int i = 0; i < 50; i++) {
                    population.add(new Chromosome(graph, graph.getNodes().size(), maxColors));
                }
                setPopulationDataModel();
                
                System.out.println("Avg Fitness = " + Util.getAvgFitness(population));
            }
        });
        
        nextGenerationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(EvolutionaryAlgorithmFrame.this, "wait for next update");
            }
        });        
        
        buttonsPanel.add(editGraphBtn);
        buttonsPanel.add(initialPopulationBtn);
        buttonsPanel.add(nextGenerationBtn);
        
        panel.add(pupulationScrollPane);
        
        buttonsPanel.setPreferredSize(new Dimension(200, 600));
        panel.add(buttonsPanel);
        
        JPanel gdp = new GraphDrawerPanel(graph, false, true);
        gdp.setPreferredSize(new Dimension(600, 600));
        panel.add(gdp);

        
        this.add(panel);
        this.pack();
        this.setResizable(false);
        //this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        this.setVisible(true);
    }
    
}
