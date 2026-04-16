/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITest;

import java.awt.Color;
import java.util.ArrayList;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;

import org.junit.jupiter.api.*;

import minijam.CMainForm;
import minijam.Render;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import shape.CShapeBase;
import shape.CShapeStyle;
import shape.Hexagon;
/**
 *
 * @author Кристина
 */
public class UIHexagon {
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
    public void testHexagonButtonVisibilityToggle() {
        
        m_window.button("btn_hexagon").requireVisible();
        m_window.button("btn_hexagon").click();
        m_window.button("btn_hexagon").requireText("Show Hexagon");
        
        Render render = ((CMainForm)m_window.target()).getRender();
        ArrayList<CShapeBase> shapes = render.getShapes();
        
        int count = 0;
        for(CShapeBase shape : shapes) {
            if(shape instanceof Hexagon hexagon)
                if( hexagon.getStyle().Color.getAlpha() == 0 )
                    count++;
        }
        
        int expectedHiddenHexagons = 1;
        assertEquals(expectedHiddenHexagons, count);
        
    }
    
    //---------------------------
    
    private FrameFixture m_window;
}
