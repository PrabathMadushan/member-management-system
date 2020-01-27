package SaveData;

import EnumClases.LogerName;

public class SaveWidyaPariksahanaya {

    //සාමාජිකයාගේ වෛද්‍ය පරීක්ෂණය
    //ප්‍රහාර අංකය 

    private final String SA;
    //සිදුකල ස්ථානය 
    private final String SKS;
    //සිදුකල දිනය 
    private final String SKD;
    //සිදුකල කොට්ටාශය 
    private final String SKK;
    //--------------------------------------------

    public SaveWidyaPariksahanaya(String SA, String SKS, String SKD, String SKK) {
        this.SA = SA;
        this.SKS = SKS;
        this.SKD = SKD;
        this.SKK = SKK;
    }

    public String getQuaryString() {
        return "INSERT INTO SJWP(SA,SKS,SKD,SKK) VALUES("
                + "'" + SA + "'," + "'" + SKS + "'," + "'" + SKD + "',"
                + "'" + SKK + "'" + ")";
    }

    public void save() {
        String quary = getQuaryString();

        try {
            SQLConnection.SqlConnection.updateDB(quary);
        } catch (Exception e) {
            new OtherClases.MyExceptionDialog(this.getClass(), e).setVisible(true);
        }
    }

}
