package algorithm;

import graph.Graph;
import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class EvolutionaryAlgorithm {

    private int colors = 0;
    private double Pc = 0.0;
    private double Pm = 0.0;
    
    private Graph graph = null;
    private int populationSize = 0;

    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();
    private ArrayList<Chromosome> intermediatePopulation = new ArrayList<Chromosome>();

    private int generation = 0;
    
    public EvolutionaryAlgorithm(Graph graph, double Pc, double Pm, int colors) {
        this.graph = graph;
        this.Pc = Pc;
        this.Pm = Pm;
        this.colors = colors;
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
        this.populationSize = Math.max(10, (int) (graph.getNodes().size()  / 2.75)); // toDo
        this.population.clear();
        new InitialPopulationGenerator(this.graph, this.population, this.populationSize, this.colors);
        best = getBest(population).clone();
        generation = 1;
    }
    
    public void setPc(double Pc) {
        this.Pc = Pc;
    }
    
    public double getPc() {
        return this.Pc;
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
        Mutation mutation = new Mutation();
        for(Chromosome ch :intermediatePopulation) {
            if(p(Pm)) {
                mutation.doMutation(this.graph, ch, colors);
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
    
    public void nextGeneration() {
        synchronized(population) {
            doSelection();
            doCrossover();
            doMutation();
            doReplacement();

            Chromosome b = getBest(population);
            if(b.getFitness() > best.getFitness()) {
                best = b.clone();
            } else {
                population.remove(0);
                population.add(best.clone());
            }

            generation++;
        }
    }
    
    public Chromosome best = null;

    @Override
    public String toString() {
        double bestFitness = 0;
        if(best != null) {
            bestFitness = best.getFitness();
            if(Double.isNaN(best.getFitness())) {
                bestFitness = 0;
            }
        }
        return "Generation = " + generation + " | " + "Best Fitness = " + bestFitness;
    }
    
    
    
}
