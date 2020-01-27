package OtherClases;

import java.sql.ResultSet;

public class PKGenaretor {

    public static String getNextPK(String tableName, String colomnName) {
        int nextPK = -1;
        try {
            String quary="select max(" + colomnName + ")  as "+colomnName+" from " + tableName;
            ResultSet rs = SQLConnection.SqlConnection.getData(quary);
            if (!rs.first()) {
                nextPK = 1;
            } else {
                nextPK = rs.getInt(colomnName) + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Integer.toString(nextPK);
    }

    private PKGenaretor() {
    }
}
