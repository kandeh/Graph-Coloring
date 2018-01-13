package algorithm;

import graph.Graph;
import graph.Node;
import graphCreator.Util;
import java.util.ArrayList;

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
            genes[i] = -1;
        }
        
        /*for(int i = 0; i < nodes; i++) {
            genes[i] = (int) (Math.random() * maxColor);
        }*/

        for(Node node : graph.getNodes()) {
            setNodeColor(node);
        }
        
        unsetFitness();
    }
    
    
    
    int arr[] = null;
    public void setNodeColor(Node node) {
        
        if(genes[node.getIndex()] != -1) {
            return;
        }
        
        boolean limited = false;
        
        for(Node toNode : node.edgesTo) {
            if(genes[toNode.getIndex()] != -1) {
                limited = true;
                break;
            }
        }
        
        if(limited == false) {
            genes[node.getIndex()] = 0;
        } else {
            
            if(arr == null || arr.length != maxColor) {
                arr = new int[maxColor];
            }
            for(int i = 0; i < maxColor; i++) {
                arr[i] = 0;
            }
            
            for(Node toNode : node.edgesTo) {
                for(int i = 0; i < maxColor; i++) {
                    if(i != genes[toNode.getIndex()]) {
                        for(int k = 0; k < 1; k++) {
                            arr[i]++;
                        }
                    }
                }
            }
            
            int maxIndex = 0;
            for(int i = 0; i < maxColor; i++) {
                if(arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            if(Math.random() <= 0.5) {
                genes[node.getIndex()] = maxIndex;
            } else {
                if(Math.random() <= 0.5) {
                    arr[maxIndex] = 0;
                    maxIndex = 0;
                    for(int i = 0; i < maxColor; i++) {
                        if(arr[i] > arr[maxIndex]) {
                            maxIndex = i;
                            genes[node.getIndex()] = maxIndex;
                        }
                    }
                } else {
                    genes[node.getIndex()] = (int) (Math.random() * maxColor);
                }
            }
        }
        
        for(Node toNode : node.edgesTo) {
            setNodeColor(toNode);
        }
        
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
