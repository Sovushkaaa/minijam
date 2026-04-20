//---------------------------

package shape;

//---------------------------

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import util.CVector;

//---------------------------

public abstract class CShapeDraw extends CShapeBase implements IShapeDrawable {

    public CShapeDraw(EShapeType type, String stringType) {
        super(type, stringType);
    }
    
    //---------------------------

    @Override
    public void draw(Graphics g) {
        
        updateAngle(g);
        
        g.setColor(m_style.Color);
        g.fillPolygon(m_vertexes);
        
        drawStroke(g, m_style.BorderColor);
    }
    
    //---------------------------
    
    protected void updateAngle(Graphics g) {
        ((Graphics2D)g).rotate(m_angle, m_position.x, m_position.y);
    }
    
    //---------------------------
    
    protected void setStrokeThickness(Graphics g, float thickness) {
        ((Graphics2D)g).setStroke(new BasicStroke(m_style.Thickness));
    }
    
    //---------------------------
    
    protected void drawStroke(Graphics g, Color color) {
        if(m_style.Thickness != 0) {
            g.setColor(color);
            setStrokeThickness(g, m_style.Thickness);
            
            g.drawPolygon(m_vertexes);
        }
    }
}

//---------------------------
