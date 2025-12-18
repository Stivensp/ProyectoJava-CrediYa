package com.finalproyectjava.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/crediya_db?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    // Método de prueba
    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            if (con != null) {
                System.out.println("Conectar a bd");
            }
        } catch (SQLException e) {

        }
    }
}
/*
Connection con = null;

try {
    con = DriverManager.getConnection(url, user, pass);
    // usar la conexión
} catch (SQLException e) {
    System.out.println("Error de conexión");
} finally {
    try {
        if (con != null) {
            con.close(); // 🔑 MUY IMPORTANTE
        }
    } catch (SQLException e) {
        System.out.println("Error al cerrar conexión");
    }
}

*/
