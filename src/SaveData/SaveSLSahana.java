package SaveData;

public class SaveSLSahana {

    //සාමාජිකයාට ලැබෙන සහන. 
    //සමාජික අංකය 
    private final String SA;
    //සහනය 
    private final String SHN;
    //ලැබෙන ස්ථානය 
    private final String LS;
    //-----------------------------------
    //අවසන් වරට ලබාගත් දිනය
    private final String SAWLD;
    

    public SaveSLSahana(String SA, String SHN, String LS,String SAWLD) {
        this.SA = SA;
        this.SHN = SHN;
        this.LS = LS;
        this.SAWLD=SAWLD;
    }

    public String getQuaryString() {
        return "INSERT INTO SLS(SA,SHN,LS,SAWLD) VALUES("
                + "'" + SA + "'," + "'" + SHN + "'," + "'" + LS + "'," +"'" + SAWLD + "'" + ")";
    }

    public void save() {
        String quary = "INSERT INTO SLS(SA,SHN,LS,SAWLD) VALUES("
                + "'" + SA + "'," + "'" + SHN + "'," + "'" + LS + "'," +"'" + SAWLD + "'" + ")";

        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
             new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
       }
    }

}
