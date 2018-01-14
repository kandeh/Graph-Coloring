package algorithm;

import graph.Graph;
import graph.Node;
import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class EvolutionaryAlgorithm {

    private double Pc = 0.1;
    private double Pm = 0.9;
    
    private Graph graph = null;
    private int populationSize = 0;
    private int maxColor = 0;
    
    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();
    private ArrayList<Chromosome> intermediatePopulation = new ArrayList<Chromosome>();

    public EvolutionaryAlgorithm(Graph graph, int maxColor) {
        this.graph = graph;
        this.maxColor = maxColor;
    }
    
    public Chromosome getBest(ArrayList<Chromosome> from) {
        Chromosome result = from.get(0);
        for(Chromosome ch : from) {
            if(ch.getFitness() > result.getFitness()) {
                result = ch;
            }
        }
        return result;
    }
    
    public void initiate() {
        this.populationSize = graph.getNodes().size()  / 2; // toDo
        this.population.clear();
        new InitialPopulationGenerator(this.graph, this.population, this.populationSize, this.maxColor);
        new FintnessSetter(this.graph, this.population, maxColor);
        new FitnessNormalizer(this.population);
        best = getBest(population).clone();
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
        intermediatePopulation.clear();
        FitnessProportionSelector fps = new FitnessProportionSelector(population, intermediatePopulation, populationSize);
    }
    
    private void doMutation() {
        //SimpleMutation mutation = new SimpleMutation();
        BetterMutation mutation = new BetterMutation();
        for(Chromosome ch :intermediatePopulation) {
            if(p(Pm)) {
                //mutation.doMutation(ch, maxColor);
                mutation.doMutation(this.graph, ch, maxColor);
            }
        }
    }
    
    private void doCrossover() {
        OnePointCrossover crossover = new OnePointCrossover();
        ArrayList<Chromosome> temp = new ArrayList<Chromosome>();
        Util.shuffle(intermediatePopulation);
        int size = intermediatePopulation.size();
        for(int i = 0; i < size; i += 2) {
            if(p(Pc) && (i + 1 < size)) {
                crossover.doCrossover(intermediatePopulation.get(i), intermediatePopulation.get(i + 1), temp);
            } else {
                temp.add(intermediatePopulation.get(i));
                if(i + 1 < size) {
                    temp.add(intermediatePopulation.get(i + 1));
                }
            }
        }
        intermediatePopulation.clear();
        intermediatePopulation.addAll(temp);
    }
    
    public ArrayList<Chromosome> getCurrentPopulation() {
        return population;
    }
    
    private boolean p(double p) {
        return Math.random() <= p;
    }
    
    private void doReplacement() {
        population.clear();
        population.addAll(intermediatePopulation);
        intermediatePopulation.clear();
    }
    
    private void calcFitnesses(ArrayList<Chromosome> chs) {
        FintnessSetter fs = new FintnessSetter(this.graph, chs, maxColor);
        FitnessNormalizer fn = new FitnessNormalizer(chs);   
    }
    
    public void nextGeneration() {
        
        synchronized(this.population) {
            doSelection();
            doCrossover();
            doMutation();
            calcFitnesses(intermediatePopulation);
            doReplacement();
            calcFitnesses(population);


            Chromosome b = getBest(population);
            if(b.getFitness() > best.getFitness()) {
                best = b.clone();
            }


            Util.sort(population);
            population.remove(population.size() - 1);
            population.add(best.clone());

            calcFitnesses(population);
        }
        

        
    }
    
    public Chromosome best = null;
    
    public void run() {
        
    }
    
    public void stop() {
        
    }
    
}
