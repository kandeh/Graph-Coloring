package algorithm;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class FitnessNormalizer {
    
    public FitnessNormalizer(ArrayList<Chromosome> populationArray) {
        double sum = 0;
        for(Chromosome ch : populationArray) {
            sum += ch.getFitness();
        }
        if(sum == 0) {
            return;
        }
        for(Chromosome ch : populationArray) {
            ch.setNormalizedFintess((ch.getFitness()) / sum);
        }
    }

}
