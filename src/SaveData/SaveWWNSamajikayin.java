package SaveData;

public class SaveWWNSamajikayin {

    //WWNS-විශ්‍රාම වැටුප් නොලබන සාමාජිකයින්
    //----------------------------------------------------------------
    //සාමාජික අංකය 
    private final String SA;
    //---------------------------------------------------------------

    public SaveWWNSamajikayin(String SA) {
        this.SA = SA;
    }
  public String getQuaryString() {
        return  "INSERT INTO WWNS(SA) VALUES("
                + "'" + SA + "'" +  ")";
    }
    public void save() {
        //විශ්‍රාම වැටුප් නොලබන සාමාජිකයින් 
        String quary =getQuaryString();
        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
             new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }
    }

}
