package dev.webacademy.bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciadorConexao {

    private static Connection connection = null;

    public GerenciadorConexao() {
    }

    public static synchronized Connection getConnection() {
        try {
            if (connection == null) {
//            Properties prop = new Properties();
//            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/database.properties");
//            System.err.println(inputStream);
//            prop.load(inputStream);
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dev_web_academy_v1", "postgres", "postgres");
            }
        } catch (SQLException | ClassNotFoundException e) {}
        return connection;
    }
}
