/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import shape.CShapeBase;
import shape.EShapeType;
import shape.Hexagon;

/**
 *
 * @author Кристина
 */
public class IntegrationTestHexagon {
    @Test
    void IntegrationHexagonTest() {
        // Arrange & Act
        Hexagon hexagon = new Hexagon();
        // Assert
        assertTrue(hexagon instanceof CShapeBase, "Hexagon должен наследоваться от CShapeBase");
        assertEquals(EShapeType.Hexagon, hexagon.getType(), "Тип должен быть Hexagon");
        assertEquals("Hexagon", hexagon.getStringType(), "Строковый тип должен быть 'Hexagon'");
    }
}
