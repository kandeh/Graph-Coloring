package graphCreator;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Alireza
 */

public class GraphFileFilter extends FileFilter {
    
    
    public static final String EXTENTION = ".graph";
    
    @Override
    public boolean accept(File f) {
        return f.getName().endsWith(EXTENTION);
    }
    
    @Override
    public String getDescription() {
        return EXTENTION;
    }
    
}
