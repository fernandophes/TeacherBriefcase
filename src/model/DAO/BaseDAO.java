package src.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {

    // Configurar acesso ao Banco de Dados
    private static Connection conn = null;
    private static final String url = "jdbc:postgresql://127.0.0.1:5432/teacher-briefcase";
    private static final String usuario = "teacher";
    private static final String senha = "briefcase";

    // Abrir uma conexão única, e retornar a atual se já existir
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, usuario, senha);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    // Fechar a conexão, caso ela exista
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
