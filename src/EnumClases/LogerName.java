
package EnumClases;


public enum LogerName {
  DBIN("DBIn"),DBOUT("DBOut"),EXCEPTION("Exception");
  
  private final String nameAsString;
  
    private LogerName(String name) {  
        this.nameAsString=name;
    }

    @Override
    public String toString() {
      return this.nameAsString;
    }
    
}
