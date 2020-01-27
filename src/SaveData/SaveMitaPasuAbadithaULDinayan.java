package SaveData;

public class SaveMitaPasuAbadithaULDinayan {

    //AULD-අබාධිත උපකරණ ලබාගත් දිනයන්
    //අනු අංකය 
    private final String UA;
    //උපකරණය ලබාගත් දිනය 
    private final String ULD;
    //-------------------------------------------

    public SaveMitaPasuAbadithaULDinayan(String UA, String ULD) {
        this.UA = UA;
        this.ULD = ULD;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO AULDMITAPASU(UA,ULD) VALUES("
                + "'" + UA + "'," + "'" + ULD + "'" + ")";
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
