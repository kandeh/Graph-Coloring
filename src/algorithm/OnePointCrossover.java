package algorithm;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class OnePointCrossover {
    
    public OnePointCrossover() {
    }
    
    public void doCrossover(Chromosome parent1, Chromosome parent2, ArrayList<Chromosome> intermediatePopulation) {
        int len = parent1.genes.length;
        if(len <= 2) {
            intermediatePopulation.add(parent1);
            intermediatePopulation.add(parent2);
            return;
        }
        Chromosome o1 = new Chromosome(len);
        Chromosome o2 = new Chromosome(len);
        int p = (int) (Math.random() * (len - 2)) + 1;
        for(int i = 0; i < p; i++) {
            o1.genes[i] = parent1.genes[i];
            o2.genes[i] = parent2.genes[i];
        }
        
        for(int i = p; i < len; i++) {
            o1.genes[i] = parent2.genes[i];
            o2.genes[i] = parent1.genes[i];
        }
        intermediatePopulation.add(o1);
        intermediatePopulation.add(o2);
    }

}
