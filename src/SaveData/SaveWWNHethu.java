package SaveData;

public class SaveWWNHethu {

    //සමාජිකයින්ට විශ්‍රාම වැටුප් නොලැබීමට හේතු    
    //අනු  අංකය 
    private final String SA;
    //අනු  අංකය 
    private final String HTW;
    //-------------------------------

    public SaveWWNHethu(String SA, String HTW) {
        this.SA = SA;
        this.HTW = HTW;
    }
    
       public String getQuaryString() {
        return  "INSERT INTO SWWNH(SA,HTW) VALUES("
                + "'" + SA + "'," + "'" + HTW + "'" + ")";
    }

    public void save() {
        String quary =getQuaryString();
        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
             new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }
    }

}
