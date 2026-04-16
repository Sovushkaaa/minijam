//---------------------------

package UITest;

//---------------------------

import java.util.ArrayList;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;

import org.junit.jupiter.api.*;

import minijam.CMainForm;
import minijam.Render;
import static org.junit.jupiter.api.Assertions.assertEquals;
import shape.CShapeBase;
import shape.COctagon;

//---------------------------

/**
 *
 * @author vladf
 */
public class UIOctagon {
    
    //---------------------------
    
    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }
    
    //---------------------------

    @BeforeEach
    public void setUp() {
        CMainForm frame = GuiActionRunner.execute(() -> new CMainForm());
        m_window = new FrameFixture(frame);
        m_window.show();
    }
    
    //---------------------------

    @AfterEach
    public void tearDown() {
        m_window.cleanUp();
    }
    
    //---------------------------

    @Test
    public void testButtonClick() {
        
        m_window.button("btn_octagon_animation").requireVisible();
        m_window.button("btn_octagon_animation").click();
        m_window.button("btn_octagon_animation").requireText("Stop animation");
        
        Render render = ((CMainForm)m_window.target()).getRender();
        ArrayList<CShapeBase> shapes = render.getShapes();
        
        int count = 0;
        for(CShapeBase shape : shapes) {
            if(shape instanceof COctagon cOctagon)
                if( cOctagon.getStateAnimation() )
                    count++;
        }
        
        int expectedAnimatedOctagons = 1;
        assertEquals(expectedAnimatedOctagons, count);
        
    }
    
    //---------------------------
    
    private FrameFixture m_window;
}

//---------------------------