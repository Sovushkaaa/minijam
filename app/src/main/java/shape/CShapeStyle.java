//---------------------------

package shape;

//---------------------------

import java.awt.Color;

//---------------------------

//c++ - pseudo struct
public class CShapeStyle {
    
    public CShapeStyle() {  }
    
    public CShapeStyle(Color color, Color borderColor, int thickness) {
        this.Color          = color;
        this.BorderColor    = borderColor;
        this.Thickness      = Math.abs(thickness);
    }
    
    public CShapeStyle(CShapeStyle Style) {
        BorderColor = Style.BorderColor;
        
        Color = Style.Color;
        Thickness = Style.Thickness;
    }
    
    public Color BorderColor,
                 Color;
    
    public int Thickness;
}

//---------------------------