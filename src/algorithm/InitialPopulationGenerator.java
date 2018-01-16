package algorithm;

import graph.Graph;
import graph.Node;
import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class InitialPopulationGenerator {

    private int colors = 0;
    private int arr[] = null;
    
    public InitialPopulationGenerator(Graph graph, ArrayList<Chromosome> popolationArray, int size, int colors) {
        this.colors = colors;
        arr = new int[colors];
        while(size-- > 0) {
            Chromosome ch = new Chromosome(graph, colors);
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
            ch.genes[node.getIndex()] = (int) (Math.random() * colors);
        } else {

            for(int i = 0; i < colors; i++) {
                arr[i] = 0;
            }
            for(Node toNode : node.edgesTo) {
                for(int i = 0; i < colors; i++) {
                    if(i != ch.genes[toNode.getIndex()]) {
                        for(int k = 0; k < 1; k++) {
                            arr[i]++;
                        }
                    }
                }
            }
            int maxIndex = 0;
            for(int i = 0; i < colors; i++) {
                if(arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            
            //ch.genes[node.getIndex()] = (int) (Math.random() * colors);
            //ch.genes[node.getIndex()] = 0;
            
            if(Math.random() <= 0.7) {
                ch.genes[node.getIndex()] = maxIndex;
            } else {
                if(Math.random() <= 0.7) {
                    arr[maxIndex] = 0;
                    maxIndex = 0;
                    for(int i = 0; i < colors; i++) {
                        if(arr[i] > arr[maxIndex]) {
                            maxIndex = i;
                        }
                    }
                    ch.genes[node.getIndex()] = maxIndex;
                } else {
                    ch.genes[node.getIndex()] = (int) (Math.random() * colors);
                }
            }
        }
        for(Node toNode : node.edgesTo) {
            setNodeColorInChromosome(ch, toNode);
        }
    }
    
}
