//---------------------------

package minijam;

//---------------------------

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;
import shape.CShapeBase;
import shape.IShapeDrawable;
import shape.IShapeUpdatable;

//---------------------------

public class Render extends JPanel {
    
    public Render() {
        super();
        m_shapes = new ArrayList<>();
    }
    
    //---------------------------
    
    public boolean addShape(CShapeBase shape) {
        m_shapes.add(shape);
        return true;
    }
    
    //---------------------------
    
    public ArrayList<CShapeBase> getShapes() {
        return m_shapes;
    }
    
    //---------------------------
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        AffineTransform originalTransform = ((Graphics2D)g).getTransform();
        

        for (CShapeBase shape : m_shapes) {
            if(shape instanceof IShapeDrawable o)
                o.draw(g);
            
            ((Graphics2D)g).setTransform(originalTransform); //fix rotate :D
        }
        
        ((Graphics2D)g).setTransform(originalTransform); //restore the transformation for gui elements
    }

    //---------------------------
    
    public void update() {
        for (CShapeBase shape : m_shapes) {
            if(shape instanceof IShapeUpdatable o)
                o.update();
        }
    }
    
    //---------------------------
    
    public void clear() {
        m_shapes.clear();
        System.gc();
    }
    
    protected ArrayList<CShapeBase> m_shapes;
}

//---------------------------
