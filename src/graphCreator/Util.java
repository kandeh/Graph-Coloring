package graphCreator;

import graph.Node;

/**
 *
 * @author Alireza
 */
public class Util {
    
    public final static double INF = 1e80; 
    
    public static double getDistance(int x, int y, Node node) {
        return Math.hypot(x - node.getX(), y - node.getY());
    }
    
}
