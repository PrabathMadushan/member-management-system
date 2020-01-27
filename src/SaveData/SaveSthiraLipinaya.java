package SaveData;

public class SaveSthiraLipinaya {
    //SL-ස්ථිර ලිපිනය
    //--------------------------------------------------------------
    //සාමාජික අංකය 
    private final String SA;
    //සාමාජිකයාගේ ස්ථිර ලිපිනය 
    private final String SL;
    //සාමාජිකයාගේ ස්ථිර ලිපිනය 
    private final String GNW;
    //පොලිස් වසම
    private final String PW;
    //ප්‍රාදේශීය ලේඛම් කොට්ටාශය 
    private final String SLPLK;
    //දිස්ත්‍රීක්කය 
    private final String DTRK;
    //---------------------------------------------------------------

    public SaveSthiraLipinaya(String SA, String SL, String GNW, String PW, String SLPLK, String DTRK) {
        this.SA = SA;
        this.SL = SL;
        this.GNW = GNW;
        this.PW = PW;
        this.SLPLK = SLPLK;
        this.DTRK = DTRK;
    }

    public String getQuaryString() {
        return "INSERT INTO SL(SA,SL,GNW,PW,PLK,DTRK) VALUES("
                + "'" + SA + "'," + "'" + SL + "'," + "'" + GNW + "',"
                + "'" + PW + "'," + "'" + SLPLK + "'," + "'" + DTRK + "'" + ")";
    }
    public void save() {
        String quary = "INSERT INTO SL(SA,SL,GNW,PW,PLK,DTRK) VALUES("
                + "'" + SA + "'," + "'" + SL + "'," + "'" + GNW + "',"
                + "'" + PW + "'," + "'" + SLPLK + "'," + "'" + DTRK + "'" + ")";
        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }

    }

}
