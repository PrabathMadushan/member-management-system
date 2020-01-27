package SaveData;

import EnumClases.LogerName;

public class SaveSahakaru {

    //SS- ස්මජිකයාගේ සහකරු/සහකාරිය
    //---------------------------------------------------------------
    //සාමාජික අංකය 
    private final String SA;
    //සහකරුගේ රාජකාරි ලිපිනය 
    private final String SRL;
    //සහකරුගේ දුරකථන අංකය 
    private final String SDA;
    //සහකරුගේ රැකියාව
    private final String SR;
    //සහකරුගේ  සම්පුර්ණ නම 
    private final String SSN;
    //----------------------------------------------------------------

    public SaveSahakaru(String SA, String SRL, String SDA, String SR, String SSN) {
        this.SA = SA;
        this.SRL = SRL;
        this.SDA = SDA;
        this.SR = SR;
        this.SSN = SSN;
    }
    
     public String getQuaryString() {
        return  "INSERT INTO SS(SA,SRL,SDA,SR,SSN) VALUES("
                + "'" + SA + "'," + "'" + SRL + "'," + "'" + SDA + "',"
                + "'" + SR + "'," + "'" + SSN + "'" + ")";
    }

    public void save() {
        //සහකරු/සහකරිනිය 
        String quary = "INSERT INTO SS(SA,SRL,SDA,SR,SSN) VALUES("
                + "'" + SA + "'," + "'" + SRL + "'," + "'" + SDA + "',"
                + "'" + SR + "'," + "'" + SSN + "'" + ")";
        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
         }
    }

}
