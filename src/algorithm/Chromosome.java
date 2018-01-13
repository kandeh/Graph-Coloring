package algorithm;

/**
 *
 * @author Alireza
 */

public class Chromosome {

    protected int genes[] = null;
    
    private boolean isFitnessSetted = false;
    private double fitness = 0;
    private boolean isNormalizedFitnessSetted = false;
    private double normalizedFitness = 0;
    
    public Chromosome(int length) {
        genes = new int[length];
        unsetFitness();
    }
    
    public final void unsetFitness() {
        this.isFitnessSetted = false;
        this.fitness = 0;
    }
    
    public void setFintess(double fitness) {
        this.isFitnessSetted = true;
        this.fitness = fitness;
    }
    
    public boolean isFitnessSetted() {
        return isFitnessSetted;
    }
    
    public double getFitness() {
        if(isFitnessSetted() == false) {
            (new Exception("fitness is not setted.")).printStackTrace();
        }
        return this.fitness;
    }
    
    public final void unsetNormalizedFitness() {
        this.isNormalizedFitnessSetted = false;
        this.normalizedFitness = 0;
    }
    
    public void setNormalizedFintess(double normalizedFitness) {
        this.isNormalizedFitnessSetted = true;
        this.normalizedFitness = normalizedFitness;
    }
    
    public boolean isNormalizedFitnessSetted() {
        return isNormalizedFitnessSetted;
    }
    
    public double getNormalizedFitness() {
        if(isNormalizedFitnessSetted() == false) {
            (new Exception("normalized fitness is not setted.")).printStackTrace();
        }
        return this.normalizedFitness;
    }
    
    public int[] getGenes() {
        return genes;
    }
    
    @Override
    public String toString() {
        String res = "";
        if(isFitnessSetted) {
            res += "fitness = " + getFitness() + " | ";
        }
        if(isNormalizedFitnessSetted) {
            res += "normalizedFitness = " + getNormalizedFitness() + " | ";
        }
        for(int i = 0; i < genes.length; i++) {
            res += genes[i] + (i < genes.length - 1 ? " , " : "");
        }
        return res;
    }
    
}
