package UITest;

import minijam.CMainForm;
import minijam.Render;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import shape.CShapeBase;
import shape.CTriangle;

import java.awt.Color;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author 987
 */

public class UITriangle {

    private FrameFixture m_window;

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        CMainForm frame = GuiActionRunner.execute(CMainForm::new);
        m_window = new FrameFixture(frame);
        m_window.show(); 
    }

    @AfterEach
    public void tearDown() {
        m_window.cleanUp(); 
    }

    @Test
    public void testTriangleColorChangesOnButtonClick() {
        m_window.button("btn_triangle_color").requireVisible();

        CMainForm form = (CMainForm) m_window.target();
        Render render = form.getRender();
        ArrayList<CShapeBase> shapes = render.getShapes();

        CTriangle triangle = null;
        for (CShapeBase shape : shapes) {
            if (shape instanceof CTriangle) {
                triangle = (CTriangle) shape;
                break;
            }
        }
        assertNotNull(triangle);
        assertEquals(Color.RED, triangle.getStyle().Color);

        m_window.button("btn_triangle_color").click();

        assertEquals(Color.BLUE, triangle.getStyle().Color);
    }
    private void assertNotNull(Object obj) {
        if (obj == null) {
            throw new AssertionError();
        }
    }
}
