package algorithm;

import graph.Graph;
import graph.Node;

/**
 *
 * @author Alireza
 */

public class BetterMutation {
    
    public void doMutation(Graph graph, Chromosome ch, int maxColor) {
        for(Node node : graph.getNodes()) {
            if(needMutation(node, ch) || Math.random() <= 0.05) {
                ch.genes[node.getIndex()] = getBestColor(node, ch, maxColor);
                ch.unsetFitness();
                ch.unsetNormalizedFitness();
            }
        }
    }
    
    private double arr[] = null;
    
    private int getBestColor(Node node, Chromosome ch, int maxColor) {
        
        if(arr == null || arr.length < maxColor) {
            arr = new double[maxColor];
        }
        
        for(int i = 0; i < maxColor; i++) {
            arr[i] = 1;
        }

        for(Node toNode : node.edgesTo) {
            arr[ch.genes[toNode.getIndex()]]++;
        }
        
        for(int i = 0; i < maxColor; i++) {
            arr[i] *= 0.75 + (Math.random() / 2);
        }
        
        arr[ch.genes[node.getIndex()]] *= 1000;
        
        //int result = (int) (Math.random() * maxColor);        
        int result = result = 0;
        
        for(int i = 0; i < maxColor; i++) {
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
