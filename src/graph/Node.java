package graph;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class Node {
    
    private int x = 0;
    private int y = 0;
    private int index = 0;
    private int color = 0;
    
    public ArrayList<Node> edgesTo = new ArrayList<Node>();

    public int getColor() {
        return color;
    }

    public int getIndex() {
        return index;
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
    
    public Node(int index, int x, int y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

}
