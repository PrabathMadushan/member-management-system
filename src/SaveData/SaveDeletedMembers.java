package SaveData;

import OtherClases.PKGenaretor;

public class SaveDeletedMembers {

    //Deleted Member
    //------------------------------------------
    private final String deletedID;
    private final String SID;
    private final String NIC;
    private final String NAME;
    private final String RESON;
    //------------------------------------------
    public SaveDeletedMembers(String SID, String NIC, String NAME, String RESON) {
        this.deletedID = PKGenaretor.getNextPK("DELETED_MEMBERS", "DELETED_ID");
        this.SID = SID;
        this.NIC = NIC;
        this.NAME = NAME;
        this.RESON = RESON;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO DELETED_MEMBERS(DELETED_ID,SID,NIC,NAME,RESON) VALUES("
                + "'" + deletedID + "'," + "'" + SID + "'," + "'" + NIC + "',"
                + "'" + NAME + "'," + "'" + RESON + "'"
                + ")";
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
