//---------------------------

package minijam;

//---------------------------

public class Log {
    
    public Log() {
        //
    }
    
    //---------------------------
    
    static public void print(Object o, String message) {
        System.out.println(" - LOG: " + o.getClass() + " ~ " + message);
    }
    
    //---------------------------
    
    static public void error(Object o, String message) {
        System.err.println(" - ERROR LOG: " + o.getClass() + " ~ " + message);
    }
}

//---------------------------
