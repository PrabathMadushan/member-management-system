package SaveData;

public class SaveTrasthaPrahara {

    //ත්‍රස්තවාදී ප්‍රහාර වලට ලක් වූ සමාජිකයින්
    //ප්‍රහාර අංකය 
    private final String SA;
    //ඉපයීමේ ශක්තිය හීන වූ ප්‍රතිශතය 
    private final String ISHP;
    //ඔත්පල වී ඇද්ද
    private final String OWAD;
    //ත්‍රස්තවාදී කාණ්ඩායාම  
    private final String TRK;
    //ලක් වූ දිනය 
    private final String LUD;
    //ලක් වූ  කොට්ටාශය 
    private final String LUK;
    //අනතුරේ ස්වබාවය 
    private final String ASB;
    //ලක් වූ ස්ථානය 
    private final String LUS;
    //--------------------------------------

    public SaveTrasthaPrahara(String SA, String ISHP, String OWAD, String TRKA, String LUD, String LUK, String ASB, String LUS) {
        this.SA = SA;
        this.ISHP = ISHP;
        this.OWAD = OWAD;
        this.TRK = TRKA;
        this.LUD = LUD;
        this.LUK = LUK;
        this.ASB = ASB;
        this.LUS = LUS;
    }

       public String getQuaryString() {
        return  "INSERT INTO TRPWLS(SA,ISHP,OWAD,TRK,LUD,LUK,ASB,LUS)  VALUES("
                    + "'" + SA + "'," + "'" + ISHP + "'," + "'" + OWAD + "',"
                    + "'" + TRK + "'," + "'" + LUD + "'," + "'" + LUK + "',"
                    + "'" + ASB + "'," + "'" + LUS + "'" + ")";
    }
    public void save() {
        try {
            //ත්‍රස්තවාදී ප්‍රහාර වලට ලක් වූ සමාජිකයින්
            String quary = getQuaryString();
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
       }
    }

}
