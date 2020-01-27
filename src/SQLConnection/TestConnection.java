package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public Connection Test() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String DATABASE_NAME = ConfigFile.ConfigHelper.getDatabase_name();
            final String PORT = ConfigFile.ConfigHelper.getPort();
            final String USER_NAME = ConfigFile.ConfigHelper.getUser_name();
            final String PASSWORD = ConfigFile.ConfigHelper.getPassword();
            con = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT + "/" + DATABASE_NAME, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return con;
    }
}
