/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author borges
 */
public class Conn {

    private static Connection connection = null;
    private final static String ADRESS = "jdbc:mysql://localhost";
    private final static String DATABASE = "ifc";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String PORT = "3306";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(ADRESS + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            throw (SQLException) ex;
        }
    }
}
