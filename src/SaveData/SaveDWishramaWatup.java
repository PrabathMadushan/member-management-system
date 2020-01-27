package SaveData;

public class SaveDWishramaWatup {

    //DWW- දුබලතා විශ්‍රාම වැටුප
    //--------------------------------------------------------------- 
    //සාමාජික අංකය 

    private final String SA;
    //ප්‍රාදේශීය ලේකම් කාර්යාලය
    private final String PLK;
    //විශ්‍රාම වැටුප් අංකය 
    private final String WWA;
    private final String WTKM;
    
    
    //---------------------------------------------------------------

    public SaveDWishramaWatup(String SA, String PLK, String WWA, String WTKM) {
        this.SA = SA;
        this.PLK = PLK;
        this.WWA = WWA;
        this.WTKM = WTKM;
    }

    public String getQuaryString() {
        return "INSERT INTO DWW(SA,PLK,WWA,WTKM) VALUES("
                + "'" + SA + "'," + "'" + PLK + "'," + "'" + WWA + "'," +"'" + WTKM + "'" + ")";
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
