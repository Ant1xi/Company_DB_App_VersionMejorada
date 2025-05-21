package com.daw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos Oracle.
 * Proporciona métodos estáticos para obtener y cerrar conexiones.
 * 
 * Esta clase centraliza la lógica de conexión para facilitar su reutilización
 * en los distintos DAOs del proyecto.
 * 
 * @author Antonio
 */
public class ClaseConectoraBBDD {

    /** URL de conexión a la base de datos Oracle XE local. */
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";

    /** Usuario de la base de datos. */
    private static final String USER = "C##COMPANY2";

    /** Contraseña del usuario de la base de datos. */
    private static final String PASSWORD = "company2";

    /**
     * Establece y devuelve una conexión activa a la base de datos.
     * 
     * @return una {@link Connection} activa a Oracle.
     * @throws SQLException si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Cierra la conexión proporcionada si no es nula.
     * 
     * @param conn la conexión a cerrar.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
