/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithm;

import graph.Graph;
import graph.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Alireza
 */
public class Util {
    
    public static void setColors(Graph graph, Chromosome chromosome) {
        ArrayList<Node> nodes = graph.getNodes();
        int colors[] = chromosome.getGenes();
        for(Node node : nodes) {
            node.setColor(colors[node.getIndex()]);
        }
    }
    
    public static void sort(ArrayList<Chromosome> chromosomes) {
        Collections.sort(chromosomes, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                return Double.compare(o1.getFitness(), o2.getFitness());
            }
        });
    }
}
