package algorithm;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class FitnessProportionSelector {

    private double fitnessSum = 0;
    
    public FitnessProportionSelector(ArrayList<Chromosome> from, ArrayList<Chromosome> to, int size) {
        Util.sortDesc(from);
        
        for(Chromosome ch : from) {
            fitnessSum += ch.getFitness();
        }
        
        while(size-- > 0) {
            to.add(select(from));
        }
    }
    
    private Chromosome select(ArrayList<Chromosome> from) {
        double r = Math.random() * fitnessSum;
        for(int i = 0; i < from.size(); i++) {
            Chromosome ch = from.get(i);
            r -= (ch.getFitness());
            if(r <= 1e-8) {
                return ch;
            }
        }
        return from.get(from.size() - 1);
    }
        
}
