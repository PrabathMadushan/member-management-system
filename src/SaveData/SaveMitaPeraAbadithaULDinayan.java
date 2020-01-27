package SaveData;

public class SaveMitaPeraAbadithaULDinayan {

    //AULD-අබාධිත උපකරණ ලබාගත් දිනයන්
    //අනු අංකය 
    private final String UA;
    //උපකරණය ලබාගත් දිනය 
    private final String ULD;
    //-------------------------------------------

    public SaveMitaPeraAbadithaULDinayan(String UA, String ULD) {
        this.UA = UA;
        this.ULD = ULD;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO AULDMITAPERA(UA,ULD) VALUES("
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
