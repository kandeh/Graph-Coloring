package algorithm;

import graph.Graph;
import graph.Node;
import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class InitialPopulationGenerator {

    private int maxColor = 0;
    private int arr[] = null;
    
    public InitialPopulationGenerator(Graph graph, ArrayList<Chromosome> popolationArray, int size, int maxColor) {
        this.maxColor = maxColor;
        arr = new int[maxColor];
        int chromosomeLength = graph.getNodes().size();
        while(size-- > 0) {
            Chromosome ch = new Chromosome(chromosomeLength);
            for(Node node : graph.getNodes()) {
                ch.genes[node.getIndex()] = -1;
            }
            for(Node node : graph.getNodes()) {
                setNodeColorInChromosome(ch, node);
            }
            popolationArray.add(ch);
        }
        
    }
    
    private void setNodeColorInChromosome(Chromosome ch, Node node) {
        if(ch.genes[node.getIndex()] != -1) {
            return;
        }
        boolean limited = false;
        for(Node toNode : node.edgesTo) {
            if(ch.genes[toNode.getIndex()] != -1) {
                limited = true;
                break;
            }
        }
        if(limited == false) {
            ch.genes[node.getIndex()] = 0;
        } else {

            for(int i = 0; i < maxColor; i++) {
                arr[i] = 0;
            }
            for(Node toNode : node.edgesTo) {
                for(int i = 0; i < maxColor; i++) {
                    if(i != ch.genes[toNode.getIndex()]) {
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
            if(Math.random() <= 0.3) {
                ch.genes[node.getIndex()] = maxIndex;
            } else {
                if(Math.random() <= 0.3) {
                    arr[maxIndex] = 0;
                    maxIndex = 0;
                    for(int i = 0; i < maxColor; i++) {
                        if(arr[i] > arr[maxIndex]) {
                            maxIndex = i;
                        }
                    }
                    ch.genes[node.getIndex()] = maxIndex;
                } else {
                    ch.genes[node.getIndex()] = (int) (Math.random() * maxColor);
                    //ch.genes[node.getIndex()] = 0;
                }
            }
        }
        for(Node toNode : node.edgesTo) {
            setNodeColorInChromosome(ch, toNode);
        }
    }
    
}
