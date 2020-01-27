package SaveData;

public class SaveSSwisheshaEkaka {

    //සමාජිකයා සේවය කළ විශේෂ ඒකක
    //සාමාජික අංකය 
    private final String SA;
    //විශේෂ ඒකකය  
    private final String SSWE;
    //බදුන දිනය 
    private final String DB;
    //කණ්ඩායම් අංක හා අනුකන්ඩ අංක
    private final String KAAA;
    //-----------------------------------------

    public SaveSSwisheshaEkaka(String SA, String SSWE, String DB, String KAAA) {
        this.SA = SA;
        this.SSWE = SSWE;
        this.DB = DB;
        this.KAAA = KAAA;
    }

    public String getQuaryString() {
        return "INSERT INTO SSWE(SA,SSWE,DB,KAAA) VALUES("
                + "'" + SA + "'," + "'" + SSWE + "'," + "'" + DB + "'," + "'" + KAAA + "'" + ")";
    }

    public void save() {
        //සාමාජිකයින් සේවය කළ විශේෂ ඒකක 
        String quary = "INSERT INTO SSWE(SA,SSWE,DB,KAAA) VALUES("
                + "'" + SA + "'," + "'" + SSWE + "'," + "'" + DB + "'," + "'" + KAAA + "'" + ")";

        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
           new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
         }

    }

}
