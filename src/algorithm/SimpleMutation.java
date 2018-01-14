package algorithm;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class SimpleMutation {
    
    public void doMutation(Chromosome ch, int maxColor) {
        int len = ch.genes.length;
        int n = Math.max(1, len / 20);
        for(int i = 0; i < n; i++) {
            int p = (int) (Math.random() * (len));
            ch.genes[p] = (int) (Math.random() * maxColor);
            ch.unsetFitness();
            ch.unsetNormalizedFitness();
        }
    }
    
}
