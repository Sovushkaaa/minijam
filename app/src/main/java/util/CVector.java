//---------------------------

package util;

//---------------------------

//c++ - pseudo struct
public class CVector {
    
    public CVector() {  }
    
    public CVector(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public CVector(CVector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }
    
    public int x, y;
}

//---------------------------
