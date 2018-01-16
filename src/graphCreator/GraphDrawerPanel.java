package graphCreator;

import graph.Graph;
import graph.Node;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Alireza
 */

public class GraphDrawerPanel extends JPanel {
    
    private Graph graph = null;
    public final int nodeRadius = 6;
    private Node selectedNode = null;
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private boolean editable = true;
    private boolean paintColors = false;
    
    
    private static Color __colors[] = {
        new Color(255, 000, 000),
        new Color(000, 250, 000),
        new Color(000, 000, 250),
        new Color(255, 255, 000),
        new Color(255, 000, 255),
        new Color(000, 255, 255),
        new Color(128, 000, 000),
        new Color(000, 128, 000),
        new Color(000, 000, 128),
        new Color(128, 128, 000),
        new Color(128, 000, 128),
        new Color(000, 128, 128),
    };
    
    public static ArrayList<Color> colors = new ArrayList<>();
    
    static {
    	for(int i = 0; i < __colors.length; i++) {
            colors.add(__colors[i]);
    	}
    	for(int i = 0; i < 350; i++) {
            colors.add(new Color((int)(Math.random() * 255), (int)( Math.random() * 255), (int)( Math.random() * 255)));
    	}
    }
    
    public GraphDrawerPanel(Graph graph, boolean editable, boolean paintColors) {
        this.editable = editable;
        this.graph = graph;
        this.paintColors = paintColors;
        
        this.setVisible(true);
        
        this.addMouseListener(new MouseAdapter() {

            
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                
                Graph graph = GraphDrawerPanel.this.graph;
                
                if(editable == false) {
                    return;
                }
                
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                selectedNode = null;
                for(Node node : graph.getNodes()) {
                    if(Util.getDistance(e.getX(), e.getY(), node) <= nodeRadius * 2.5) {
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
                
                graph.addNode(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                Graph graph = GraphDrawerPanel.this.graph;
                
                if(editable == false) {
                    return;
                }
                
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                
                if(selectedNode == null) {
                    repaint();
                    return;
                }
                Node toSelectedNode = null;
                for(Node node : graph.getNodes()) {
                    if(Util.getDistance(e.getX(), e.getY(), node) <= nodeRadius * 2.5) {
                        toSelectedNode = node;
                    }
                }
                
                if(toSelectedNode == null) {
                    selectedNode.setXY(e.getX(), e.getY());
                } else if(toSelectedNode != null &&toSelectedNode != selectedNode) {
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
                
                if(editable == false) {
                    return;
                }
                
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                repaint();
            }

        });
    }

    public Graph getGraph() {
        return this.graph;
    }
    
    public void setGraph(Graph graph) {
        selectedNode = null;
        this.graph = graph;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 20));
        g.fillRect(0, 0, getWidth(), getHeight());
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
            
            if(paintColors) {
                g.setColor(colors.get(node.getColor()));
            } else {
                 g.setColor(Color.lightGray);
            }

            g.fillOval(node.getX() - nodeRadius, node.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
            g.setColor(Color.black);
            g.drawOval(node.getX() - nodeRadius, node.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        }
            
    }

}
