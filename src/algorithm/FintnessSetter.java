package algorithm;

import graph.Graph;
import graph.Node;
import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class FintnessSetter {

    public FintnessSetter(Graph graph, ArrayList<Chromosome> populationArray) {
        for(Chromosome ch : populationArray) {
            ch.setFintess(getFitness(graph, ch));
        }
    }
    
    private double getFitness(Graph graph, Chromosome ch) {
        double res = 0;
        for(Node node : graph.getNodes()) {
            for(Node toNode : node.edgesTo) {
                if(ch.genes[node.getIndex()] == ch.genes[toNode.getIndex()]) {
                    res++;
                }
            }
        }
        return res / 2;
    }
    
}
