package algorithm;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class FitnessProportionSelector {

    public FitnessProportionSelector(ArrayList<Chromosome> from, ArrayList<Chromosome> to, int size) {
        Util.sortByNormalizedFitness(from);
        
        
        while(size-- > 0) {
            to.add(select(from));
        }
    }
    
    private Chromosome select(ArrayList<Chromosome> from) {
        double r = Math.random();
        //System.out.println("--------------------");
        for(int i = 0; i < from.size(); i++) {
            
            Chromosome ch = from.get(i);
            r -= ch.getNormalizedFitness();
            //r -= (Math.pow(ch.getNormalizedFitness(), 0.99));
            if(r <= 1e-8) {
                //System.out.println(i);
                return ch;
            }
        }
        //System.out.println("last");
        return from.get(from.size() - 1);
    }
        
}
