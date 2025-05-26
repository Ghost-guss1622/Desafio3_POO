package Consultores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnBD {
    private static final String url = "jdbc:mysql://localhost:3306/adopcion_mascotas?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";
    private static final String driver = "com.mysql.jdbc.Driver";

    static {
        try{
            Class.forName(driver);
        }  catch (ClassNotFoundException e){
            throw new RuntimeException("Error al cargar el driver", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user,password);
    }
}