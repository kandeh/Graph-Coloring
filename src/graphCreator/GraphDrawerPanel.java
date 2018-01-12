package graphCreator;

import graph.Graph;
import graph.Node;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

/**
 *
 * @author Alireza
 */
public class GraphDrawerPanel extends JPanel {
    
    public Graph graph = null;
    
    public final int nodeRadius = 15;
    
    private Node selectedNode = null;
    
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    
    public GraphDrawerPanel(Graph graph) {
        this.graph = graph;
        
        this.setVisible(true);
        
        this.addMouseListener(new MouseAdapter() {

            
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                selectedNode = null;
                for(Node node : graph.getNodes()) {
                    if(Util.getDistance(e.getX(), e.getY(), node) <= nodeRadius) {
                        selectedNode = node;
                        repaint();
                        return;
                    }
                }
                
                for(Node node : graph.getNodes()) {
                    if(Util.getDistance(e.getX(), e.getY(), node) <= nodeRadius * 3) {
                        return;
                    }
                }
                
                graph.addNode(new Node(e.getX(), e.getY()));
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                
                if(selectedNode == null) {
                    repaint();
                    return;
                }
                Node toSelectedNode = null;
                for(Node node : graph.getNodes()) {
                    if(Util.getDistance(e.getX(), e.getY(), node) <= nodeRadius) {
                        toSelectedNode = node;
                    }
                }
                
                if(toSelectedNode != null &&toSelectedNode != selectedNode) {
                    if(graph.isBidirectedEdge(selectedNode, toSelectedNode)) {
                        graph.removeBidirectedEdge(selectedNode, toSelectedNode);
                    } else {
                        graph.addBidirectedEdge(selectedNode, toSelectedNode);
                    }
                }
                selectedNode = null;
                repaint();
            }
            
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {


            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseMoved(e);
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                repaint();
            }

        });
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Node nearNode = null;
        

        
        g.setColor(Color.cyan);
        if(selectedNode != null) {
            
            for(Node node : graph.getNodes()) {
                if(node != selectedNode && Util.getDistance(lastMouseX, lastMouseY, node) <= nodeRadius * 1.5) {
                    nearNode = node;
                }
            }
        
            g.fillRect(selectedNode.getX() - nodeRadius, selectedNode.getY() - nodeRadius, 2 * nodeRadius + 1, 2 * nodeRadius + 1);
            g.setColor(Color.orange);
            if(nearNode != null) {
                g.fillRect(nearNode.getX() - nodeRadius, nearNode.getY() - nodeRadius, 2 * nodeRadius + 1, 2 * nodeRadius + 1);
            }

        }
        

        
        if(selectedNode != null) {
            g.setColor(Color.black);
        
            if(nearNode == null) {
                g.drawLine(selectedNode.getX(), selectedNode.getY(), lastMouseX, lastMouseY);
            } else {
                g.drawLine(selectedNode.getX(), selectedNode.getY(), nearNode.getX(), nearNode.getY());
            }
        }
        
        g.setColor(Color.black);
        
        for(Node node : graph.getNodes()) {
            for(Node toNode : node.edgesTo) {
                g.drawLine(node.getX(), node.getY(), toNode.getX(), toNode.getY());
            }
        }
        
        
        for(Node node : graph.getNodes()) {
            g.setColor(Color.lightGray);
            g.fillOval(node.getX() - nodeRadius, node.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
            g.setColor(Color.black);
            g.drawOval(node.getX() - nodeRadius, node.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        }
            
    }
    
}
