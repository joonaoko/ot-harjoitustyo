package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String dbAddress;
    
    public Database(String dbAddress) throws ClassNotFoundException {
        this.dbAddress = dbAddress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbAddress);
    }
    
    public void init() {
        List<String> sqliteInit = new ArrayList<>();
        sqliteInit.add("CREATE TABLE Image (id integer PRIMARY KEY, title varchar(240), path varchar(300))");
        sqliteInit.add("INSERT INTO Image (title, path) VALUES ('Test', 'src/main/resources/images/testimg.jpg')");
        sqliteInit.add("INSERT INTO Image (title, path) VALUES ('Test2', 'src/main/resources/images/testimg2.jpg')");
        
        sqliteInit.add("CREATE TABLE Tag (id integer PRIMARY KEY, name varchar(240))");
        sqliteInit.add("INSERT INTO Tag (name) VALUES ('smiley')");
        sqliteInit.add("INSERT INTO Tag (name) VALUES ('face')");
        sqliteInit.add("INSERT INTO Tag (name) VALUES ('upside down')");
        
        sqliteInit.add("CREATE TABLE ImageTag (image_id integer, tag_id integer, FOREIGN KEY (image_id) REFERENCES Image(id), FOREIGN KEY (tag_id) REFERENCES Tag(id))");
        sqliteInit.add("INSERT INTO ImageTag (image_id, tag_id) VALUES (1, 1)");
        sqliteInit.add("INSERT INTO ImageTag (image_id, tag_id) VALUES (1, 2)");
        sqliteInit.add("INSERT INTO ImageTag (image_id, tag_id) VALUES (2, 1)");
        sqliteInit.add("INSERT INTO ImageTag (image_id, tag_id) VALUES (2, 2)");
        sqliteInit.add("INSERT INTO ImageTag (image_id, tag_id) VALUES (2, 3)");
        
        
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            
            for (String s : sqliteInit) {
                stmt.executeUpdate(s);
            }
        } catch (Throwable t) {
            System.out.println("Error: "+t.getMessage());
        }
    }
}
