/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minijam;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.CVector;
import shape.Hexagon;

class HexagonTest {

    @Test
    void UnitHexagonTest() {
        // Arrange
        Hexagon hexagon = new Hexagon();
        CVector newSize = new CVector(150, 100);
        
        // Acts
        hexagon.setSize(newSize);
        
        // Asserts
        CVector actualSize = hexagon.getSize();
        assertEquals(150, actualSize.x);
        assertEquals(100, actualSize.y);
    }
}