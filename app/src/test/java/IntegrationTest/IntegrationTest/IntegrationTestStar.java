//---------------------------

package IntegrationTest;

//---------------------------

import org.junit.jupiter.api.*;
import minijam.*;

import static org.junit.jupiter.api.Assertions.*;
import shape.CStar;

//---------------------------

public class IntegrationTestStar {
    @BeforeEach
    public void setUp() {
        m_render = new Render();
        m_star = new CStar();
        m_render.addShape(m_star);
    }

    @Test
    public void testStarAnimationStartsOnClick() {
        
        m_star.setStateAnimation(true);
        CStar star = (CStar)m_render.getShapes().get(0);
        
        assertTrue(star.getStateAnimation());
    }
    
    private Render m_render;
    private CStar m_star;
}

//---------------------------
