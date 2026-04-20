package shape;

//---------------------------

import java.awt.Color;
import util.CVector;

//---------------------------

public class COctagon extends CShapeDraw implements IShapeUpdatable {
    
    //---------------------------
    
    public COctagon() {
        super(EShapeType.Octagon, "Octagon");
        
        for(int i = 0; i < 8; ++i)
            m_vertexes.addPoint(0, 0);
        
        this.m_stepAnimation = 0.03;
        this.m_baseSize = new CVector(200, 200);
        
        this.m_greenHue     = 0.25f;
        this.m_blueHue      = 0.5f;
        this.m_saturation   = 1.0f;
        this.m_brightness   = 1.0f;
        
        this.m_sin = 0;
        this.m_deltaTime = 0;
        
        this.m_isAnimation = false;
    }
    
    //---------------------------

    @Override
    public void setSize(CVector size) {

        m_size = size;
        
        this.setOrigin(new CVector(0,0));
        m_size = size;
        
        double radius = Math.min(m_size.x, m_size.y) / 2.0;
        double startAngle = 0;
        
        double step = 2 * Math.PI / m_vertexes.npoints;
        
        for (int i = 0; i < m_vertexes.npoints; ++i) {
            double ang = startAngle + i * step;
            
            m_vertexes.xpoints[i] = (int) (radius + radius * Math.cos(ang));
            m_vertexes.ypoints[i] = (int) (radius + radius * Math.sin(ang));
        }
        
        m_vertexes.translate(m_position.x, m_position.y);
    }
    
    //---------------------------

    @Override
    public void update() {
        
        if(!m_isAnimation)
            return;

        float progress = (float)(Math.sin(m_deltaTime) + 1) / 2;
        float currentHue = m_blueHue + (m_greenHue - m_blueHue) * progress;
        
        m_style.BorderColor = Color.getHSBColor(currentHue, m_saturation, m_brightness);
        
        CVector size = new CVector( MIN_SIZE + (int)(Math.abs(progress)*(m_baseSize.x-MIN_SIZE)),
                                    MIN_SIZE + (int)(Math.abs(progress)*(m_baseSize.y-MIN_SIZE)));
        
        this.setSize(size);
        this.setOrigin(new CVector(size.x/2, size.y/2));
        
        m_sin+=3;
        m_deltaTime+=m_stepAnimation;
        
        this.setAngle(m_sin);
    }
    
    //---------------------------
    
    /*
    * animation step. Responsible for the animation speed
    */
    public void setStepAnimation(double step) {
        m_stepAnimation = step;
    }
    
    //---------------------------
    
    /*
    * the reference size for animation
    */
    public void setBaseSize(CVector size) {
        m_baseSize = new CVector(size);
    }
    
    //---------------------------
    
    public void setStateAnimation(boolean state) {
        this.m_isAnimation = state;
    }
    
    //---------------------------
    
    public boolean getStateAnimation() {
        return this.m_isAnimation;
    }
    
    //---------------------------
    
    private CVector m_baseSize;
    
    private double  m_sin,
                    m_stepAnimation;
    
    private final float     m_greenHue,
                            m_blueHue,
                            m_saturation,
                            m_brightness;
    
    private float m_deltaTime;
    
    private static final int MIN_SIZE = 10;
    
    private boolean m_isAnimation;
}
