package SaveData;

import EnumClases.LogerName;

public class SaveMWLSamajikayin {

    // මාසික වැටුප් ලබන සාමාජිකයින්
    //සාමාජික අංකය 
    private final String SA;
    //ලබන කොට්ටාශය 
    private final String LK;
    //පොලිස් ස්ථානය 
    private final String PS;
    //තාම ලැබේද 

    //වැටුපේ වටිනාකම 
    private final String WWT;
    //දීමනා වල වටිනකම 
    private final String DWT;
    //----------------------------------------------

    public SaveMWLSamajikayin(String SA, String LK, String PS, String WWT, String DWT) {
        this.SA = SA;
        this.LK = LK;
        this.PS = PS;

        this.WWT = WWT;
        this.DWT = DWT;
    }

    public String getQuaryString() {
        return "INSERT INTO MWLS(SA,LK,PS,WWT,DWT) VALUES("
                + "'" + SA + "'," + "'" + LK + "',"
                + "'" + PS + "'," 
                + "'" + WWT + "'," + "'" + DWT + "'" + ")";
    }

    public void save() {
        String quary = getQuaryString();
        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }

    }

}
