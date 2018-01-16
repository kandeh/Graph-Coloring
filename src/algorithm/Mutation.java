package algorithm;

import graph.Graph;
import graph.Node;

/**
 *
 * @author Alireza
 */

public class Mutation {
    
    private double arr[] = null;
    private int colors = 0;
    
    public void doMutation(Graph graph, Chromosome ch, int colors) {
        arr = new double[colors];
        this.colors = colors;
        int p = (int) (Math.random() * ch.genes.length);
        ch.genes[p] = (int) (Math.random() * colors);
        
        int k = 0;
        for(Node node : graph.getNodes()) {
            if(needMutation(node, ch)) {
                ch.genes[node.getIndex()] = getBestColor(node, ch);
                ch.resetFitness();
                /*k++;
                if(k >= 3) {
                    return;
                }*/
            }
        }
    }
    
    private int getBestColor(Node node, Chromosome ch) {
        for(int i = 0; i < colors; i++) {
            arr[i] = 1;
        }

        for(Node toNode : node.edgesTo) {
            arr[ch.genes[toNode.getIndex()]]++;
        }
        
        arr[ch.genes[node.getIndex()]] *= 1.5;
        
        int result = 0;
        for(int i = 0; i < colors; i++) {
            if(arr[result] > arr[i]) {
                result = i;
            }
        }
        
        return result;
    }
    
    private boolean needMutation(Node node, Chromosome ch) {
        for(Node toNode : node.edgesTo) {
            if(ch.genes[node.getIndex()] == ch.genes[toNode.getIndex()]) {
                return true;
            }
        }
        return false;
    }
    
}
