package SaveData;

public class SaveSThanathura {

    //සමාජිකයාගේ තනතුර
    //තනතුරු අංකය 
    private final String SA;
    //පොලිසියට බැදෙන විට තනතුර
    private final String TNTR;
    //විශ්‍රාම යන විට තනතුර 
    private final String TNTR2;
    //නිල අංකය 
    //private final String NA;
    //උපසේවා අංකය 
    private final String UA;
    //රණවිරු හැදුනුම්පත් අංකය 
    private final String RHA;
    // 
    private final String NA;
            
    //-----------------------------------------

    public SaveSThanathura(String SA, String TNTR1, String TNTR2,  String UA, String RHA,String NSA) {
        this.SA = SA;
        this.TNTR = TNTR1;
        //this.NA = NA;
        this.UA = UA;
        this.RHA = RHA;
        this.TNTR2 = TNTR2;
        this.NA=NSA;
    }
    
    public String getQuaryString() {
        return "INSERT INTO ST(SA,TNTR1,UA,RHA,TNTR2,NA) VALUES("
                    + "'" + SA + "'," + "'" + TNTR + "'," 
                    + "'" + UA + "'," + "'" + RHA + "'," + "'" + TNTR2 + "'," + "'" + NA + "'" + ")";
    }

    public void save() {
        //සාමාජිකයින්ගේ තනතුරු 

        try {
            String quary = getQuaryString();

            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }

    }

}
