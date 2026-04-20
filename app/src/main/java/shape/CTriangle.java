/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape;

import java.awt.Color;
/**
 *
 * @author 987
 */
import util.CVector;
import java.awt.Polygon;

public class CTriangle extends CShapeDraw {

    public CTriangle() {
    super(EShapeType.Triangle, "Triangle");
    updateVertexes(new CVector(100, 80));
    
    setStyle(new CShapeStyle(Color.RED, Color.BLACK, 2));
}

    @Override
    public void setSize(CVector size) {
        if (size.x <= 0 || size.y <= 0) return;
        m_size = new CVector(size);
        updateVertexes(m_size);
    }
    
    private void updateVertexes(CVector size) {
        m_vertexes.reset();
        int[] xPoints = {
            0,                     
            (int) size.x,          
            (int) (size.x / 2.0)   
        };
        int[] yPoints = {
            (int) size.y,          
            (int) size.y,          
            0                      
        };

        m_vertexes = new Polygon(xPoints, yPoints, 3);

        if (m_position != null) {
            m_vertexes.translate((int) m_position.x, (int) m_position.y);
        }
        if (m_origin != null && !(m_origin.x == 0 && m_origin.y == 0)) {
            m_vertexes.translate(-(int) m_origin.x, -(int) m_origin.y);
        }
    }
}