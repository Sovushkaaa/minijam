//---------------------------

package shape;

//---------------------------

import java.awt.Graphics;
import util.CVector;

//---------------------------

public class CAxes extends CShapeDraw {

    public CAxes() {
        super(EShapeType.Axes, "Axes");
        
        //add vertex
        for(int i = 0; i < 4; ++i)
            this.m_vertexes.addPoint(0, 0);
    }
    
    //---------------------------
    
    @Override
    public void setSize(CVector size) {
        m_size = size;
        
        //horizontal line
        m_vertexes.xpoints[0] = 0;
        m_vertexes.ypoints[0] = size.y/2;
        m_vertexes.xpoints[1] = size.x;
        m_vertexes.ypoints[1] = size.y/2;
        
        //vertical line
        m_vertexes.xpoints[2] = size.x/2;
        m_vertexes.ypoints[2] = 0;
        m_vertexes.xpoints[3] = size.x/2;
        m_vertexes.ypoints[3] = size.y;
    }
    
    //---------------------------

    @Override
    public void draw(Graphics g) {
        
        g.setColor(m_style.Color);
        
        this.updateAngle(g);
        this.setStrokeThickness(g, m_style.Thickness);
        
        g.drawLine( m_vertexes.xpoints[0], m_vertexes.ypoints[0], 
                    m_vertexes.xpoints[1], m_vertexes.ypoints[1]);
        
        g.drawLine( m_vertexes.xpoints[2], m_vertexes.ypoints[2], 
                    m_vertexes.xpoints[3], m_vertexes.ypoints[3]);
    }
}

//---------------------------
