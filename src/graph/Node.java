package graph;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */
public class Node {
    
    private int x = 0;
    private int y = 0;
    
    private int color = 0;
    
    public ArrayList<Node> edgesTo = new ArrayList<Node>();

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
