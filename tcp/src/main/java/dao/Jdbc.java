package dao;

import utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    protected Connection conn;


    public void conect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION, Constants.DB_USER_CONNECTION, Constants.DB_PASS_CONNECTION);
            conn.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error al Cerrar : " + e);
        }
    }
}