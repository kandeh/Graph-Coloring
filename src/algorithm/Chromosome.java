package algorithm;

/**
 *
 * @author Alireza
 */

public class Chromosome {

    private int genes[] = null;
    private int maxColor = 0;
    
    public Chromosome(int nodes, int maxColor) {
        this.maxColor = maxColor;
        genes = new int[nodes];
        for(int i = 0; i < nodes; i++) {
            genes[i] = (int) (Math.random() * maxColor);
        }
    }
    
    
    public int[] getGenes() {
        return genes;
    }
    
    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < genes.length; i++) {
            res += genes[i] + (i < genes.length - 1 ? " | " : "");
        }
        return res;
    }
}
