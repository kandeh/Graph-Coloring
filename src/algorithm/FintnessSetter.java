package algorithm;

import graph.Graph;
import graph.Node;
import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class FintnessSetter {

    public FintnessSetter(Graph graph, ArrayList<Chromosome> populationArray, int maxColor) {
        for(Chromosome ch : populationArray) {
            if(ch.isFitnessSetted() == false) {
                ch.setFintess(getFitness(graph, ch, maxColor));
            }
        }
    }
    
    private double getFitness(Graph graph, Chromosome ch, int maxColor) {
        double res = 0;
        int edges = 0;
        for(Node node : graph.getNodes()) {
            for(Node toNode : node.edgesTo) {
                edges++;
                if(ch.genes[node.getIndex()] == ch.genes[toNode.getIndex()]) {
                    res++;
                }
            }
        }
        if(edges != 0) {
            res = res / 2;
            edges = edges / 2;
            res = (edges - res) / edges;
        } else {
            res = 1;
        }
        res = Math.pow(res, 10);

        
        int nodes = graph.getNodes().size();
        
        return 0.95 * res + 0.05 * Math.pow(((double) (nodes - Util.getColorsDef(ch, maxColor)) / nodes), 10);
    }
    
    
    
}
