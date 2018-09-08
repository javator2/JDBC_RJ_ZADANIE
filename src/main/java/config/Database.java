package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static final String URL = "jdbc:mysql://localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Nie ma paczania";
    private static final String PORT = "3306";
    private static final String DATABASE = "ksiegarnia";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PARAMS = "useSSL=false&serverTimezone=UTC";
    private static Connection connection = null;

    public static String getFormatedURL(){
        return URL + ":" + PORT + "/" + DATABASE + "?" + PARAMS;
    }

    private static void loadDriver(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean loadConnection(){
        try {
            connection = DriverManager.getConnection(getFormatedURL(), USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(connection == null){
            return false;
        }
        return true;
    }

    public static Connection getConnection(){
        if (connection == null){
            loadDriver();
            loadConnection();
        }
        return connection;
    }

    public static void closeConnection(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
