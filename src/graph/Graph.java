package graph;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */
public class Graph {
    
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private int lastIndex = -1;
    public void addNode(int x, int y) {
        lastIndex++;
        nodes.add(new Node(lastIndex, x, y));
    }
    
    public void addBidirectedEdge(Node a, Node b) {
        a.edgesTo.add(b);
        b.edgesTo.add(a);
    }
    
    public void removeBidirectedEdge(Node a, Node b) {
        a.edgesTo.remove(b);
        b.edgesTo.remove(a);
    }
    
    public boolean isBidirectedEdge(Node a, Node b) {
        return a.edgesTo.contains(b) && b.edgesTo.contains(a);
    }
    
    public ArrayList<Node> getNodes() {
        return nodes;
    }
    
}
