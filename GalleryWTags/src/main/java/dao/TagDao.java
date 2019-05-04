package dao;

import domain.Img;
import domain.Tag;
import java.sql.*;
import java.util.*;

/**
 * Class for finding, updating and deleting tags in the database.
 */
public class TagDao implements Dao<Tag, Integer> {
    private Database db;
    
    public TagDao(Database db) {
        this.db = db;
    }
    
    /**
     * Finds specified tag in the Tag table and returns it.
     * @param key ID of the tag
     * @return The tag
     * @throws SQLException SQLException
     */
    @Override
    public Tag findOne(Integer key) throws SQLException {
        
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tag WHERE id = ?");
        stmt.setInt(1, key);
        
        ResultSet rs = stmt.executeQuery();
        
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        
        Tag tag = new Tag(id, name);
       
        rs.close();
        stmt.close();
        conn.close();
        
        return tag;
    }
    
    /**
     * Finds all tags in the tag table.
     * @return List of all tags in the database
     * @throws SQLException SQLException
     */
    @Override
    public List<Tag> findAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tag");
        
        ResultSet rs = stmt.executeQuery();
        List<Tag> tags = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            
            tags.add(new Tag(id, name));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return tags;
    }
    
    /**
     * Saves a new tag to the database.
     * @param tag The tag being saved
     * @throws SQLException SQLException
     */
    @Override
    public void save(Tag tag) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Tag"
                + "(name)"
                + "VALUES (?)");
        
        stmt.setString(1, tag.toString());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
    
    /**
     * Removes a tag from the database.
     * @param key ID of the tag being removed
     * @throws SQLException SQLException
     */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Tag WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
    
}
