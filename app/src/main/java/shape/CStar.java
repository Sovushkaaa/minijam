/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape;
import util.CVector;
/**
 *
 * @author
 */
public class CStar  extends CShapeDraw implements IShapeUpdatable{ //
    public CStar() {
        super(EShapeType.Star, "Star");
        for(float i = 0; i < 2*Math.PI; i+=0.04)
            this.m_vertexes.addPoint(0, 0);
        m_isAnimation = false;
        k = 0;
        kDelta = 0.01;
    }
    @Override
    public void setSize(CVector size) {
        m_size = size;
    }
    
    @Override
    public void update() {
        
        m_vertexes.reset();
        
        for(float i = 0; i < 2*Math.PI; i+=0.04)
            this.m_vertexes.addPoint(0, 0);
        
        float m = 3;
        float n = 5;
        
        int counter = 0;
        for (float i = 0; i < 2*Math.PI; i+=0.04) {
            var inner = Math.cos((2*(Math.asin( Math.abs(Math.sin(k)) )+Math.PI*m))/(2*n));
            var denom = Math.cos((2*Math.asin( Math.abs(Math.sin(k)) *Math.cos(n*i))+Math.PI*m)/(2*n));
            
            var angle = inner /denom;
            
            int x = (m_size.x/2) + (int)(angle * Math.cos(i)*m_size.x);
            int y = (m_size.y/2) + (int)(angle * Math.sin(i)*m_size.y);
            
            m_vertexes.xpoints[counter] = x;
            m_vertexes.ypoints[counter] = y;
            counter++;
        }
        
        m_vertexes.translate(-m_origin.x, -m_origin.y);
        m_vertexes.translate(m_position.x, m_position.y);
        m_vertexes.invalidate();
        
        if (m_isAnimation){            
            k += kDelta;
        }
    }
    
    public void setStateAnimation(boolean state){
        m_isAnimation = state;
    }
    
    public boolean getStateAnimation(){
        return m_isAnimation;
    }
    
    protected boolean m_isAnimation;
    protected double k;
    protected double kDelta;
    protected int test;
    
}
