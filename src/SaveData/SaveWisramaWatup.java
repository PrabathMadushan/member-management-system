package SaveData;

public class SaveWisramaWatup {

    //WW-විශ්‍රාම වැටුප
    //---------------------------------------------------------------
    //සාමාජික අංකය 
    private final String SA;
    //ප්‍රාදේශීය ලේකම් කාර්යාලය 
    private final String WWPLK;
    //විශ්‍රාම වැටුප් අංකය 
    private final String WWA;

    private final String WTKM;
    //---------------------------------------------------------------

    public SaveWisramaWatup(String SA, String WWPLK, String WWA, String WTKM) {
        this.SA = SA;
        this.WWPLK = WWPLK;
        this.WWA = WWA;
        this.WTKM = WTKM;
    }

    public String getQuaryString() {
        return "INSERT INTO WW(SA,PLK,WWA,WTKM) VALUES("
                + "'" + SA + "'," + "'" + WWPLK + "'," + "'" + WWA + "','" + WTKM + "'" + ")";
    }

    public void save() {
        //විශ්‍රාම වැටුප 

        try {
            System.out.println(getQuaryString());
            SQLConnection.SqlConnection.updateDB(getQuaryString());
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }

    }

}
