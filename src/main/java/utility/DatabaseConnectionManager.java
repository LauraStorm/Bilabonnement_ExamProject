package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager(){}

    public static Connection getConnection(){
        if(conn != null){
            return conn;
        }


        url = "jdbc:mysql://bilabonnementdatabase.mysql.database.azure.com:3306/bilabonnement?useSSL=true&requireSSL=false";
        username = "bilabonnement@bilabonnementdatabase";
        password = "LauraRasmusSimonElisa123";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("yes");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no");
        }

        return conn;
    }

    public static void main(String[] args) {
        DatabaseConnectionManager.getConnection();
    }
}
