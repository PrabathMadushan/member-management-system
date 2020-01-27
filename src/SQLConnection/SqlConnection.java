package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlConnection {

    private static Connection con = null;

    public static Connection getSqlConnection() throws Exception {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                final String DATABASE_NAME = ConfigFile.ConfigHelper.getDatabase_name();
                final String PORT = ConfigFile.ConfigHelper.getPort();
                final String USER_NAME = ConfigFile.ConfigHelper.getUser_name();
                final String PASSWORD = ConfigFile.ConfigHelper.getPassword();
                con = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT + "/" + DATABASE_NAME, USER_NAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                
            }
        }
        return con;
    }

    public static void updateDB(String q) throws Exception {
        System.out.println(q);
        SqlConnection.getSqlConnection().createStatement().executeUpdate(q);
    }

    public static ResultSet getData(String q) throws Exception {
        System.out.println(q);
        return SqlConnection.getSqlConnection().createStatement().executeQuery(q);
    }

}
