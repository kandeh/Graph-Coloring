package algorithm;

import graph.Graph;
import graph.Node;
import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class EvolutionaryAlgorithm {

    private double Pc = 0.5;
    private double Pm = 0.5;
    private Graph graph = null;
    private int populationSize = 0;
    private int maxColor = 0;
    
    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();
    private ArrayList<Chromosome> intermediatePopulation = new ArrayList<Chromosome>();

    public EvolutionaryAlgorithm(Graph graph, int maxColor) {
        this.graph = graph;
        this.maxColor = maxColor;
    }
    

    
    public void initiate() {
        this.populationSize = 10; // toDo
        this.intermediatePopulation.clear();
        this.population.clear();
        new InitialPopulationGenerator(this.graph, this.population, this.populationSize, this.maxColor);
        new FintnessSetter(this.graph, this.population);
        new FitnessNormalizer(this.population);
    }
    
    public void setPc(double Pc) {
        this.Pc = Pc;
    }
    
    public double getPc() {
        return Pc;
    }
    
    public void setPm(double Pm) {
        this.Pm = Pm;
    }
    
    public double getPm() {
        return this.Pm;
    }
    
    private void doSelection() {
        
    }
    
    private void doMutation() {
        
    }
    
    private void doCrossover() {
        
    }
    
    public ArrayList<Chromosome> getCurrentPopulation() {
        return population;
    }
    
    public void nextGeneration() {
        
    }
    
    public void run() {
        
    }
    
    public void stop() {
        
    }
    
}
