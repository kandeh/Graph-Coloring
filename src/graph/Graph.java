package graph;

import java.util.ArrayList;

/**
 *
 * @author Alireza
 */

public class Graph {
    
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private int lastIndex = -1;
    
    private int edges = 0;
    
    public void addNode(int x, int y) {
        lastIndex++;
        nodes.add(new Node(lastIndex, x, y));
    }
    
    public void addBidirectedEdge(Node a, Node b) {
        if(isBidirectedEdge(a, b) == false) {
            a.edgesTo.add(b);
            b.edgesTo.add(a);
            edges++;
        }
    }
    
    public void removeBidirectedEdge(Node a, Node b) {
        if(isBidirectedEdge(a, b) == true) {
            a.edgesTo.remove(b);
            b.edgesTo.remove(a);
            edges--;
        }
    }
    
    public int getEdges() {
        return this.edges;
    }
    
    public boolean isBidirectedEdge(Node a, Node b) {
        return a.edgesTo.contains(b) && b.edgesTo.contains(a);
    }
    
    public ArrayList<Node> getNodes() {
        return nodes;
    }
    
}
