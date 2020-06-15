package mysqldb;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase implements Serializable{

    private static final String CONFIG_FILE = "/configuration/db.properties";
    private static DataBase instance = null;
    private Properties configuration;
    private Connection connection;

    private DataBase() throws
            ClassNotFoundException,
            IOException,
            IllegalAccessException,
            InstantiationException {
        configuration = new Properties();
        try {
            configuration.load(DataBase.class.getResourceAsStream(CONFIG_FILE));
            try {
                String manejador = configuration.getProperty("database_driver");
                System.out.printf("Cargando el manejador de la base de datos: %s%n", manejador);
                Class.forName(manejador).newInstance();
            } catch (ClassNotFoundException
                    | IllegalAccessException
                    | InstantiationException ex) {
                System.err.println("No se pudo cargar el manejador de la base de datos..");
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                throw ex;
            }
        } catch (IOException ex) {
            System.err.println("No se pudo leer el archivo de configuración..");
            throw ex;
        }
    }
    public static DataBase getInstance() throws
            ClassNotFoundException,
            IOException,
            IllegalAccessException,
            InstantiationException {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public Connection getConnection(String dataBase, String user, String password)
            throws SQLException {
        if (connection != null) {
            connection.close();
        }
        String URL_connection = String.format("%s//%s/%s",
                configuration.getProperty("protocol"),
                configuration.getProperty("server_url"),
                dataBase);
        System.out.printf("Conexión: '%s'%n", URL_connection);

        connection = DriverManager.getConnection(URL_connection, user, password);
        return connection;
    }

    public Properties getConfig() {
        return configuration;
    }

    public static void main(String[] args) {
        try {
            DataBase db = DataBase.getInstance();
            Properties cfg = db.getConfig();
            try (Connection connection = db.getConnection(
                    cfg.getProperty("database"),
                    cfg.getProperty("user"),
                    cfg.getProperty("password")
            )) {
                System.out.println("La conexión fue exitosa..");
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
        }
    }

}
