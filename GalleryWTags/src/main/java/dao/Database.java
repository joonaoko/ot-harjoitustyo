package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for initializing the database.
 */
public class Database {
    private String dbAddress;
    
    public Database(String dbAddress) throws ClassNotFoundException {
        this.dbAddress = dbAddress;
    }
    
    /**
     * Connects to the database given in the address, and returns connection.
     * @return Connection
     * @throws SQLException SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbAddress);
    }
    
    /**
     * Creates tables if database hasn't been initialized yet.
     */
    public void init() {
        List<String> sqliteInit = sqliteStatements();
        
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            
            for (String s : sqliteInit) {
                stmt.executeUpdate(s);
            }
        } catch (Throwable t) {
            // Error
        }
    }
    
    private List<String> sqliteStatements() {
        List<String> sqliteInit = new ArrayList<>();
        
        sqliteInit.add("CREATE TABLE Image (id integer PRIMARY KEY, title varchar(240), path varchar(300))");
        sqliteInit.add("CREATE TABLE Tag (id integer PRIMARY KEY, name varchar(240))");
        sqliteInit.add("CREATE TABLE ImageTag (image_id integer, tag_id integer, FOREIGN KEY (image_id) REFERENCES Image(id), FOREIGN KEY (tag_id) REFERENCES Tag(id))");
        return sqliteInit;
    }
}
