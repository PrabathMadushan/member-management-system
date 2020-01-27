package SaveData;

public class SaveSamajikaya {

    //SMJK-සාමාජිකයා 
    //------------------------------------------------------
    //සාමාජික අංකය 
    private final String SA;
    //ජාතික හැදුනුම්පත් අංකය 
    private final String JHA;
    //සම්පුර්ණ නම 
    private final String SN;
    //පොලිසියට බැදුන දිනය 
    private final String PBD;
    //උපන් දිනය 
    private final String UD;
    //අවසන් වරට සේවය කළ ස්ථානය 
    private final String AWSKS;
    //අවසන් වරට සේවය කළ කොට්ටාශය 
    private final String AWSKK;
    //විද්‍යුත් ලිපිනය 
    private final String WL;
    //විශ්‍රාම ලැබූ දිනය 
    private final String WLD;
    //නැවත විවාහ වුවේද 
    private final String NWUD;
    //ජංගම දුරකථන අංකය 
    private final String JDA;
    //ස්ථාවර දුරකථන අංකය 
    private final String SDA;
    //නිත්‍ය  සේවාද උප සේවාද
    private final String NSUS;
    //සාමාජිකත්වය ලැබූ දිනය
    private final String SLD;
    //දික්කසද වීද
    private final String DKKD;
    //විවහකද 
    private final String WD;
    //විවාහය අසාර්ථක වීමට අබාධිත තත්වය බලපෑවේද
    private final String WAWATBD;
    //--------------------------------------------------------------

    public SaveSamajikaya(String SA, String JHA, String SN, String PBD,
            String UD, String AWSKS, String AWSKK, String WL,
            String WLD, String NWUD, String JDA, String SDA,
            String DKKD, String WD, String WAWATBD, String NSUS,
            String SLD) {
        this.AWSKK = AWSKK;
        this.AWSKS = AWSKS;
        this.DKKD = DKKD;
        this.JDA = JDA;
        this.JHA = JHA;
        this.NSUS = NSUS;
        this.NWUD = NWUD;
        this.PBD = PBD;
        this.SA = SA;
        this.SDA = SDA;
        this.SLD = SLD;
        this.SN = SN;
        this.UD = UD;
        this.WAWATBD = WAWATBD;
        this.WD = WD;
        this.WL = WL;
        this.WLD = WLD;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO SMJK(SA,JHA,SN,PBD,"
                + "UD,AWSKS,AWSKK,WL,WLD,NWUD,JDA,SDA,"
                + "DKKD,WD,WAWATBD,NSUS,SLD ) "
                + "VALUES("
                + "'" + SA + "'," + "'" + JHA + "'," + "'" + SN + "',"
                + "'" + PBD + "'," + "'" + UD + "'," + "'" + AWSKS + "',"
                + "'" + AWSKK + "'," + "'" + WL + "'," + "'" + WLD + "',"
                + "'" + NWUD + "'," + "'" + JDA + "'," + "'" + SDA + "',"
                + "'" + DKKD + "'," + "'" + WD + "'," + "'" + WAWATBD + "',"
                + "'" + NSUS + "'," + "'" + SLD + "')";
    }

    public void save() {

        try {
            String quary = makeQuaryString();
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }

    }

}
