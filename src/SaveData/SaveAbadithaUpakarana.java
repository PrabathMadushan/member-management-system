package SaveData;

public class SaveAbadithaUpakarana {

    //AU-අබාදිත උපකරණ
    //සාමාජික අංකය 
    private final String SA;
    //අනු අංකය 
    private final String UA;
    //උපකරණ කාණ්ඩ අංකය 
    private final String AUN;
    //------------------------------------------

    public SaveAbadithaUpakarana(String SA, String UA, String AUN) {
        this.SA = SA;
        this.UA = UA;
        this.AUN = AUN;
    }

    public String getQuaryString() {
        return makeQuaryString();
    }

    private String makeQuaryString() {
        return "INSERT INTO SAU(UA,AUN,SA) VALUES("
                + "'" + UA + "'," + "'" + AUN + "'," + "'" + SA + "'" + ")";
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
