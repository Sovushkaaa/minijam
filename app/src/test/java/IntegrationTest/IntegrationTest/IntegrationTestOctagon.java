//---------------------------

package IntegrationTest;

//---------------------------

import org.junit.jupiter.api.*;
import minijam.*;

import static org.junit.jupiter.api.Assertions.*;
import shape.COctagon;

//---------------------------

/**
 *
 * @author vladf
 */
public class IntegrationTestOctagon {
    @BeforeEach
    public void setUp() {
        m_render = new Render();
        m_octagon = new COctagon();
        m_render.addShape(m_octagon);
    }

    @Test
    public void testOctagonAnimationStartsOnClick() {
        
        m_octagon.setStateAnimation(true);
        COctagon octagon = (COctagon)m_render.getShapes().get(0);
        
        assertTrue(octagon.getStateAnimation());
    }
    
    private Render m_render;
    private COctagon m_octagon;
}

//---------------------------
