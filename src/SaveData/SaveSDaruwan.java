package SaveData;

import EnumClases.LogerName;

public class SaveSDaruwan {

    //ස්මජිකයාගේ දරුවන්
    //සාමාජික අංකය 
    private final String SA;
    //උපන් දිනය
    private final String UD;
    //නම 
    private final String NAME;
    //දැනට තත්වය
    private final String DT;
    //අබාධිතද
    private final String ABTD;
    //-------------------------------------

    public SaveSDaruwan(String SA, String UD, String NAME, String DT, String ABTD) {
        this.SA = SA;
        this.UD = UD;
        this.NAME = NAME;
        this.DT = DT;
        this.ABTD = ABTD;
    }

    public String getQuaryString() {
        return "INSERT INTO SD(SA,UD,NAME,DT,ABTD) VALUES("
                + "'" + SA + "'," + "'" + UD + "'," + "'" + NAME + "',"
                + "'" + DT + "'," + "'" + ABTD + "'"
                + ")";
    }

    public void save() {

        String quary = "INSERT INTO SD(SA,UD,NAME,DT,ABTD) VALUES("
                + "'" + SA + "'," + "'" + UD + "'," + "'" + NAME + "',"
                + "'" + DT + "'," + "'" + ABTD + "'"
                + ")";

        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
             new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }
    }

}
