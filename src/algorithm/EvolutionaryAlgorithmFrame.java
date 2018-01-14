package algorithm;

import graph.Graph;
import graphCreator.GraphCreator;
import graphCreator.GraphDrawerPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Alireza
 */

public class EvolutionaryAlgorithmFrame extends JFrame {

    private DefaultListModel<Chromosome> populationDataModel = new DefaultListModel<>();
    private JList<String> populationList = new JList(populationDataModel);
    private Graph graph = null;
    
    private Thread thread = null;
    
    private JPanel gdp = null;
    private EvolutionaryAlgorithm algorithm = null;
    
    private final int maxColor = 6;
    
    private JButton editGraphBtn = new JButton("Edit Graph");
    private JButton initialPopulationBtn = new JButton("Generate Initial Population");
    private JButton nextGenerationBtn = new JButton("Next Generation");
    private JButton runBtn = new JButton("Run");
    private JButton stopBtn = new JButton("Stop");
        
    public void setPopulationDataModel() {

        synchronized(algorithm.getCurrentPopulation()) {
            Util.sort(algorithm.getCurrentPopulation());
            populationDataModel.clear();
            for(Chromosome ch : algorithm.getCurrentPopulation()) {
                populationDataModel.addElement(ch);
            }
            populationList.setSelectedIndex(0);
            Util.setColors(graph, algorithm.best);
            repaint();
        }
    }
    
    
    private void nextGeneration() {
        algorithm.nextGeneration();
        setPopulationDataModel();
    }

    class Run implements Runnable {
        @Override
        public void run() {
            try {
                while(true) {
                    algorithm.nextGeneration();
                    Util.setColors(graph, algorithm.best);
                    repaint();
                    if(Thread.currentThread().isInterrupted()) {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            }
        });

        JPanel buttonsPanel = new JPanel();

        
        
        editGraphBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(EvolutionaryAlgorithmFrame.this.thread != null && EvolutionaryAlgorithmFrame.this.thread.isAlive()) {
                    EvolutionaryAlgorithmFrame.this.thread.interrupt();
                }
                new GraphCreator(graph);
                EvolutionaryAlgorithmFrame.this.setVisible(false);
                EvolutionaryAlgorithmFrame.this.dispose();
            }
        });
        
        initialPopulationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EvolutionaryAlgorithmFrame.this.runBtn.setEnabled(true);
                algorithm = new EvolutionaryAlgorithm(graph, maxColor);
                algorithm.initiate();
                setPopulationDataModel();

            }
        });
        
        nextGenerationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextGeneration();
                setPopulationDataModel();
            }
        });  
        
        EvolutionaryAlgorithmFrame.this.nextGenerationBtn.setEnabled(false);
        EvolutionaryAlgorithmFrame.this.runBtn.setEnabled(false);
        EvolutionaryAlgorithmFrame.this.stopBtn.setEnabled(false);
        
        runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                EvolutionaryAlgorithmFrame.this.populationList.setEnabled(false);
                EvolutionaryAlgorithmFrame.this.populationDataModel.clear();
                
                EvolutionaryAlgorithmFrame.this.editGraphBtn.setEnabled(false);
                EvolutionaryAlgorithmFrame.this.initialPopulationBtn.setEnabled(false);
                EvolutionaryAlgorithmFrame.this.nextGenerationBtn.setEnabled(false);
                EvolutionaryAlgorithmFrame.this.runBtn.setEnabled(false);
                EvolutionaryAlgorithmFrame.this.stopBtn.setEnabled(true);
                
                EvolutionaryAlgorithmFrame.this.thread = new Thread(new Run());
                EvolutionaryAlgorithmFrame.this.thread.start();
            }
        });  
        
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EvolutionaryAlgorithmFrame.this.editGraphBtn.setEnabled(true);
                EvolutionaryAlgorithmFrame.this.populationList.setEnabled(true);
                EvolutionaryAlgorithmFrame.this.initialPopulationBtn.setEnabled(true);
                EvolutionaryAlgorithmFrame.this.nextGenerationBtn.setEnabled(true);
                EvolutionaryAlgorithmFrame.this.runBtn.setEnabled(true);
                EvolutionaryAlgorithmFrame.this.stopBtn.setEnabled(false);
                if(EvolutionaryAlgorithmFrame.this.thread != null && EvolutionaryAlgorithmFrame.this.thread.isAlive()) {
                    EvolutionaryAlgorithmFrame.this.thread.interrupt();
                }
                setPopulationDataModel();
            }
        });  
        
        
        buttonsPanel.add(editGraphBtn);
        buttonsPanel.add(initialPopulationBtn);
        buttonsPanel.add(nextGenerationBtn);
        buttonsPanel.add(runBtn);
        buttonsPanel.add(stopBtn);
        
        panel.add(pupulationScrollPane);
        
        buttonsPanel.setPreferredSize(new Dimension(200, 600));
        panel.add(buttonsPanel);
        
        gdp = new GraphDrawerPanel(graph, false, true);
        gdp.setPreferredSize(new Dimension(600, 600));
        panel.add(gdp);
        
        this.add(panel);
        this.pack();
        this.setResizable(false);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        this.setVisible(true);
    }
    
}
