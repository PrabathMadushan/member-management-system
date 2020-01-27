package SaveData;

public class SaveDWWNSamajikayin {

    //DWWNS- දුබලතා විශ්‍රාම වැටුප් නොලබන සාමාජිකයින්
    //----------------------------------------------------------------
    //සාමාජික අංකය 
    private final String SA;

    //---------------------------------------------------------------
    public SaveDWWNSamajikayin(String SA) {
        this.SA = SA;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO DWWNS(SA) VALUES("
                + "'" + SA + "')";
    }

    public void save() {
        //දුබලතා විශ්‍රාම වැටුප් නොලබන සාමාජිකයින් 
        String quary = makeQuaryString();
        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }
    }

}
