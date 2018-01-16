package algorithm;

import graph.Graph;
import graph.Node;

/**
 *
 * @author Alireza
 */

public class Chromosome {

    protected int genes[] = null;
    
    private boolean isFitnessCalculated = false;
    private double fitness = 0;
    private Graph graph = null;
    private int colors = 0;
    
    public Chromosome(Graph graph, int colors) {
        genes = new int[graph.getNodes().size()];
        this.graph = graph;
        this.colors = colors;
        resetFitness();
    }
    
    public final void resetFitness() {
        this.isFitnessCalculated = false;
        this.fitness = 0;
    }
    
    private double _getFitness() {
        int conflicts = 0;
        double A = 0;
        double B = 0;
        int nodes = graph.getNodes().size();
        for(Node node : graph.getNodes()) {
            for(Node toNode : node.edgesTo) {
                if(this.genes[node.getIndex()] == this.genes[toNode.getIndex()]) {
                    conflicts++;
                }
            }
        }
        conflicts /= 2;
        int edges = graph.getEdges();
        if(edges != 0) {
            A = (double) (edges - conflicts) / edges;
        } else {
            A = 1;
        }
        B = ((double) (nodes - Util.getColorsFrequencyDifference(this, colors)) / nodes);
        
        A = Math.pow(A, 5);
        B = Math.pow(B, 5);
        
        return 0.95 * A + 0.05 * B;
    }
    
    public double getFitness() {
        if(this.isFitnessCalculated) {
            return this.fitness;
        }
        this.fitness = _getFitness();
        this.isFitnessCalculated = true;
        return this.fitness;
    }
    
    public int[] getGenes() {
        return genes;
    }
    
    @Override
    public String toString() {
        String res = "";
        res += "fitness = " + getFitness() + " | ";
        for(int i = 0; i < genes.length; i++) {
            res += genes[i] + (i < genes.length - 1 ? " , " : "");
        }
        return res;
    }
    
    @Override
    protected Chromosome clone() {
        Chromosome result = new Chromosome(this.graph, this.colors);
        for(int i = 0; i < result.genes.length; i++) {
            result.genes[i] = this.genes[i];
        }
        return result;
    }
  
}
