/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape;

import util.CVector;

/**
 *
 * @author Кристина
 */
public class Hexagon extends CShapeDraw {
    public Hexagon() {
        super(EShapeType.Hexagon, "Hexagon");
        for(int i = 0; i < 6; ++i)
            this.m_vertexes.addPoint(0, 0);
    }
    
    //---------------------------
	
    @Override
    public void setSize(CVector size) {
        m_size = size;
        updateHexagonVertexes();
    }
    
    //---------------------------
    
    private void updateHexagonVertexes() {
        int centerX = m_size.x / 2;
        int centerY = m_size.y / 2;
        int radius = Math.min(m_size.x, m_size.y) / 2;
        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI * i / 6;
            int x = centerX + (int)(radius * Math.cos(angle));
            int y = centerY + (int)(radius * Math.sin(angle));
            
            m_vertexes.xpoints[i] = x;
            m_vertexes.ypoints[i] = y;
        }
        
        m_vertexes.invalidate();
    }
}
