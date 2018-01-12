package algorithm;

import graph.Graph;
import graph.Node;
import graphCreator.Util;

/**
 *
 * @author Alireza
 */

public class Chromosome {

    private int genes[] = null;
    private int maxColor = 4;
    private Graph graph = null;
    private boolean fitnessCalced = false;
    private double fitness = 0;
    
    public Chromosome(Graph graph, int nodes, int maxColor) {
        this.graph = graph;
        this.maxColor = maxColor;
        genes = new int[nodes];
        for(int i = 0; i < nodes; i++) {
            genes[i] = (int) (Math.random() * maxColor);
        }
        unsetFitness();
    }
    
    public void unsetFitness() {
        fitnessCalced = false;
        fitness = 1;
    }

    private double _getFitness() {
        double res = 0;
        for(Node node : graph.getNodes()) {
            for(Node toNode : node.edgesTo) {
                if(genes[node.getIndex()] == genes[toNode.getIndex()]) {
                    res++;
                }
            }
        }
        return res / 2;
    }
    
    public double getFitness() {
        if(fitnessCalced) {
            return fitness;
        }
        fitness = _getFitness();
        fitnessCalced = true;
        return fitness;
    }
    
    public int[] getGenes() {
        return genes;
    }
    
    @Override
    public String toString() {
        String res = "fitness = " + getFitness() + " | ";
        for(int i = 0; i < genes.length; i++) {
            res += genes[i] + (i < genes.length - 1 ? " , " : "");
        }
        return res;
    }
}
