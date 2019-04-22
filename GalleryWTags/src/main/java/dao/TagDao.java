package dao;

import domain.Img;
import domain.Tag;
import java.sql.*;
import java.util.*;

public class TagDao implements Dao<Tag, Integer> {
    private Database db;
    
    public TagDao(Database db) {
        this.db = db;
    }
    
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

    @Override
    public List<Tag> findAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tag");
        
        ResultSet rs = stmt.executeQuery();
        List<Tag> tags = new ArrayList<>();
        while(rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            
            tags.add(new Tag(id, name));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return tags;
    }

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

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
