package SaveData;

public class SaveAthuruAbada {
    //AASS-අතුරු ආබාධ සහිත සාමාජිකයින්
    //ප්‍රහාර අංකය

    private final String SA;
    //අබදය 
    private final String ABD;
    //---------------------------------------------------

    public SaveAthuruAbada(String SA, String ABD) {
        this.SA = SA;
        this.ABD = ABD;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO AASS(SA,ABD) VALUES("
                + "'" + SA + "'," + "'" + ABD + "'"
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
