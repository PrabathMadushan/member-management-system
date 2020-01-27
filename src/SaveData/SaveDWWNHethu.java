package SaveData;

public class SaveDWWNHethu {

    //සමාජිකයින්ට දුබලතා  විශ්‍රාම වැටුප් නොලැබීමට හේතු    
    //අනු  අංකය 
    private final String SA;
    //හේතුව 
    private final String HTW;
    //-------------------------------

    public SaveDWWNHethu(String SA, String HTW) {
        this.SA = SA;
        this.HTW = HTW;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO SDWWNH(SA,HTW) VALUES("
                + "'" + SA + "'," + "'" + HTW + "'" + ")";
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
