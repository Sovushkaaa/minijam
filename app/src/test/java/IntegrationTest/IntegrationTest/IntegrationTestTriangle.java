package IntegrationTest;

import minijam.Render;
import org.junit.jupiter.api.*;
import shape.CShapeBase;
import shape.CTriangle;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author 987
 */
public class IntegrationTestTriangle {

    private Render m_render;
    private CTriangle m_triangle;

    @BeforeEach
    void setUp() {
        m_render = new Render();
        m_triangle = new CTriangle();
        m_triangle.setSize(new util.CVector(100, 80));
        m_render.addShape(m_triangle);
    }

    @Test
    void testTriangleColorChangesAndIsReflectedInRender() {
        assertEquals(Color.RED, m_triangle.getStyle().Color);

        m_triangle.setStyle(new shape.CShapeStyle(Color.BLUE, Color.BLACK, 2));

        CShapeBase shapeInRender = m_render.getShapes().get(0);
        CTriangle triangleInRender = (CTriangle) shapeInRender;

        assertEquals(Color.BLUE, triangleInRender.getStyle().Color);

        assertSame(m_triangle, triangleInRender);
    }
}