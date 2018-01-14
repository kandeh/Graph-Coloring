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
                return Double.compare(o2.getFitness(), o1.getFitness());
            }
        });
    }
    
    public static void sortByNormalizedFitness(ArrayList<Chromosome> chromosomes) {
        Collections.sort(chromosomes, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                return Double.compare(o2.getNormalizedFitness(), o1.getNormalizedFitness());
            }
        });
    }
    
    
    public static double getAvgFitness(ArrayList<Chromosome> chromosomes) {
        double sum = 0;
        if(chromosomes.isEmpty()) {
            return 0;
        }
        for(Chromosome ch : chromosomes) {
            sum += ch.getFitness();
        }
        return sum / chromosomes.size();
    }
    
    public static void shuffle(ArrayList<Chromosome> chromosomes) {
        Collections.shuffle(chromosomes);
    }
    
    private static int __arr[] = null;
    public static int getColorsDef(Chromosome ch, int maxColor) {
        if(__arr == null || __arr.length < maxColor) {
            __arr = new int[maxColor];
        }
        for(int i = 0; i < maxColor; i++) {
            __arr[i] = 0;
        }
        for(int i = 0; i < ch.genes.length; i++) {
            __arr[ch.genes[i]]++;
        }
        int minIndex = 0;
        int maxIndex = 0;
        for(int i = 0; i < maxColor; i++) {
            if(__arr[maxIndex] < __arr[i]) {
                maxIndex = i;
            }
        }
        
        /*for(int i = 0; i < maxColor; i++) {
            if(__arr[i] == 0) {
                __arr[i] = 1000000;
            }
        }*/
        
        for(int i = 0; i < maxColor; i++) {
            if(__arr[minIndex] > __arr[i]) {
                minIndex = i;
            }
        }
        return __arr[maxIndex] - __arr[minIndex];
    }
    
    
}
