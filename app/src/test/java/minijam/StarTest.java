/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minijam;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shape.CStar;

/**
 *
 * @author Мария
 */
public class StarTest {
    @Test
    public void SetSizeTriangleTest() {
        CStar star = new CStar();
        boolean testState = true;
        star.setStateAnimation(testState);
        assertEquals(testState, star.getStateAnimation());
    }
}
