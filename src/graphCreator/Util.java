package graphCreator;

import graph.Graph;
import graph.Node;
import java.awt.Component;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Alireza
 */
public class Util {
    
    public final static double INF = 1e80; 
    
    
    
    public static void saveGraph(Component parent, Graph graph) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        GraphFileFilter graphFileFilter = new GraphFileFilter();
        fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
        fileChooser.setFileFilter(graphFileFilter);
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if(graphFileFilter.accept(selectedFile) == false) {
                selectedFile = new File(selectedFile.getName() + GraphFileFilter.EXTENTION);
            }
            try {
                PrintWriter pw = new PrintWriter(selectedFile);
                pw.println(graph.getNodes().size());
                for(Node node : graph.getNodes()) {
                    node.seen = false;
                    pw.println(node.getX() + " " + node.getY());
                }
                for(Node node : graph.getNodes()) {
                    node.seen = true;
                    for(Node toNode : node.edgesTo) {
                        if(toNode.seen == true) {
                            continue;
                        }
                        pw.println(node.getIndex() + " " + toNode.getIndex());
                    }
                }
                pw.flush();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Graph loadGraph(Component parent) {
        Graph graph = new Graph();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(new GraphFileFilter());
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Scanner scn = new Scanner(selectedFile);
                int n = scn.nextInt();
                while(n-- > 0) {
                    int x = scn.nextInt();
                    int y = scn.nextInt();
                    graph.addNode(x, y);
                }
                while(scn.hasNextInt()) {
                    int aIndex = scn.nextInt();
                    int bIndex = scn.nextInt();
                    graph.addBidirectedEdge(graph.getNodes().get(aIndex), graph.getNodes().get(bIndex));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return graph;
    }
    
    public static double getDistance(int x, int y, Node node) {
        return Math.hypot(x - node.getX(), y - node.getY());
    }
    
}
