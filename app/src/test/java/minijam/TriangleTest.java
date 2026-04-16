/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minijam;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shape.CTriangle;
import util.CVector;
/**
 *
 * @author 987
 */
public class TriangleTest {

    @Test
    public void SetSizeTriangleTest() {
        // Arrange
        CTriangle triangle = new CTriangle();
        CVector newSize = new CVector(150, 100); 
        // Act
        triangle.setSize(newSize);
        // Assert
        CVector actualSize = triangle.getSize();
        assertEquals(150, actualSize.x);
        assertEquals(100, actualSize.y);
    }
}
