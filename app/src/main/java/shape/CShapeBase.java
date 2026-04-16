//---------------------------

package shape;

//---------------------------

import java.awt.Color;
import java.awt.Polygon;
import minijam.Log;
import util.CVector;

//---------------------------

public abstract class CShapeBase {
    
    public CShapeBase(EShapeType type, String stringType) {
        m_vertexes  = new Polygon();
        m_position  = new CVector(0,0);
        m_origin    = new CVector(0,0);
        m_size      = new CVector(0,0);
        
        m_style = new CShapeStyle(  new Color(0,0,0,0), 
                                    new Color(0,0,0,0), 2);
        
        m_type = type;
        m_stringType = stringType;
        
        Log.print(this, "init shape: " + stringType);
    }
    
    //---------------------------
    
    public abstract void setSize(CVector size);
    
    //---------------------------
    
    public CVector getSize() {
        return new CVector(m_size);
    }
    
    //---------------------------
    
    public void setPosition(CVector position) {
        
        if(m_position.x == position.x && m_position.y == position.y)
            return;
        
        m_vertexes.translate(-m_position.x, -m_position.y);
        m_position = position;
        
        m_vertexes.translate(m_position.x, m_position.y);
        m_vertexes.invalidate();
    }
    
    //---------------------------
    
    public CVector getPosition() {
        return new CVector(m_position);
    }
    
    //---------------------------
    /*
    * angle is indicated in degrees
    */
    public void setAngle(double angle) {
        m_angle = Math.toRadians(angle);
    }
    
    //---------------------------
    
    public double getAngle() {
        return m_angle;
    }
    
    //---------------------------
    
    public void setOrigin(CVector origin) {
        
        if(m_origin.x == origin.x && m_origin.y == origin.y)
            return;
        
        m_vertexes.translate(m_origin.x, m_origin.y);
        m_origin = origin;
        
        m_vertexes.translate(-m_origin.x, -m_origin.y);
        m_vertexes.invalidate();
    }
    
    //---------------------------
    
    public CVector getOrigin() {
        return m_origin;
    }
    
    //---------------------------
    
    public String getStringType() {
        return this.m_stringType;
    }
    
    //---------------------------
    
    public EShapeType getType() {
        return m_type;
    }
    
    //---------------------------
    
    public void setStyle(CShapeStyle style) {
        this.m_style = style;
    }
    
    //---------------------------
    
    public CShapeStyle getStyle() {
        return new CShapeStyle(this.m_style);
    }
    
    //---------------------------
    
    public boolean testPoint(CVector Point) {
        return m_vertexes.contains(Point.x, Point.y); 
    }
    
    //---------------------------
    
    protected String m_stringType;
    protected EShapeType m_type;
    
    protected CVector   m_position,
                        m_origin,
                        m_size;
    
    protected CShapeStyle m_style;
    
    protected double m_angle;
    
    protected Polygon m_vertexes; //< array vertex
}

//---------------------------
