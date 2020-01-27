
package OtherClases;

import Interfaces.returnBoolean;
import Interfaces.returnString;


public class Funtional {
    
    public static boolean getBooleanFun(returnBoolean rb){
        return rb.getBoolean();
    }
    
    public static String getStringValue(returnString rs) throws Exception{
        return rs.getString();
    }
}

